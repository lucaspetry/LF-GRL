package slr.exception;

/**
 * Exceção de transição inválida.
 * @author lucas
 *
 */
public class InvalidTransitionException extends Exception {
	
	public String getMessage() {
		return "Transição inválida!";
	}
	
	private static final long serialVersionUID = -798526128518478289L;

}
