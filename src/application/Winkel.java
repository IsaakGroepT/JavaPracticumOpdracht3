package application;

import db.KlantenRegister;
import db.UitleningenRegister;
import db.ItemLijst;
import db.OpslagManager;
import db.bestand.BestandOpslagStrategy;
import db.derby.DerbyOpslagStrategy;
import model.Adres;
import model.CD;
import model.Film;
import model.Klant;
import model.Spel;
import model.Uitlening;
import view.Main;

public class Winkel {
	private static OpslagManager db;
	
	public static void main(String[] args)
	{
		db = new OpslagManager();
		db.setStrategy(new DerbyOpslagStrategy());
		// Hebben we al opgeslagen gegevens?
		db.lezen();
		
		/* 
		 * Eerst items aanmaken zonder deze al per type te groeperen om later de sort functie te
		 * controleren
		 */
		
		// Zoniet, aanmaken!
		if (ItemLijst.getItems().isEmpty()) {
			// voorbeeldgegevens
			new CD("Metallica");
			new Film("Scary movie");
			new Spel("Gtz");
			new CD("Petallica2");
			new CD("Netallica3");
			new Film("Scary movie2");
			new Film("Xcary movie3");
			new Spel("gta2");
			new Spel("Bta3");
		} else {
			// Debugging man
			//ItemLijst.getItems().stream().forEach(item -> System.out.println(item));
		}
		
		// Uitgeleend test
		//ItemLijst.getItems().get(0).setUitgeleend(true);
		
		if (KlantenRegister.getKlanten().isEmpty()) {
			// voorbeeldgegevens
			Adres adres1 = new Adres();
			Adres adres2 = new Adres();
			
			adres1.setStraat("pilstraat");
			adres1.setNummer(115);
			adres1.setPostcode(3000);
			adres1.setGemeente("Bxl");
			adres1.setEmail("joske@mail.com");
			
			adres2.setStraat("polstraat");
			adres2.setNummer(15);
			adres2.setPostcode(1000);
			adres2.setGemeente("Bxl");
			adres2.setEmail("dencoolepatte@mail.com");
			
			// Klanten
			new Klant("Joske", "Vermeulen", adres1);
			new Klant("Patrick", "Pol", adres2);
		} else {
			// Debugging man
			//KlantenRegister.getKlanten().stream().forEach(klant -> System.out.println(klant));
		}
		
		if (UitleningenRegister.getUitleningen().isEmpty()) {
			try {
				new Uitlening(KlantenRegister.getKlanten().get(0).getID(), ItemLijst.getItems().get(0).getID(), 5);
				new Uitlening(KlantenRegister.getKlanten().get(1).getID(), ItemLijst.getItems().get(1).getID(), 31);
			} catch (Exception e) {
				System.out.println("Probleem met registreren uitlening in Winkel klasse: " + e.getMessage());
			}
		} else {
			// Debugging man
			//UitleningenRegister.getUitleningen().stream().forEach(klant -> System.out.println(klant));
		}
		
		// Start de JavaFX GUI
		Main.launch(Main.class, args);
		
		// Bij het sluiten van de applicatie wordt dit uitgevoerd
		programmaExit();
	}
	
	/**
	 * Taken die moeten uitgevoerd worden op het einde
	 */
	private static void programmaExit()
	{
		db.opslaan();
		db.close();
	}
}
