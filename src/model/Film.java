package model;

import java.util.UUID;

public class Film extends Item {
	
	/*
	 * Dus komt neer op: 5 euro voor drie dagen, voor elke dag daarna 2 euro per dag
	 */
	private final double prijs = 5;
	private final int dagenBasisprijsGeldig = 3;
	private final double prijsPerDagNaInbegrepenDagen = 2;
	
	public Film(String titel)
	{
		super(titel);
	}
	
	public Film(String titel, UUID id)
	{
		super(titel, id);
	}
	
	/**
	 * Sommige items hebben een minimumperiode voor de prijs die ze betalen
	 * 
	 * @return
	 */
//	public int getMinimumLeenPeriode()
//	{
//		return dagenBasisprijsGeldig;
//	}
	
	/**
	 * Geeft gewoon de prijs terug. In dit geval (item is een film) wordt de prijs per dag
	 * na 3 dagen gebruikt. Standaard zitten altijd 3 dagen inbegrepen voor een prijs van 5 euro.
	 * Dus om de boete te berekenen moet eerst bekeken worden of het leentermijn > 3
	 */
	@Override
	public double getPrijs()
	{
		return prijsPerDagNaInbegrepenDagen;
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
		return super.toString() + " - " + prijs;
	}
}
