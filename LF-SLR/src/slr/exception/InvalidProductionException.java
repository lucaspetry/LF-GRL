package slr.exception;

/**
 * Exceção de Produção Inválida.
 * @author lucas
 *
 */
public class InvalidProductionException extends Exception {

	private static final long serialVersionUID = 3060091069211388500L;

	/**
	 * Construtor.
	 */
	public InvalidProductionException() {
		super("Produção inválida!");
	}
}
