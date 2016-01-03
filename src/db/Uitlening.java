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
	private int aantalDagenBetaald;
	private Datum startDatum;
	private Datum eindDatum;
	
	public Uitlening(int klantID, UUID itemID, int dagen) throws Exception
	{
		item = itemID;
		klant = klantID;
		aantalDagenBetaald = dagen;
		
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
		return aantalDagenBetaald;
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
		return new DatumVerschil(startDatum, new Datum()).getDagen() > aantalDagenBetaald;
	}
	
	public int getAantalDagenTeLaat() throws Exception
	{
		return new DatumVerschil(eindDatum, new Datum()).getDagen();
	}
}
