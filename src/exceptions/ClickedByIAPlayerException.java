package exceptions;

public class ClickedByIAPlayerException extends Exception{

	public ClickedByIAPlayerException(String message) {
		System.out.println("ClickedByIA : " + message);
	}

}
