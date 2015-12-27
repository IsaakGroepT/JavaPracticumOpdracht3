package db;

import java.util.List;
import model.Item;

public class Uitleenlijst {

	static List<Item> items;
	
	/**
	 * 
	 */
	public static List<Item> getItems()
	{
		return items;
	}
	
	/**
	 * Haal de item uit de lijst
	 * 
	 * @param idx
	 * @return
	 */
	public static Item getItem(int idx)
	{
		return items.get(idx);
	}
	
	/**
	 * Voeg eentje toe aan de lijst
	 * 
	 * @param item
	 */
	public static void addItem(Item item)
	{
		items.add(item);
	}
}
