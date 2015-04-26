package slf.automaton;

import java.util.ArrayList;
import java.util.List;

/**
 * Autômato finito.
 * @author lucas.
 */
public class FiniteAutomaton {

	/**
	 * Construtor.
	 */
	public FiniteAutomaton() {
		this.alphabet = "";
		this.states = new ArrayList<State>();
		this.initialState = null;
	}

	/**
	 * Construtor.
	 * @param states lista de estados, sendo o primeiro o estado inicial.
	 * @param alphabet alfabeto do autômato.
	 */
	public FiniteAutomaton(List<State> states, String alphabet) {
		this.alphabet = alphabet;
		this.states = states;
		
		if(states.size() > 0)
			this.initialState = states.get(0);
	}

	private String alphabet;
	private List<State> states;
	private State initialState;
	
}
