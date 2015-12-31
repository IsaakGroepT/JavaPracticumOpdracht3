package db;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Item;

public class ItemLijst implements Comparable<Item> {

	static List<Item> items = new ArrayList<Item>();
	
	/**
	 * Geeft gewoon de array met items terug
	 * @return
	 */
	public static List<Item> getItems()
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
	
	/**
	 * Dit is een aangepaste vergelijker om voor correcte sortering van de itemlijst variabele te zorgen
	 */
	public static Comparator<Item> ItemVergelijker = new Comparator<Item>()
	{
		public int compare(Item arg0, Item arg1)
		{
			return arg0.getTitel().compareTo(arg1.getTitel());
		}
	};

	@Override
	public int compareTo(Item arg0)
	{
		return getClass().getSimpleName().compareTo(arg0.getClass().getSimpleName());
	}
}
