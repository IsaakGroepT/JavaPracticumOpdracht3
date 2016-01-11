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
import db.UitleningenRegister;

/**
 * Deze klasse linkt alle gegevens van de uitlening aan mekaar
 * 
 * @author Isaak Malik
 */
public class Uitlening {
	private UUID item;
	private int klant;
	private int aantalDagen;
	private Datum startDatum;
	private Datum eindDatum;
	
	public Uitlening(int klantID, UUID itemID, int dagen) throws Exception
	{
		item = itemID;
		klant = klantID;
		aantalDagen = dagen;
		
		startDatum = new Datum();
		eindDatum = new Datum();
		eindDatum.veranderDatum(dagen);
		
		// Item wordt onbeschikbaar
		ItemLijst.getItemObvID(itemID).setUitgeleend(true);
		
		UitleningenRegister.nieuweUitlening(this);
	}
	
	/**
	 * Constructor die wordt gebruikt om gelezen uitlenigen uit de opslag terug te creeren 
	 * als objecten
	 * 
	 * @param klantID
	 * @param itemID
	 * @param dagen
	 * @throws Exception
	 */
	public Uitlening(int klantID, UUID itemID, int dagen, String startDatum) throws Exception
	{
		item = itemID;
		klant = klantID;
		aantalDagen = dagen;
		
		this.startDatum = new Datum(startDatum);
		eindDatum = new Datum(startDatum);
		eindDatum.veranderDatum(dagen);
		
		// Item wordt onbeschikbaar
		ItemLijst.getItemObvID(itemID).setUitgeleend(true);
		
		UitleningenRegister.nieuweUitlening(this);
	} 

	public UUID getItemID()
	{
		return item;
	}

	public int getKlantID()
	{
		return klant;
	}

	public int getAantalDagen()
	{
		return aantalDagen;
	}
	
	public void setAantalDagen(int dagen)
	{
		aantalDagen = dagen;
	}
	
	/**
	 * Is gewoon een verwijzing met de getPrijs van de Item (sub)klassen
	 * 
	 * @return Prijs van de uitlening
	 */
	public double getPrijs()
	{
		return ItemLijst.getItemObvID(item).getPrijs(aantalDagen);
	}

	public Datum getStartDatum()
	{
		return startDatum;
	}

	public Datum getEindDatum()
	{
		return eindDatum;
	}
	
	/**
	 * Het aantal dagen van de uitlening is aangepast, dus we moeten de einddatum ook veranderen
	 * 
	 * @param dagen
	 * @throws Exception 
	 */
	public void veranderEindDatum(int dagen) throws Exception
	{
		eindDatum = new Datum(startDatum);
		eindDatum.veranderDatum(dagen);
	}
	
	/**
	 * Deze methode is safe omdat startDatum niet hoger kan zijn dan de huidige datum. Dit
	 * is altijd de datum de dag waarop de uitlening werd aangemaakt in het register.
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean isTeLaat() throws Exception
	{
		return new DatumVerschil(startDatum, new Datum()).getDagen() > aantalDagen;
	}
	
	/**
	 * Deze methode heeft enkel nut als eerst wordt gecheckt met isTeLaat() omdat deze methode
	 * ook een positief getal teruggeeft als eindDatum > huidige datum. Dan is de uitlening nog niet
	 * te laat
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getAantalDagenTeLaat() throws Exception
	{
		return new DatumVerschil(eindDatum, new Datum()).getDagen();
	}
	
	/**
	 * Converteer data naar een string dat we later in het tekstbestand kunnen opslaan
	 */
	@Override
	public String toString()
	{
		return String.format("%s | %d | %s | %d | %s", 
				getClass().getSimpleName(), klant, item, aantalDagen, startDatum);
	}
}
