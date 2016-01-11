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
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Klant;

/**
 * @author Isaak Malik
 */
public class KlantenRegister {
	private static int ID = 0;

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
	
	/**
	 * Vergelijk gegeven ID met de huidige en indien groter gebruik deze dan ook
	 */
	public static void setHoogsteID(int nieuweID)
	{
		if (nieuweID > ID) {
			ID = nieuweID;
		}
	}
	
	/**
	 * 
	 * @return 
	 */
	public static int getNieuweID()
	{
		return ++ID;
	}
}
