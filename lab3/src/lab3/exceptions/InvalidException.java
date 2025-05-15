package lab3.exceptions;

public class InvalidException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//tar emot felmeddelande som sedan kan anv med throw/catch
	public InvalidException(String message) {
        super(message); 
        
        

    }
    
    
}