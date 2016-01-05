package model.Exceptions;

public class ItemInputProbleemException extends Exception {

	public ItemInputProbleemException() {}
	
	public ItemInputProbleemException(String bericht)
	{
		super(bericht);
	}
}
