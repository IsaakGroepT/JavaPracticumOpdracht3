package model;

import java.util.UUID;

public class Spel implements Item {
	
	final int id;
	final double prijs = 3;
	final String titel;
	boolean uitgeleend = false;
	
	/**
	 * Constructor
	 */
	public Spel(String titel)
	{
		id = UUID.randomUUID();
		this.titel = titel;
	}

	/**
	 * 
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * 
	 */
	public String getTitel()
	{
		return titel;
	}

	/**
	 * 
	 */
	public boolean getUitgeleend()
	{
		return uitgeleend;
	}

	/**
	 * 
	 */
	public void setUitgeleend(boolean isUitgeleend)
	{
		uitgeleend = isUitgeleend;
	}

	/**
	 * 
	 */
	public double getPrijs()
	{
		return prijs;
	}
}
