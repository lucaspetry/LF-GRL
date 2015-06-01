package slr.exception;

/**
 * Exceção de expressão regular inválida.
 */
public class InvalidRegularExpressionException extends Exception {

	private static final long serialVersionUID = 3060091069211388500L;

	/**
	 * Construtor.
	 */
	public InvalidRegularExpressionException() {
		super("Expressão regular inválida!");
	}
}
