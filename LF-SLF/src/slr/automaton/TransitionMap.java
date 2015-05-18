package slr.automaton;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import slr.exception.InvalidTransitionException;

/**
 * Mapa de transições.
 * @author lucas
 *
 */
public class TransitionMap {

	/**
	 * Construtor.
	 */
	public TransitionMap() {
		this.transitions = new HashMap<Character, Set<State>>();
	}
	
	/**
	 * Adicionar transição.
	 * @param symbol símbolo de transição.
	 * @param targetState estado de destino da transição.
	 */
	public void add(char symbol, State targetState) {
		if(this.transitions.containsKey(symbol)) {
			Set<State> states = this.transitions.get(symbol);
			
			if(!states.contains(targetState))
				states.add(targetState);
		} else {
			Set<State> states = new TreeSet<State>();
			states.add(targetState);
			this.transitions.put(symbol, states);
		}
	}

	/**
	 * Remover transição.
	 * @param symbol símbolo de transição.
	 * @param targetState estado de destino da transição.
	 * @throws InvalidTransitionException se a transição não existe.
	 */
	public void remove(char symbol, State targetState) throws InvalidTransitionException {
		if(!this.transitions.containsKey(symbol))
			throw new InvalidTransitionException();
		
		if(!this.transitions.get(symbol).contains(targetState))
			throw new InvalidTransitionException();
		
		Set<State> states = this.transitions.get(symbol);
		
		if(states.size() == 1)
			this.transitions.remove(symbol);
	}

	/**
	 * Obter as transições possíveis por um símbolo.
	 * @param symbol símbolo de transição.
	 * @return conjunto de estados de destino.
	 * @throws InvalidTransitionException caso não exista transição pelo símbolo.
	 */
	public Set<State> get(char symbol) throws InvalidTransitionException {
		if(!this.transitions.containsKey(symbol))
			throw new InvalidTransitionException();
		
		return this.transitions.get(symbol);
	}
	
	/**
	 * Obter todos os estados para os quais existe uma transição.
	 * @return conjunto de estados.
	 */
	public Set<State> getTargetStates() {
		Set<State> states = new TreeSet<State>();
		
		for(Set<State> transition : this.transitions.values()) {
			for(State s : transition)
				states.add(s);
		}
	
		return states;
	}
	
	Map<Character, Set<State>> transitions;
}
