package model;

public class CD implements Item {
	
	final int id;
	final double prijs = 5;
	final String titel;
	boolean uitgeleend = false;
	
	/**
	 * Constructor:
	 */
	public CD(String titel)
	{
		id = 1;
		this.titel = titel;
	}

	@Override
	public int getId()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId()
	{
		// TODO Auto-generated method stub
		
	}

	public String getTitel()
	{
		return titel;
	}

	@Override
	public void setTitel()
	{
		// TODO Auto-generated method stub
		
	}

	public boolean getUitgeleend()
	{
		return uitgeleend;
	}

	@Override
	public void setUitgeleend()
	{
		// TODO Auto-generated method stub
		
	}

	public double getPrijs()
	{
		return prijs;
	}

	@Override
	public void setPrijs()
	{
		// TODO Auto-generated method stub
		
	}

}
