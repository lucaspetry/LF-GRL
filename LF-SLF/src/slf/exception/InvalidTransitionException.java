package slf.exception;

public class InvalidTransitionException extends Exception {
	
	public String getMessage() {
		return "Transição inválida!";
	}
	
	private static final long serialVersionUID = -798526128518478289L;

}
