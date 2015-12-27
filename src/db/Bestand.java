package db;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import application.Registration;
import model.datum.Datum;

public class Bestand {

	/**
	 * 
	 */
	public void opslaan()
	{
        try {
			PrintWriter OpslaanInBestand = new PrintWriter("items.txt");
	        
	        for (int n = 0; n < Uitleenlijst.getItems().size(); n++)
	        {
	        	OpslaanInBestand.println(registrations.get(n).formaatVoorBestandLijn());
	        }
	        OpslaanInBestand.close();
	        
	        return true;
        }
        catch (IOException e) {
            System.out.println(e.toString());
            return false;
        }
	}
	
	/**
	 * 
	 */
	public void lezen()
	{
		  File file = new File("items.txt");
		  
		  if (!file.exists())
		  {
			  return;
		  }
		  
		  try {
			Scanner scanner = new Scanner(file);
			
			while (scanner.hasNext())
			{
				String lijn = scanner.nextLine();
				
				// Leeg bestand
				if (lijn.isEmpty())
				{
					break;
				}
				
							
			}
			
			if (scanner != null)
			{
				scanner.close();
			}
		  }
		  catch(Exception e) {
			  System.out.println(e.getMessage());
		  }
	}
}
