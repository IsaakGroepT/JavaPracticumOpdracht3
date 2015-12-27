package model;

import java.util.UUID;

public class Film extends Item {
	
	final double prijs = 3;
	
	public Film(String titel)
	{
		super(titel);
	}
	
	public Film(String titel, UUID id)
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
