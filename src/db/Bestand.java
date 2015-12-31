package db;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.UUID;

import model.CD;
import model.Film;
import model.Item;
import model.Spel;

public class Bestand {

	/**
	 * 
	 */
	public void opslaan()
	{
		try {
			PrintWriter OpslaanInBestand = new PrintWriter("items.txt");

			for (int n = 0; n < ItemLijst.getItems().size(); n++) {
				OpslaanInBestand.println(ItemLijst.getItem(n));
			}
			OpslaanInBestand.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * 
	 */
	public void lezen()
	{
		File file = new File("items.txt");
		Scanner scanner = null;

		if (!file.exists()) {
			return;
		}

		try {
			scanner = new Scanner(file);
			Item item;

			while (scanner.hasNext()) {
				String lijn = scanner.nextLine();

				// Leeg bestand
				if (lijn.isEmpty()) {
					break;
				}

				String[] itemData = lijn.split(";");
				
				//TODO: veranderen naar factory DP?
				if (itemData[0] == "CD") {
					item = new CD(itemData[2], UUID.fromString(itemData[1]));
				}
				else if (itemData[0] == "Film") {
					item = new Film(itemData[2], UUID.fromString(itemData[1]));
				}
				else if (itemData[0] == "Spel") {
					item = new Spel(itemData[2], UUID.fromString(itemData[1]));
				}
				else {
					throw new Exception("Dit type item is niet bestaande");
				}
				ItemLijst.addItem(item);
			}

			if (scanner != null) {
				scanner.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			scanner.close();
		}
	}
}
