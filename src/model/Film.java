package model;

import java.util.UUID;

public class Film extends Item {
	
	final double prijs = 5;
	final int dagenBasisprijsGeldig = 3;
	final double prijsPerDagNaInbegrepenDagen = 2;
	
	public Film(String titel)
	{
		super(titel);
	}
	
	public Film(String titel, UUID id)
	{
		super(titel, id);
	}
	
	/**
	 * Aangepaste getPrijs() methode voor deze subklasse
	 */
	@Override
	public double getPrijs(int aantalDagen)
	{
		double prijs = this.prijs;
		
		int dagenOver = aantalDagen - dagenBasisprijsGeldig;
		
		if (dagenOver > 0) {
			prijs += dagenOver * prijsPerDagNaInbegrepenDagen;
		}
		
		return prijs;
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
