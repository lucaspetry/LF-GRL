package slf.automaton;

import java.util.ArrayList;
import java.util.List;

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
	
	/**
	 * Reconhecer uma entrada qualquer.
	 * @param entry entrada qualquer.
	 * @return true caso a entrada seja uma sentença da linguagem.
	 */
	public boolean recognize(final String entry) {
		return true; // TODO
	}
	
	/**
	 * Determinizar o autômato.
	 */
	public void determinize() {
		
	}
	
	/**
	 * Calcular o autômato da interseção com o autômato especificado.
	 * @param automaton autômato finito.
	 * @return o autômato resultante da interseção.
	 */
	public FiniteAutomaton intercection(final FiniteAutomaton automaton) {
		return null;
	}

	/**
	 * Calcular o autômato da diferença com o autômato especificado.
	 * @param automaton autômato finito.
	 * @return o autômato resultante da diferença.
	 */
	public FiniteAutomaton difference(final FiniteAutomaton automaton) {
		return null;
	}

	/**
	 * Minimizar o autômato.
	 */
	public void minimize() {
		
	}

	/**
	 * Verificar se o autômato é determinístico.
	 * @return true se o autômato é determinístico.
	 */
	public boolean isDeterministic() {
		return true;
	}

	/**
	 * Verificar se o autômato é equivalente ao autômato especificado.
	 * @param automaton autômato finito.
	 * @return true se os autômatos são equivalentes.
	 */
	public boolean isEquivalent(final FiniteAutomaton automaton) {
		return true;
	}

	/**
	 * Verificar se o autômato é mínimo.
	 * @return true se o autômato é mínimo.
	 */
	public boolean isMinimal() {
		return true;
	}

	private String alphabet;
	private List<State> states;
	private State initialState;

}
