package db;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.UUID;

import model.Uitlening;

public class UitleningenOpslag {

	public static void opslaan()
	{
		try {
			PrintWriter OpslaanInBestand = new PrintWriter("uitleningen.txt");

			for (int n = 0; n < UitleningenRegister.getUitleningen().size(); n++) {
				OpslaanInBestand.println(UitleningenRegister.getUitleningen().get(n));
			}
			OpslaanInBestand.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void lezen()
	{
		File file = new File("uitleningen.txt");
		Scanner scanner = null;

		if (!file.exists()) {
			return;
		}

		try {
			scanner = new Scanner(file);
			String[] uitleningData;

			while (scanner.hasNext()) {
				String lijn = scanner.nextLine();

				// Leeg bestand
				if (lijn.isEmpty()) {
					break;
				}

				uitleningData = lijn.split(" \\| ");
				
				// Wordt ook autom. toegevoegd aan het register in de klasse zelf
				new Uitlening(Integer.parseInt(uitleningData[1]), UUID.fromString(uitleningData[2]), 
						Integer.parseInt(uitleningData[3]), uitleningData[4]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}
