package CA;

public class IncorrectLengthException extends Exception{
	
	public IncorrectLengthException(String errorMessage) {
		super("The issue in " + errorMessage);
	}
}
