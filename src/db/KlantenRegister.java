package db;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Klant;

public class KlantenRegister {

	private static ObservableList<Klant> klanten = FXCollections.observableArrayList();
	
	public static ObservableList<Klant> getKlanten()
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
	 * Maak een lijst van alle klanten IDs
	 * 
	 * @return
	 */
	public static List<Integer> getKlantIDs()
	{
		return klanten.stream().map(Klant::getID).collect(Collectors.toList());
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
