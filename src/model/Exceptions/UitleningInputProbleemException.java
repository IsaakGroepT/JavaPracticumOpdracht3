package model.Exceptions;

public class UitleningInputProbleemException extends Exception {

	public UitleningInputProbleemException() {}
	
	public UitleningInputProbleemException(String bericht)
	{
		super(bericht);
	}
}
