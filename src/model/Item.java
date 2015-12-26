package model;

public interface Item {

	int id = 0;
	String titel = "";
	boolean uitgeleend = false;
	double prijs = 0;
	
	public int getId();
	public void setId();
	
	public String getTitel();
	public void setTitel();
	
	public boolean getUitgeleend();
	public void setUitgeleend();
	
	public double getPrijs();
	public void setPrijs();
}
