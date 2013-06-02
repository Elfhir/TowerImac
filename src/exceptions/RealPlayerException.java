package exceptions;

public class RealPlayerException extends Exception {
	public RealPlayerException(String message) {
		System.out.println("RealPlayerException : " + message);
		System.out.println("Mode spectateur !");
	}

}
