package slf.automaton;

import java.util.List;

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
	public State(String name, boolean isFinal, TransitionMap transitions) {
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
	public List<State> transit(char symbol) throws InvalidTransitionException {
		return this.transitions.get(symbol);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof State) {
			State state = (State) obj;
			return state.getName().equals(this.name);
		}
		
		return false;
	}

	private String name;
	private boolean isFinal;
	private TransitionMap transitions;

}
