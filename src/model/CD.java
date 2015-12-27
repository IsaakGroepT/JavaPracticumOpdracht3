package model;

import java.util.UUID;

public class CD extends Item {
	
	final double prijs = 1.5;
	
	public CD(String titel)
	{
		super(titel);
	}
	
	public CD(String titel, UUID id)
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
