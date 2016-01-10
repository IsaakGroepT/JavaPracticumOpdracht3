package model;

import java.util.concurrent.atomic.AtomicInteger;

import db.KlantenRegister;

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
