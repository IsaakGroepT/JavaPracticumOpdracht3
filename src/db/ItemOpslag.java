package db;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.Scanner;
import java.util.UUID;

import model.CD;
import model.Film;
import model.Item;
import model.Spel;

/**
 * De klasse die voor de opslag van de items in een bestand zorgt
 */
@SuppressWarnings("unused")
public class ItemOpslag {

	/**
	 * 
	 */
	public static void opslaan()
	{
		try {
			PrintWriter OpslaanInBestand = new PrintWriter("items.txt");

			for (int n = 0; n < ItemLijst.getItems().size(); n++) {
				OpslaanInBestand.println(ItemLijst.getItemObvIdx(n));
			}
			OpslaanInBestand.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * 
	 */
	public static void lezen()
	{
		File file = new File("items.txt");
		Scanner scanner = null;

		if (!file.exists()) {
			return;
		}

		try {
			scanner = new Scanner(file);
			Item item;
			String[] itemData;

			while (scanner.hasNext()) {
				String lijn = scanner.nextLine();

				// Leeg bestand
				if (lijn.isEmpty()) {
					break;
				}

				itemData = lijn.split(";");
				
				//TODO: veranderen naar factory DP?
				switch(itemData[0]) {
					case "CD":
					case "Film":
					case "Spel":
						//TODO: Enkel items moeten worden opgeslagen, zonder uitleendetails
						Class<?> klasse = Class.forName("model." + itemData[0]);
						Constructor<?> cons = klasse.getConstructor(String.class, UUID.class);
						Object itemObject = cons.newInstance(itemData[2], UUID.fromString(itemData[1]));
						//System.out.println(itemObject.toString());
						break;
					default:
						throw new Exception("Dit type item is niet bestaande: '" + itemData[0] + "'");
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			scanner.close();
		}
	}
}
