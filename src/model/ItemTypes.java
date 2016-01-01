package model;

public enum ItemTypes {
	TYPE_CD("CD"),
	TYPE_FILM("Film"),
	TYPE_SPEL("Spel"),
	TYPE_ALL("ALLES");
	
	private String type;
	
	ItemTypes(String type)
	{
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}
}
