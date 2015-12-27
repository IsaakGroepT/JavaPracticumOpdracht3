package model;

import java.util.UUID;

public class Spel extends Item {
	
	final double prijs = 3;
	
	public Spel(String titel)
	{
		super(titel);
	}
	
	public Spel(String titel, UUID id)
	{
		super(titel, id);
	}
	
	/**
	 * Toevoeging van de prijs aan de originele toString() methode
	 */
	@Override
	public String toString()
	{
		return super.toString() + ";" + prijs;
	}
}
