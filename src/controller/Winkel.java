package controller;

import db.ItemLijst;
import db.Klant;
import db.Uitleningen;
import model.Adres;
import model.CD;
import model.Film;
import model.Item;
import model.ItemTypes;
import model.Spel;

public class Winkel {
	
	static Klant klant1;
	static Klant klant2;
	static CD cd1;
	static CD cd2;
	static CD cd3;
	static Film film1;
	static Film film2;
	static Film film3;
	static Spel spel1;
	static Spel spel2;
	static Spel spel3;
	
	
	public static void main(String[] args)
	{
		/* 
		 * Eerst items aanmaken zonder deze al per type te groeperen om later de sort functie te
		 * controleren
		 */
		cd1 = new CD("Metallica");
		film1 = new Film("Scary movie");
		spel1 = new Spel("Gtz");
		cd2 = new CD("Petallica2");
		cd3 = new CD("Netallica3");
		film2 = new Film("Scary movie2");
		film3 = new Film("Xcary movie3");
		spel2 = new Spel("gta2");
		spel3 = new Spel("Bta3");
		
		Adres adres1 = new Adres();
		Adres adres2 = new Adres();
		
		adres1.setStreet("polstraat");
		adres1.setNumber(15);
		adres1.setPostcode(1000);
		adres1.setCity("Bxl");
		adres1.setEmail("joske@mail.com");
		
		adres2.setStreet("polstraat");
		adres2.setNumber(15);
		adres2.setPostcode(1000);
		adres2.setCity("Bxl");
		adres2.setEmail("dencoolepatte@mail.com");
		
		// Klanten
		klant1 = new Klant("Joske V.", adres1);
		klant2 = new Klant("Patrick J.", adres2);
				
		// Test 1
		//voorbeeldGeformateerdeItems();
		
		// Test 2
		//voorbeeldZoekenOpKernwoord();
		
		// Test 3
		voorbeeldUitlening();
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
		for (Item item : ItemLijst.zoekItemsOpDeelString("alli", ItemTypes.TYPE_ALL)) {
			System.out.println(item);
		}
	}
	
	/**
	 * Een uitlening simuleren
	 */
	private static void voorbeeldUitlening()
	{
		System.out.println(Uitleningen.nieuweUitlening(klant1.getID(), film1.getID(), 4));
		System.out.println(Uitleningen.nieuweUitlening(klant2.getID(), cd1.getID(), 3));
	}
}
