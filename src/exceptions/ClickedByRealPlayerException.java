package exceptions;

public class ClickedByRealPlayerException extends Exception {

	private static final long serialVersionUID = 8173287734825104749L;

	public ClickedByRealPlayerException(String message) {
		System.out.println("ClickedByReal : " + message);
	}
}
