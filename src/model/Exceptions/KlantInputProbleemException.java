package model.Exceptions;

public class KlantInputProbleemException extends Exception {

	public KlantInputProbleemException() {}
	
	public KlantInputProbleemException(String bericht)
	{
		super(bericht);
	}
}
