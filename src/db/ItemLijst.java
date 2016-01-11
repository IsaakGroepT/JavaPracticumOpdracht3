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
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.ItemTypes;

/**
 * @author Isaak Malik
 */
public class ItemLijst {

	static ObservableList<Item> items = FXCollections.observableArrayList();
	
	/**
	 * Geeft gewoon de array met items terug
	 * @return
	 */
	public static ObservableList<Item> getItems()
	{
		return items;
	}
	
	/**
	 * Geeft alle items geformateerd en gestorteerd terug. Maakt gebruik van Java 8 Streams
	 * 
	 * @return
	 */
	public static String getItemsGeformateerd()
	{
		String output = "";
		
		Map<Object, List<Item>> itemMap = items.stream()
															.sorted((i1, i2) -> i1.getTitel().compareTo(i2.getTitel()))
															.collect(Collectors.groupingBy(i -> i.getClass().getSimpleName()));
		
		for (List<Item> itemLijst : itemMap.values()) {
			for (Item item : itemLijst) {
				output += item + System.lineSeparator();
			}
		}
		
		return output;
	}
	
	/**
	 * Zoeken op basis van de gegeven string en eventueel gelimiteerd tot de gegeven type
	 * 
	 * @param zoekwoord
	 * @param type
	 * @return
	 */
	public static List<Item> zoekItemsOpDeelString(String zoekwoord, ItemTypes type)
	{
		return items.stream()
						.filter(i -> i.getTitel().toLowerCase().contains(zoekwoord.toLowerCase()) 
						&& (type == null ? true : i.getClass().getSimpleName().toLowerCase().equals(type.toString().toLowerCase())))
						.collect(Collectors.toList());
	}
	
	/**
	 * Haal de item uit de lijst obv index nummer
	 * 
	 * @param idx
	 * @return
	 */
	public static Item getItemObvIdx(int idx)
	{
		return items.get(idx);
	}
	
	/**
	 * Haal de Item op obv id, null als niet bestaande
	 * 
	 * @param id
	 * @return Item|null
	 */
	public static Item getItemObvID(UUID id)
	{
		return items.stream().filter(i -> i.getID().compareTo(id) == 0).findFirst().get();
	}
	
	/**
	 * Maak een lijst van alle item IDs die nog niet uitgeleend zijn. Uit deze lijst kan dan gekozen
	 * worden om een uitlening te registreren obv van Item ID zoals de opdracht vereist
	 * 
	 * Vanaf Opdracht 3 deel 2 deprecated
	 * 
	 * @deprecated
	 * @return
	 */
	public static List<UUID> getUitleendbareItemIDs()
	{
		return items.stream().filter(i -> !i.isUitgeleend()).map(Item::getID).collect(Collectors.toList());
	}
	
	/**
	 * Lijst van item namen voor de combobox in de GUI
	 * 
	 * @return
	 */
	public static List<String> getItemTitels()
	{
		return items.stream().map(Item::getTitel).collect(Collectors.toList());
	}
	
	/**
	 * Voeg eentje toe aan de lijst
	 * 
	 * @param item
	 */
	public static void itemToevoegen(Item item)
	{
		items.add(item);
	}
}
