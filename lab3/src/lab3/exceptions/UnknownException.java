package lab3.exceptions;

public class UnknownException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//tar emot felmeddelande som sedan kan sedan kan anv med throw/catch
	public UnknownException(String message) {
        super(message); 
    }
}