package controller;

import db.ItemOpslag;
import db.ItemLijst;
import db.Klant;
import db.Uitleningen;
import model.Adres;
import model.CD;
import model.Film;
import model.Item;
import model.ItemTypes;
import model.Spel;
import view.Main;

public class Winkel {
	
	private static Klant klant1;
	private static Klant klant2;
	
	
	public static void main(String[] args)
	{	
		/* 
		 * Eerst items aanmaken zonder deze al per type te groeperen om later de sort functie te
		 * controleren
		 */
		// Hebben we al opgeslagen gegevens?
		ItemOpslag.lezen();
		
		// Zoiet, aanmaken!
		if (ItemLijst.getItems().isEmpty()) {
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
		ItemLijst.getItems().get(0).setUitgeleend(true);
		
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
		klant1 = new Klant("Joske", "Vermeulen", adres1);
		klant2 = new Klant("Patrick", "Pol", adres2);
				
		// Test 1
		//voorbeeldGeformateerdeItems();
		
		// Test 2
		//voorbeeldZoekenOpKernwoord();
		
		// Test 3
		//voorbeeldUitlening();
		
		// Start de JavaFX GUI
		Main.launch(Main.class, args);
		
		// 't Is gedaan!
		programmaExit();
	}
	
	/**
	 * Geformateerde resultaten
	 */
	private static void voorbeeldGeformateerdeItems()
	{
		System.out.println(ItemLijst.getItemsGeformateerd());
	}
	
	/**
	 * Zoeken op basis van een gegeven (deel van een) woord.
	 */
	private static void voorbeeldZoekenOpKernwoord()
	{
		for (Item item : ItemLijst.zoekItemsOpDeelString("alli", null)) {
			System.out.println(item);
		}
	}
	
	/**
	 * Een uitlening simuleren
	 */
	private static void voorbeeldUitlening()
	{
		Film film1 = (Film) ItemLijst.getItemObvIdx(1);
		CD cd1 = (CD) ItemLijst.getItemObvIdx(0);
		
		System.out.println(Uitleningen.nieuweUitlening(klant1.getID(), film1.getID(), 4));
		// cd1
		System.out.println(Uitleningen.nieuweUitlening(klant2.getID(), cd1.getID(), 3));
		
		double boete = Uitleningen.uitleningStoppen(film1.getID());
		
		if (boete > 0) {
			System.out.println("Het is te laat binnengebracht! Boete: " + boete + " euro");
		}
		else {
			System.out.println("Er is geen boete want was op tijd terug");
		}
	}
	
	/**
	 * Taken die moeten uitgevoerd worden op het einde
	 */
	private static void programmaExit()
	{
		ItemOpslag.opslaan();
	}
}
