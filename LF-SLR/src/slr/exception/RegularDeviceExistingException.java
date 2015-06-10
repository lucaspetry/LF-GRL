package slr.exception;

/**
 * Exceção de dispositivo regular existente.
 */
public class RegularDeviceExistingException extends Exception {

	private static final long serialVersionUID = 5613529175480061691L;

	/**
	 * Construtor.
	 */
	public RegularDeviceExistingException() {
		super("Dispositivo regular já existente!");
	}
	
}
