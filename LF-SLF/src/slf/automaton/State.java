package slf.automaton;

/**
 * Estado.
 * @author lucas
 */
public class State {
	
	/**
	 * Construtor.
	 * @param name nome do estado.
	 * @param isFinal true caso o estado seja final.
	 */
	public State(String name, boolean isFinal) {
		this.name = name;
		this.isFinal = isFinal;
	}
	
	/**
	 * Obter o nome do estado.
	 * @return nome do estado.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Verificar se o estado é final.
	 * @return true caso o estado seja final.
	 */
	public boolean isFinal() {
		return isFinal;
	}

	private String name;
	private boolean isFinal;

}
