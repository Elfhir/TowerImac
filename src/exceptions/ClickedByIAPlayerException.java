package exceptions;

public class ClickedByIAPlayerException extends Exception{

	private static final long serialVersionUID = -2813966006141799080L;

	public ClickedByIAPlayerException(String message) {
		System.out.println("ClickedByIA : " + message);
	}

}
