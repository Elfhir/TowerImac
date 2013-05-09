package exceptions;

public class IAPlayerException extends Exception {
	public IAPlayerException(String message) {
		System.out.println("IAPlayerException : " + message);
	}

}