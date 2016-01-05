package db;

import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.Uitlening;

public class UitleningenRegister {
	private static ObservableList<Uitlening> uitleningen = FXCollections.observableArrayList();
	
	public static ObservableList<Uitlening> getUitleningen()
	{
		return uitleningen;
	}
	
	/**
	 * Item werd terug binnengebracht: zoekt op item ID in de lijst en verwijdert
	 * de gevonden rij. Geeft boete of 0 terug
	 * 
	 * @param item
	 * @throws Exception 
	 */
	public static double uitleningStoppen(UUID item) throws Exception
	{
		double kost = 0;
		
		Item itemObject = ItemLijst.getItemObvID(item);
		Uitlening uitlening = uitleningen.stream()
													.filter(u -> u.getItemID() == item)
													.findFirst()
													.get();

		// Te laat binnen dus bereken boete
		if (uitlening.isTeLaat()) {
			kost = itemObject.getBoete() * uitlening.getAantalDagenTeLaat();
		}

		itemObject.setUitgeleend(false);
		uitleningen.remove(uitlening);
		// 0 of kost van boete
		return kost;
	}
	
	/**
	 * Wordt gebruikt in Uitlening klasse
	 * @param uitlening
	 */
	public static void nieuweUitlening(Uitlening uitlening)
	{
		uitleningen.add(uitlening);
	}
}
