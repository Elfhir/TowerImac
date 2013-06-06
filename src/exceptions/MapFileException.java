package exceptions;

public class MapFileException extends Exception {

	private static final long serialVersionUID = 1828442184051382786L;

	public MapFileException(String message) {
		System.out.println("MapFileException : " + message);
	}

}
