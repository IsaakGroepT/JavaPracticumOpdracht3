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
package model;

import java.util.UUID;

import db.ItemLijst;

/**
 * @author Isaak Malik
 */
public abstract class Item {

	private UUID id;
	private String titel;
	private boolean uitgeleend = false;
	// Prijs wordt in de subklasse gespecifieerd
	@SuppressWarnings("unused")
	private double prijs;
	private double boete = 3;
	
	/**
	 * Expliciete default constructor die enkel bestaat om een compile error te voorkomen,
	 * mag dus niet gebruikt worden
	 */
	public Item()
	{
		throw new UnsupportedOperationException("De Item object moet geinstantieerd worden met een titel parameter");
	}
	
	/**
	 * Constructor met titel paramater
	 */
	public Item(String titel)
	{
		id = UUID.randomUUID();
		this.titel = titel;
		
		ItemLijst.itemToevoegen(this);
	}
	
	/**
	 * Constructor die wordt gebruikt om gelezen items uit de opslag terug te creeren als objecten
	 * 
	 * @param titel
	 * @param id
	 */
	public Item(String titel, UUID id)
	{
		this.id = id;
		this.titel = titel;
		
		ItemLijst.itemToevoegen(this);
	}
	
	public UUID getID()
	{
		return id;
	}
	
	public String getTitel()
	{
		return titel;
	}
	
	public ItemTypes getType()
	{
		return ItemTypes.valueOf(getClass().getSimpleName());
	}
	
	public boolean isUitgeleend()
	{
		return uitgeleend;
	}
	
	/**
	 * String versie voor tabellen
	 * @return
	 */
	public String isUitgeleendString()
	{
		return uitgeleend ? "Ja" : "Nee";
	}

	public void setUitgeleend(boolean isUitgeleend)
	{
		uitgeleend = isUitgeleend;
	}
	
	/**
	 * Converteer data naar een string dat we later in het tekstbestand kunnen opslaan
	 */
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + " - " + id + " - " + titel + " - uitgeleend: " + (uitgeleend ? "ja" : "nee");
	}
	
	public double getBoete()
	{
		return boete;
	}
	
	/**
	 * Sommige items hebben een minimumperiode voor de prijs die ze betalen
	 */
	//public abstract int getMinimumLeenPeriode();
	
	/**
	 * Geeft de prijs terug bij het te laat terugbrengen
	 * 
	 * @return
	 */
	public abstract double getPrijs();

	/**
	 * Deze moet geimplementeerd worden in de subklasse om de prijs juist te weergeven voor dat
	 * specifieke item en het aantal dagen
	 * 
	 * @param dagen
	 * @return
	 */
	public abstract double getPrijs(int dagen);
}
