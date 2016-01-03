package db;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Item;
import model.Film;

public class Uitleningen {
	private static List<Uitlening> uitleningen = new ArrayList<Uitlening>();
	
	/**
	 * Registreer een nieuwe uitlening
	 *  
	 * @param klant
	 * @param item
	 * @param dagen
	 * @return De totale prijs van de uitlening
	 */
	public static double nieuweUitlening(int klant, UUID item, int dagen)
	{
		try {
			uitleningen.add(new Uitlening(klant, item, dagen));
		} catch (Exception e) {
			System.out.println(e.toString());
			//e.printStackTrace();
		}
		
		return ItemLijst.getItemObvID(item).getPrijs(dagen);
	}
	
	/**
	 * Item werd terug binnengebracht: zoekt op item ID in de lijst en verwijdert
	 * de gevonden rij. Geeft boete of 0 terug
	 * 
	 * @param item
	 */
	public static double uitleningStoppen(UUID item)
	{
		double kost;
		
		try {
			Item itemObject = ItemLijst.getItemObvID(item);
			Uitlening uitlening = uitleningen.stream()
														.filter(u -> u.getItemID() == item)
														.findFirst()
														.get();
			if (uitlening.isTeLaat()) {
				/*// Film heeft een minimum uitleenperiode die inbegrepen is in de prijs
				if (itemObject instanceof Film) {
					if (uitlening.getAantalDagen() > itemObject.getMinimumLeenPeriode()) {
						kost = itemObject.getPrijs() * uitlening.getAantalDagenTeLaat();
					}
				}
				else {
					kost = itemObject.getPrijs() * uitlening.getAantalDagenTeLaat();
				}*/
				kost = itemObject.getBoete() * uitlening.getAantalDagenTeLaat();
				
				uitleningen.remove(uitlening);
				
				return kost;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			//e.printStackTrace();
		}
		// Was niet te laat binnengebracht
		return 0;
	}
}
