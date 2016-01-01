package model;

import java.util.UUID;

public class Spel extends Item {
	
	private final double prijs = 3;
	
	public Spel(String titel)
	{
		super(titel);
	}
	
	public Spel(String titel, UUID id)
	{
		super(titel, id);
	}
	
	/**
	 * Geeft gewoon de prijs terug
	 */
	@Override
	public double getPrijs()
	{
		return prijs;
	}
	
	/**
	 * Geeft de prijs voor het item terug. 
	 * 
	 * @param aantalDagen
	 * @return
	 */
	@Override
	public double getPrijs(int aantalDagen)
	{
		return prijs * aantalDagen;
	}
	
	/**
	 * Toevoeging van de prijs aan de originele toString() methode
	 */
	@Override
	public String toString()
	{
		return super.toString() + ";" + prijs;
	}

	@Override
	public int getMinimumLeenPeriode()
	{
		return 1;
	}
}
