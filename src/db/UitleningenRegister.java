/*
 * The MIT License
 *
 * Copyright 2016 Isaak Malik.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package db;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.Uitlening;

/**
 * @author Isaak Malik
 */
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
	
	public static List<Uitlening> getUitleningenVoorKlant(int klantID)
	{
		return uitleningen.stream()
			.filter(p -> p.getKlantID() == klantID)
			.collect(Collectors.toList());
	}
}
