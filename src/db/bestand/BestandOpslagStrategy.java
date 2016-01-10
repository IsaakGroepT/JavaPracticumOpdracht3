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

package db.bestand;

import db.ItemLijst;
import db.KlantenRegister;
import db.OpslagStrategy;
import db.UitleningenRegister;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.Scanner;
import java.util.UUID;
import model.Adres;
import model.Item;
import model.Klant;
import model.Uitlening;

/**
 * @author Isaak Malik
 */
public class BestandOpslagStrategy implements OpslagStrategy {

	@Override
	public void lezen()
	{
		itemsLezen();
		klantenLezen();
		uitleningenLezen();
	}

	@Override
	public void opslaan()
	{
		itemsOpslaan();
		klantenOpslaan();
		uitleningenOpslaan();
	}

	public static void itemsOpslaan()
	{
		try {
			PrintWriter OpslaanInBestand = new PrintWriter("items.txt");

			for (int n = 0; n < ItemLijst.getItems().size(); n++) {
				OpslaanInBestand.println(ItemLijst.getItemObvIdx(n));
			}
			OpslaanInBestand.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void itemsLezen()
	{
		File file = new File("items.txt");
		Scanner scanner = null;

		if (!file.exists()) {
			return;
		}

		try {
			scanner = new Scanner(file);
			Item item;
			String[] itemData;

			while (scanner.hasNext()) {
				String lijn = scanner.nextLine();

				// Leeg bestand
				if (lijn.isEmpty()) {
					break;
				}

				itemData = lijn.split(" - ");
				
				//TODO: veranderen naar factory DP?
				switch(itemData[0]) {
					case "CD":
					case "Film":
					case "Spel":
						//TODO: Enkel items moeten worden opgeslagen, zonder uitleendetails
						Class<?> klasse = Class.forName("model." + itemData[0]);
						Constructor<?> cons = klasse.getConstructor(String.class, UUID.class);
						Object itemObject = cons.newInstance(itemData[2], UUID.fromString(itemData[1]));
						//System.out.println(itemObject.toString());
						break;
					default:
						throw new Exception("Dit type item is niet bestaande: '" + itemData[0] + "'");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
	
	public static void klantenOpslaan()
	{
		try {
			PrintWriter OpslaanInBestand = new PrintWriter("klanten.txt");

			for (int n = 0; n < KlantenRegister.getKlanten().size(); n++) {
				OpslaanInBestand.println(KlantenRegister.getKlanten().get(n));
			}
			OpslaanInBestand.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void klantenLezen()
	{
		File file = new File("klanten.txt");
		Scanner scanner = null;

		if (!file.exists()) {
			return;
		}

		try {
			scanner = new Scanner(file);
			String[] klantData;

			while (scanner.hasNext()) {
				String lijn = scanner.nextLine();

				// Leeg bestand
				if (lijn.isEmpty()) {
					break;
				}

				klantData = lijn.split(" \\| ");
				
				Adres adres = new Adres();
				adres.setEmail(klantData[2]);
				adres.setStraat(klantData[5]);
				adres.setNummer(Integer.parseInt(klantData[6]));
				adres.setPostcode(Integer.parseInt(klantData[7]));
				adres.setGemeente(klantData[8]);
				
				// Wordt ook autom. toegevoegd aan het register in de klasse zelf
				new Klant(Integer.parseInt(klantData[1]), klantData[3], klantData[4], adres);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
	
	public static void uitleningenOpslaan()
	{
		try {
			PrintWriter OpslaanInBestand = new PrintWriter("uitleningen.txt");

			for (int n = 0; n < UitleningenRegister.getUitleningen().size(); n++) {
				OpslaanInBestand.println(UitleningenRegister.getUitleningen().get(n));
			}
			OpslaanInBestand.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void uitleningenLezen()
	{
		File file = new File("uitleningen.txt");
		Scanner scanner = null;

		if (!file.exists()) {
			return;
		}

		try {
			scanner = new Scanner(file);
			String[] uitleningData;

			while (scanner.hasNext()) {
				String lijn = scanner.nextLine();

				// Leeg bestand
				if (lijn.isEmpty()) {
					break;
				}

				uitleningData = lijn.split(" \\| ");
				
				// Wordt ook autom. toegevoegd aan het register in de klasse zelf
				new Uitlening(Integer.parseInt(uitleningData[1]), UUID.fromString(uitleningData[2]), 
						Integer.parseInt(uitleningData[3]), uitleningData[4]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
