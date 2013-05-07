package exceptions;

public class MapFileException extends Exception {
	public MapFileException(String message) {
		System.out.println("MapFileException : " + message);
	}

}
