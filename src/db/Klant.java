package db;

import java.util.concurrent.atomic.AtomicInteger;

import model.Adres;

public class Klant {
	private String naam;
	private Adres adres;
	private static AtomicInteger uniekeID = new AtomicInteger(); 
	private final int ID;
	
	public Klant(String naam, Adres adres)
	{
		ID = uniekeID.incrementAndGet();
		this.naam = naam;
		this.adres = adres;
		
		KlantenRegister.klantToevoegen(this);
	}

	public String getNaam()
	{
		return naam;
	}

	public void setNaam(String naam)
	{
		this.naam = naam;
	}

	public Adres getAdres()
	{
		return adres;
	}

	public void setAdres(Adres adres)
	{
		this.adres = adres;
	}

	public int getID()
	{
		return ID;
	}
}
