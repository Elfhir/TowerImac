package exceptions;

public class IAPlayerException extends Exception {
	
	private static final long serialVersionUID = -121433360015891281L;

	public IAPlayerException(String message) {
		System.out.println("IAPlayerException : " + message);
	}

}