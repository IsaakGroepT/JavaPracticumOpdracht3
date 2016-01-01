package db;

import java.util.UUID;

import model.Datum;
import model.DatumVerschil;

/**
 * Deze klasse linkt alle gegevens van de uitlening aan mekaar
 */
public class Uitlening {
	private UUID item;
	private int klant;
	private int aantalDagen;
	private Datum startDatum;
	private Datum eindDatum;
	
	public Uitlening(int klantID, UUID itemID, int dagen) throws Exception
	{
		item = itemID;
		klant = klantID;
		aantalDagen = dagen;
		
		startDatum = new Datum();
		eindDatum = new Datum();
		eindDatum.veranderDatum(dagen);
	}

	public UUID getItemID()
	{
		return item;
	}

	public int getKlantID()
	{
		return klant;
	}

	public int getAantalDagen()
	{
		return aantalDagen;
	}

	public String getStartDatum()
	{
		return startDatum.getDatumInEuropeesFormaat();
	}

	public String getEindDatum()
	{
		return eindDatum.getDatumInEuropeesFormaat();
	}
	
	public boolean isTeLaat() throws Exception
	{
		return new DatumVerschil(startDatum, eindDatum).getDagen() > aantalDagen;
	}
	
	public int getAantalDagenTeLaat() throws Exception
	{
		return new DatumVerschil(startDatum, eindDatum).getDagen() - aantalDagen;
	}
}
