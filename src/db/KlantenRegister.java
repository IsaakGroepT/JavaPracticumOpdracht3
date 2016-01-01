package db;

import java.util.ArrayList;
import java.util.List;

public class KlantenRegister {

	private static List<Klant> klanten = new ArrayList<Klant>();
	
	public static List<Klant> getKlanten()
	{
		return klanten;
	}
	
	/**
	 * Haal de Klant op obv id, null als niet bestaande
	 * 
	 * @param klantID
	 * @return Klant|null
	 */
	public static Klant getKlantObvID(int klantID)
	{
		return klanten.stream().filter(k -> k.getID() == klantID).findFirst().get();
	}
	
	/**
	 * Klant toevoegen aan lijst
	 * 
	 * @param item
	 */
	public static void klantToevoegen(Klant klant)
	{
		klanten.add(klant);
	}
}
