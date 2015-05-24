package slr.automaton;

import java.util.Set;
import java.util.TreeSet;

import slr.exception.InvalidTransitionException;

/**
 * Autômato finito.
 * @author lucas
 */
public class FiniteAutomaton {

	private String alphabet;
	private Set<State> states;
	private State initialState;
	
	/**
	 * Construtor.
	 */
	public FiniteAutomaton() {
		this.alphabet = "";
		this.states = new TreeSet<State>();
		this.initialState = null;
	}

	/**
	 * Construtor.
	 * @param states conjunto de estados.
	 * @param alphabet alfabeto do autômato.
	 * @param initialState estado inicial do conjunto de estados.
	 */
	public FiniteAutomaton(final Set<State> states, final String alphabet, final State initialState) {
		this.alphabet = alphabet;
		this.states = states;
		this.initialState = initialState;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Set<State> states = new TreeSet<State>();
		State initial = null;
		
		for(State s : this.states) {
			State newState = (State) s.clone();
			states.add(newState);
			
			if(s.equals(this.initialState)) {
				initial = newState;
			}
		}
		
	    return new FiniteAutomaton(states, this.alphabet, initial);
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
	 * Obter os estados finais.
	 * @return conjunto de estados finais.
	 */
	public Set<State> getFinalStates() {
		Set<State> states = new TreeSet<State>();
		
		for(State s : this.states) {
			if(s.isFinal())
				states.add(s);
		}
		
		return states;
	}
	
	/**
	 * Obter os estados não finais.
	 * @return conjunto de estados não finais.
	 */
	public Set<State> getNotFinalStates() {
		Set<State> states = new TreeSet<State>();
		
		for(State s : this.states) {
			if(!s.isFinal())
				states.add(s);
		}
		
		return states;
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
	 * Calcular o autômato complemento.
	 * @return o autômato resultante do complemento.
	 */
	public FiniteAutomaton complement() {
		try {
			FiniteAutomaton automaton = (FiniteAutomaton) this.clone();
			Set<State> finalStates = automaton.getFinalStates();
			Set<State> notFinalStates = automaton.getNotFinalStates();
			
			for(State s : finalStates) {
				s.setIsFinal(false);
			}

			for(State s : notFinalStates) {
				s.setIsFinal(true);
			}
			
			return automaton;
		} catch (CloneNotSupportedException e) {
			return null;
		}
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
		if(!this.isDeterministic())
			this.determinize();
		
		this.removeUnreachableStates();
		this.removeDeadStates();
		// TODO Continuar a minimização.
	}

	/**
	 * Verificar se o autômato é determinístico.
	 * @return true se o autômato é determinístico.
	 */
	public boolean isDeterministic() {
		for(State state : this.states) {
			for(char symbol : this.alphabet.toCharArray()) {
				try {
					Set<State> reachable = state.transit(symbol);
					
					if(reachable.size() > 1)
						return false;
				} catch (InvalidTransitionException e) {}
			}
			
			try {
				Set<State> reachable = state.transit('&'); // TODO Padronizar símbolo epsilon
				
				if(reachable.size() > 0)
					return false;
			} catch (InvalidTransitionException e) {}
		}
		
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
	
	/**
	 * Remover estados inalcançáveis.
	 */
	private void removeUnreachableStates() {
		Set<State> reachableStates = new TreeSet<State>();
		reachableStates.add(this.initialState);
		
		boolean reachableSetChanged = true;

		while(reachableSetChanged) {
			int setSize = reachableStates.size();
			
			for(State state : reachableStates) {
				for(State reachable : state.getReachableStates())
					reachableStates.add(reachable);
			}
			
			if(setSize == reachableStates.size())
				reachableSetChanged = false;
		}
		
		this.states = reachableStates;
	}
	
	/**
	 * Remover estados mortos.
	 */
	private void removeDeadStates() {
		Set<State> livingStates = new TreeSet<State>();

		for(State state : this.states) {
			if(state.isFinal())
				livingStates.add(state);
		}
		
		boolean livingSetChanged = true;

		while(livingSetChanged) {
			int setSize = livingStates.size();
			
			for(State state : this.states) {
				if(!livingStates.contains(state)) {
					for(State reachable : state.getReachableStates()) {
						if(livingStates.contains(reachable)) {
							livingStates.add(state);
							break;
						}
					}
				}
			}
			
			if(setSize == livingStates.size())
				livingSetChanged = false;
		}
		
		this.states = livingStates;
	}

}
