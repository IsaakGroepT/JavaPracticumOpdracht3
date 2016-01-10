/*
 * The MIT License
 *
 * Copyright 2016 Isaak Malik.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package db.derby;

import db.ItemLijst;
import db.KlantenRegister;
import db.OpslagStrategy;
import db.UitleningenRegister;
import java.lang.reflect.Constructor;
import model.Item;
import model.Klant;
import model.Uitlening;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import model.Adres;

/**
 * @author Isaak Malik
 */
public class DerbyOpslagStrategy implements OpslagStrategy {
	
	private static String dbLink = "jdbc:derby:db/MediaWinkelDB;user=test;password=test";
	private static Connection db;
	
	public DerbyOpslagStrategy()
	{
		try {
			db = DriverManager.getConnection(dbLink);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void lezen()
	{
		if (db == null) {
			throw new IllegalStateException("Geen verbinding met databank");
		}
		
		itemsLezen();
		klantenLezen();
		uitleningenLezen();
	}

	/**
	 * Slaat de items, klanten en uitleningen op.
	 */
	@Override
	public void opslaan()
	{
		if (db == null) {
			throw new IllegalStateException("Geen verbinding met databank");
		}
		
		// We moeten wel eerst alle bestaande records verwijderen, anders hebben we duplicaten
		verversTabellen();
		
		itemsOpslaan();
		klantenOpslaan();
		uitleningenOpslaan();
	}
	
	private void itemsLezen()
	{
		try {
			Statement stm = db.createStatement();
			ResultSet result = stm.executeQuery("SELECT * FROM ITEM");

			while (result.next()) {
				//TODO: veranderen naar factory DP?
				switch(result.getString("TYPE")) {
					case "CD":
					case "Film":
					case "Spel":
						Class<?> klasse = Class.forName("model." + result.getString("TYPE"));
						Constructor<?> cons = klasse.getConstructor(String.class, UUID.class);
						Object itemObject = cons.newInstance(result.getString("TITEL"), UUID.fromString(result.getString("ID")));
						//System.out.println(itemObject.toString());
						break;
					default:
						throw new Exception("Dit type item is niet bestaande: '" + result.getString("TYPE") + "'");
				}
			}
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void klantenLezen()
	{
		try {
			Statement stm = db.createStatement();
			ResultSet result = stm.executeQuery("SELECT * FROM KLANT");

			while (result.next()) {
				Adres adres = new Adres();
				adres.setEmail(result.getString("EMAIL"));
				adres.setStraat(result.getString("STRAAT"));
				adres.setNummer(result.getInt("NUMMER"));
				adres.setPostcode(result.getInt("POSTCODE"));
				adres.setGemeente(result.getString("GEMEENTE"));
				
				// Wordt ook autom. toegevoegd aan het register in de klasse zelf
				new Klant(result.getInt("ID"), result.getString("VOORNAAM"), result.getString("ACHTERNAAM"), adres);
			}
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	private void uitleningenLezen()
	{
		try {
			Statement stm = db.createStatement();
			ResultSet result = stm.executeQuery("SELECT * FROM UITLENINGEN");

			while (result.next()) {
				// Wordt ook autom. toegevoegd aan het register in de klasse zelf
				new Uitlening(result.getInt("KLANT_ID"), UUID.fromString(result.getString("ITEM_ID")), 
						result.getInt("DAGEN"), result.getString("START_DATUM"));
			}
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void itemsOpslaan()
	{
		PreparedStatement stm = null;
		
		try {
			stm = db.prepareStatement("INSERT INTO ITEM (ID, TYPE, TITEL, UITGELEEND) VALUES (?, ?, ?, ?)");

			for (int n = 0; n < ItemLijst.getItems().size(); n++) {
				Item huidigItem = ItemLijst.getItemObvIdx(n);
				
				stm.setString(1, huidigItem.getID().toString());
				stm.setString(2, huidigItem.getType().toString());
				stm.setString(3, huidigItem.getTitel());
				stm.setString(4, String.valueOf(huidigItem.isUitgeleend()));
				stm.executeUpdate();
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void klantenOpslaan()
	{
		PreparedStatement stm = null;
		
		try {
			stm = db.prepareStatement("INSERT INTO KLANT (ID, VOORNAAM, ACHTERNAAM, EMAIL, STRAAT, NUMMER, POSTCODE, GEMEENTE)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
			for (int n = 0; n < KlantenRegister.getKlanten().size(); n++) {
				Klant huidigeKlant = KlantenRegister.getKlanten().get(n);
				
				stm.setInt(1, huidigeKlant.getID());
				stm.setString(2, huidigeKlant.getVoornaam());
				stm.setString(3, huidigeKlant.getAchternaam());
				stm.setString(4, huidigeKlant.getAdres().getEmail());
				stm.setString(5, huidigeKlant.getAdres().getStraat());
				stm.setInt(6, huidigeKlant.getAdres().getNummer());
				stm.setInt(7, huidigeKlant.getAdres().getPostcode());
				stm.setString(8, huidigeKlant.getAdres().getGemeente());
				stm.executeUpdate();
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void uitleningenOpslaan()
	{
		PreparedStatement stm = null;
		
		try {
			stm = db.prepareStatement("INSERT INTO uitleningen (KLANT_ID, ITEM_ID, DAGEN, START_DATUM) VALUES (?, ?, ?, ?)");
			
			for (int n = 0; n < UitleningenRegister.getUitleningen().size(); n++) {
				Uitlening huidigeUitl = UitleningenRegister.getUitleningen().get(n);
				
				stm.setInt(1, huidigeUitl.getKlantID());
				stm.setString(2, huidigeUitl.getItemID().toString());
				stm.setInt(3, huidigeUitl.getAantalDagen());
				stm.setString(4, huidigeUitl.getStartDatum().getDatumInEuropeesFormaat());
				stm.executeUpdate();
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void verversTabellen()
	{
		Statement stm;
		
		try {
			stm = db.createStatement();
			
			stm.execute("DELETE FROM ITEM");
			stm.execute("DELETE FROM KLANT");
			stm.execute("DELETE FROM UITLENINGEN");
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void close()
	{
		try {
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
