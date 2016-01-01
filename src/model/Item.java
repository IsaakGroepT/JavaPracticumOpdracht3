package model;

import java.util.UUID;

import db.ItemLijst;

public abstract class Item {

	private UUID id;
	private String titel;
	private boolean uitgeleend = false;
	// Prijs wordt in de subklasse gespecifieerd
	private double prijs;
	
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
		
		ItemLijst.itemToevoegen(this);
	}
	
	/**
	 * Constructor die wordt gebruikt om gelezen items uit het bestand terug te creëren als objecten
	 */
	public Item(String titel, UUID id)
	{
		this.id = id;
		this.titel = titel;
		uitgeleend = true;
		
		ItemLijst.itemToevoegen(this);
	}
	
	public UUID getID()
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
	 * Converteer data naar een string dat we later in het tekstbestand kunnen opslaan
	 */
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + ";" + id + ";" + titel + ";uitgeleend: " + (uitgeleend ? "ja" : "nee");
	}
	
	/**
	 * Sommige items hebben een minimumperiode voor de prijs die ze betalen
	 */
	public abstract int getMinimumLeenPeriode();
	
	/**
	 * Geeft de prijs terug bij het te laat terugbrengen
	 * 
	 * @return
	 */
	public abstract double getPrijs();

	/**
	 * Deze moet geimplementeerd worden in de subklasse om de prijs juist te weergeven voor dat
	 * specifieke item en het aantal dagen
	 * 
	 * @param dagen
	 * @return
	 */
	public abstract double getPrijs(int dagen);
}
