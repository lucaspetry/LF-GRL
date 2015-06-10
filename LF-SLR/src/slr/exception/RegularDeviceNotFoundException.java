package slr.exception;

/**
 * Exceção de dispositivo regular não encontrado.
 */
public class RegularDeviceNotFoundException extends Exception {

	private static final long serialVersionUID = 5208628057728789038L;

	/**
	 * Construtor.
	 */
	public RegularDeviceNotFoundException() {
		super("Dispositivo regular não encontrado!");
	}
	
}
