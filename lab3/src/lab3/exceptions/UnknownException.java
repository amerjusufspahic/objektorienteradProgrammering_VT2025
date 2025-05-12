package lab3.exceptions;

public class UnknownException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnknownException(String message) {
        super(message); 
    }
}