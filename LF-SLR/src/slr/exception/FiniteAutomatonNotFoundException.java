package slr.exception;

/**
 * Exceção de autômato finito não encontrado.
 */
public class FiniteAutomatonNotFoundException extends Exception {

	private static final long serialVersionUID = 8334446107269162117L;

	/**
	 * Construtor.
	 */
	public FiniteAutomatonNotFoundException() {
		super("Autômato finito não encontrado!");
	}
	
}
