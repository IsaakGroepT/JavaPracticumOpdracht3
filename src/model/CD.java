package model;

import java.util.UUID;

public class CD extends Item {
	
	private final double prijs = 1.5;
	
	public CD(String titel)
	{
		super(titel);
	}
	
	public CD(String titel, UUID id)
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
	 * Geeft de prijs voor het item terug berekend voor het aantal dagen. 
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
	
/*	@Override
	public int getMinimumLeenPeriode()
	{
		return 1;
	}*/
}
