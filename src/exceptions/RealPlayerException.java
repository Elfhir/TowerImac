package exceptions;

public class RealPlayerException extends Exception {
	
	private static final long serialVersionUID = 1627951501532475859L;

	public RealPlayerException(String message) {
		System.out.println("RealPlayerException : " + message);
	}

}
