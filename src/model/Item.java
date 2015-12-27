package model;

import java.util.UUID;

public abstract class Item {

	UUID id;
	String titel;
	boolean uitgeleend = false;
	double prijs;
	
	/**
	 * Expliciete default constructor die enkel bestaat om een compile error te voorkomen,
	 * mag dus niet gebruikt worden
	 */
	public Item()
	{
		throw new UnsupportedOperationException("De Item object moet geinstantieerd worden met een titel parameter");
	}
	
	/**
	 * Constructor met titel paramater
	 */
	public Item(String titel)
	{
		id = UUID.randomUUID();
		this.titel = titel;
	}
	
	/**
	 * Constructor die wordt gebruikt om gelezen items uit het bestand terug te creëren als objecten
	 */
	public Item(String titel, UUID id)
	{
		this.id = id;
		this.titel = titel;
		uitgeleend = true;
	}
	
	public UUID getId()
	{
		return id;
	}
	
	public String getTitel()
	{
		return titel;
	}
	
	public boolean isUitgeleend()
	{
		return uitgeleend;
	}

	public void setUitgeleend(boolean isUitgeleend)
	{
		uitgeleend = isUitgeleend;
	}
	
	/**
	 * Geeft de prijs voor het item terug. Deze methode wordt aangepast in de subklasse als er een 
	 * specifieke prijsberekening voor nodig is.
	 * 
	 * @see Film
	 * @param aantalDagen
	 * @return
	 */
	//TODO: wordt de prijs juist weergegeven op deze manier of moet deze methode in de subklasse overridden worden
	public double getPrijs(int aantalDagen)
	{
		return prijs;
	}
	
	/**
	 * Converteer data naar een string dat we later in het tekstbestand kunnen opslaan
	 */
	@Override
	public String toString()
	{
		//TODO: geeft getClass() juiste subklasse naam door?
		return getClass().getName() + ";" + id + ";" + titel;
	}
}
