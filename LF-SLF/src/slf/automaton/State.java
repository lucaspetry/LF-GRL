package slf.automaton;

import java.util.Map;

import slf.exception.InvalidTransitionException;

/**
 * Estado.
 * @author lucas
 */
public class State {
	
	/**
	 * Construtor.
	 * @param name nome do estado.
	 * @param isFinal true caso o estado seja final.
	 * @param transitions transições do estado.
	 */
	public State(String name, boolean isFinal, Map<Character, State> transitions) {
		this.name = name;
		this.isFinal = isFinal;
		this.transitions = transitions;
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
	
	/**
	 * Transitar do estado para outro.
	 * @param symbol símbolo de entrada de transição.
	 * @return estado de destino da transição.
	 * @throws InvalidTransitionException se não há uma transição pelo símbolo de entrada especificado.
	 */
	public State transit(char symbol) throws InvalidTransitionException {
		if(!this.transitions.containsKey(symbol))
			throw new InvalidTransitionException();
		
		return this.transitions.get(symbol);
	}

	private String name;
	private boolean isFinal;
	private Map<Character, State> transitions;

}
