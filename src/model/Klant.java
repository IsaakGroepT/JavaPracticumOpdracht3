package model;

import java.util.concurrent.atomic.AtomicInteger;

import db.KlantenRegister;

public class Klant {
	private String voornaam;
	private String achternaam;
	private Adres adres;
	private static AtomicInteger uniekeID = new AtomicInteger(); 
	private final int ID;
	
	public Klant(String voornaam, String achternaam, Adres adres)
	{
		ID = uniekeID.incrementAndGet();
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.adres = adres;
		
		KlantenRegister.klantToevoegen(this);
	}
	
	/**
	 * Constructor die wordt gebruikt om gelezen klanten uit het bestand terug te creëren als objecten
	 * 
	 * @return
	 */
	public Klant(Integer ID, String voornaam, String achternaam, Adres adres)
	{
		this.ID = ID;
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
				getClass().getSimpleName(), ID, adres.getEmail(), voornaam, achternaam, adres.getStraat(), adres.getNummer(), 
				adres.getPostcode(), adres.getGemeente());
	}
}
