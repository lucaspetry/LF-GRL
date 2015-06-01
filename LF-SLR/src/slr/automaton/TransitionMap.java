package slr.automaton;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import slr.exception.InvalidTransitionException;

/**
 * Mapa de transições.
 */
public class TransitionMap {

	Map<Character, Set<State>> transitions;
	
	/**
	 * Construtor.
	 */
	public TransitionMap() {
		this.transitions = new HashMap<Character, Set<State>>();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
	    TransitionMap transitionMap = new TransitionMap();
	    
	    for(char k : this.transitions.keySet()) {
	    	try {
				for(State s : this.get(k))
					transitionMap.add(k, s);
			} catch (InvalidTransitionException e) {
				throw new CloneNotSupportedException();
			}
	    }
	    
	    return transitionMap;
	}
	
	/**
	 * Adicionar transição.
	 * @param symbol símbolo de transição.
	 * @param targetState estado de destino da transição.
	 */
	public void add(final char symbol, final State targetState) {
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
	public void remove(final char symbol, final State targetState) throws InvalidTransitionException {
		if(!this.transitions.containsKey(symbol))
			throw new InvalidTransitionException();
		
		if(!this.transitions.get(symbol).contains(targetState))
			throw new InvalidTransitionException();
		
		Set<State> states = this.transitions.get(symbol);
		
		if(states.size() == 1)
			this.transitions.remove(symbol);
	}
	
	/**
	 * Substituir os estados de destino pelo estado especificado.
	 * @param target estado a ser substituído.
	 * @param newTarget novo estado.
	 */
	public void replaceTargets(final State target, final State newTarget) {
		for(char symbol : this.transitions.keySet()) {
			Set<State> targets = this.transitions.get(symbol);
			
			if(targets.contains(target)) {
				targets.remove(target);
				targets.add(newTarget);
			}
		}
	}

	/**
	 * Obter as transições possíveis por um símbolo.
	 * @param symbol símbolo de transição.
	 * @return conjunto de estados de destino.
	 * @throws InvalidTransitionException caso não exista transição pelo símbolo.
	 */
	public Set<State> get(final char symbol) throws InvalidTransitionException {
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
	
	/**
	 * Obter as transições.
	 * @return mapa de transições.
	 */
	public Map<Character, Set<State>> getMap() {
		return this.transitions;
	}
	
}
