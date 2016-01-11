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

import java.util.concurrent.atomic.AtomicInteger;

import db.KlantenRegister;

/**
 * @author Isaak Malik
 */
public class Klant {
	private final String voornaam;
	private final String achternaam;
	private final Adres adres;
	private int ID;
	
	/**
	 * Voor nieuwe creaties in de broncode zelf
	 * 
	 * @param voornaam
	 * @param achternaam
	 * @param adres 
	 */
	public Klant(String voornaam, String achternaam, Adres adres)
	{
		ID = KlantenRegister.getNieuweID();
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.adres = adres;
		
		KlantenRegister.klantToevoegen(this);
	}
	
	/**
	 * Constructor die wordt gebruikt om gelezen klanten uit de opslag terug te creëren als objecten
	 * 
	 * @param ID
	 * @param voornaam
	 * @param achternaam
	 * @param adres 
	 */
	public Klant(Integer ID, String voornaam, String achternaam, Adres adres)
	{
		this.ID = ID;
		KlantenRegister.setHoogsteID(ID);
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.adres = adres;
		
		KlantenRegister.klantToevoegen(this);
	}

	public String getVoornaam()
	{
		return voornaam;
	}
	
	public String getAchternaam()
	{
		return achternaam;
	}

	public Adres getAdres()
	{
		return adres;
	}

	public int getID()
	{
		return ID;
	}
	
	/**
	 * Converteer data naar een string dat we later in het tekstbestand kunnen opslaan
	 */
	@Override
	public String toString()
	{
		return String.format("%s | %d | %s | %s | %s | %s | %d | %d | %s",
				getClass().getSimpleName(), getID(), adres.getEmail(), voornaam, achternaam, adres.getStraat(), adres.getNummer(), 
				adres.getPostcode(), adres.getGemeente());
	}
}
