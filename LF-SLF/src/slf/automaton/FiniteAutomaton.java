package slf.automaton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Autômato finito.
 * @author lucas
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
	
	/**
	 * Obter o alfabeto de entrada do autômato.
	 * @return alfabeto de entrada.
	 */
	public String getAlphabet() {
		return this.alphabet;
	}

	/**
	 * Obter o estado inicial do autômato.
	 * @return estado inicial.
	 */
	public State getInitialState() {
		return this.initialState;
	}
	
	private String alphabet;
	private List<State> states;
	private State initialState;
	
}
