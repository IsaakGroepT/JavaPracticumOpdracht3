package controller;

import db.ItemLijst;
import model.CD;
import model.Film;
import model.Spel;

public class Winkel {

	public static void main(String[] args) {
		/* 
		 * Eerst items aanmaken zonder deze al per type te groeperen om later de sort functie te
		 * controleren
		 */
		CD cd1 = new CD("Metallica");
		ItemLijst.addItem(cd1);
		Film film1 = new Film("Scary movie");
		ItemLijst.addItem(film1);
		Spel spel1 = new Spel("Gtz");
		ItemLijst.addItem(spel1);
		CD cd2 = new CD("Petallica2");
		ItemLijst.addItem(cd2);
		CD cd3 = new CD("Netallica3");
		ItemLijst.addItem(cd3);
		
		Film film2 = new Film("Scary movie2");
		ItemLijst.addItem(film2);
		Film film3 = new Film("Xcary movie3");
		ItemLijst.addItem(film3);
		
		Spel spel2 = new Spel("gta2");
		ItemLijst.addItem(spel2);
		Spel spel3 = new Spel("Bta3");
		ItemLijst.addItem(spel3);
		
		System.out.println(ItemLijst.getItemsGeformateerd());
	}
}
