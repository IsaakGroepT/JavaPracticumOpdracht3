package db;

public static class Uitleenlijst {

	List<Item> items;
	
	/**
	 * 
	 */
	public Uitleenlijst()
	{
		items = new List<Item>();
	}
	
	/**
	 * 
	 */
	public List<Item> getItems()
	{
		return items;
	}
	
	/**
	 * 
	 */
	public Item getItem(idx)
	{
		return items.get(idx);
	}
}
