package slr.exception;

/**
 * Exceção de transição inválida.
 */
public class InvalidTransitionException extends Exception {

	private static final long serialVersionUID = -798526128518478289L;

	/**
	 * Construtor.
	 */
	public InvalidTransitionException() {
		super("Transição inválida!");
	}

}
