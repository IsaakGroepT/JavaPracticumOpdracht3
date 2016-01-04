package db;

import java.util.concurrent.atomic.AtomicInteger;

import model.Adres;

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
}
