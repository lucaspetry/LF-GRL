package slf.automaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import slf.exception.InvalidTransitionException;

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
		this.transitions = new HashMap<Character, List<State>>();
	}
	
	/**
	 * Adicionar transição.
	 * @param symbol símbolo de transição.
	 * @param targetState estado de destino da transição.
	 */
	public void add(char symbol, State targetState) {
		if(this.transitions.containsKey(symbol)) {
			List<State> states = this.transitions.get(symbol);
			
			if(!states.contains(targetState))
				states.add(targetState);
		} else {
			List<State> states = new ArrayList<State>();
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
		
		List<State> states = this.transitions.get(symbol);
		
		if(states.size() == 1)
			this.transitions.remove(symbol);
	}

	/**
	 * Obter as transições possíveis por um símbolo.
	 * @param symbol símbolo de transição.
	 * @return lista de estados de destino.
	 * @throws InvalidTransitionException caso não exista transição pelo símbolo.
	 */
	public List<State> get(char symbol) throws InvalidTransitionException {
		if(!this.transitions.containsKey(symbol))
			throw new InvalidTransitionException();
		
		return this.transitions.get(symbol);
	}
	
	Map<Character, List<State>> transitions;
}
