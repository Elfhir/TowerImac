package exceptions;

public class ClickedByRealPlayerException extends Exception {
	
	public ClickedByRealPlayerException(String message) {
		System.out.println("ClickedByReal : " + message);
	}
}
