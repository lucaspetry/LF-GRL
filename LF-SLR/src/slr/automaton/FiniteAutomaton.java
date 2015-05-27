package slr.automaton;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import slr.exception.InvalidTransitionException;
import slr.expression.RegularExpression;

/**
 * Autômato finito.
 * @author lucas
 * 
 */
public class FiniteAutomaton {

	private String alphabet;
	private Set<State> states;
	private State initialState;
	
	/**
	 * Construtor.
	 * @param states conjunto de estados.
	 * @param  alfabeto do autômato.
	 * @param initialState estado inicial do conjunto de estados.
	 */
	public FiniteAutomaton(final Set<State> states, final State initialState) {
		this.states = states;
		this.initialState = initialState;
		this.buildAlphabet();
	}
	
	/**
	 * Construir o alfabeto do autômato a partir de suas transições.
	 */
	private void buildAlphabet() {
		Set<Character> alphabet = new TreeSet<Character>();
		this.alphabet = "";
		
		for(State s : this.states)
			alphabet.addAll(s.getTransitions().keySet());
		
		alphabet.remove(RegularExpression.EPSILON);
		
		for(char c : alphabet.toArray(new Character[1]))
			this.alphabet += "" + c;
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
		
	    return new FiniteAutomaton(states, initial);
	}

	@Override
	public String toString() {
		String automaton = "";
		for(State s : this.states) {
			Map<Character, Set<State>> t = s.getTransitions();
			for(char symbol : t.keySet()) {
				for(State target : t.get(symbol))
					automaton += "δ(" + s.getName() + ", " + symbol + ") = "
									+ target.getName() + "\n";
			}
		}
		
		StringBuilder builder = new StringBuilder(automaton);
		int initialIndex = builder.indexOf("δ(" + this.initialState.getName());
		String rest = builder.substring(0, initialIndex);
		builder.delete(0, initialIndex);
		builder.append(rest);
		
		return builder.toString();
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
		return this.recognizeEntry(entry, this.initialState);
	}
	
	/**
	 * Método auxiliar de reconhecimento de uma entrada.
	 * @param entry entrada qualquer.
	 * @param begin estado de início do reconhecimento.
	 * @return true caso a entrada seja reconhecida a partir do estado especificado.
	 */
	private boolean recognizeEntry(final String entry, final State begin) {
		switch(entry.length()) {
			case 0:
				try {
					for(State s : begin.transit(RegularExpression.EPSILON)) {
						if(this.recognizeEntry(entry, s))
							return true;
					}
				} catch (InvalidTransitionException e) {}
				
				return begin.isFinal();
			default:
				try {
					for(State s : begin.transit(RegularExpression.EPSILON)) {
						if(this.recognizeEntry(entry, s))
							return true;
					}
				} catch (InvalidTransitionException e) {}
				
				try {
					for(State s : begin.transit(entry.charAt(0))) {
						if(this.recognizeEntry(entry.substring(1, entry.length()), s))
							return true;
					}
				} catch (InvalidTransitionException e) {}
				
				return false;
		}
	}
	
	/**
	 * Completar o autômato.
	 */
	public void complete() {
		TransitionMap transitionsDead = new TransitionMap();
		State deadState = new State("Φ", false, transitionsDead);
		boolean deadStateNeeded = false;
		
		for(char symbol : this.getAlphabet().toCharArray()) {
			transitionsDead.add(symbol, deadState);
			
			for(State s : this.states) {
				try {
					s.transit(symbol);
				} catch (InvalidTransitionException e) {
					s.getTransitionMap().add(symbol, deadState);
					deadStateNeeded = true;
				}
			}
		}
		
		if(deadStateNeeded)
			this.states.add(deadState);
	}
	
	/**
	 * Determinizar o autômato.
	 */
	public void determinize() {
		// TODO
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
	 * Verificar se o autômato é completo.
	 * @return true se o autômato possui todas as transições definidas.
	 */
	public boolean isComplete() {
		for(char symbol : this.getAlphabet().toCharArray()) {
			for(State s : this.states) {
				try {
					s.transit(symbol);
				} catch (InvalidTransitionException e) {
					return false;
				}
			}
		}
		
		return true;
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
				Set<State> reachable = state.transit(RegularExpression.EPSILON);
				
				if(reachable.size() > 0)
					return false;
			} catch (InvalidTransitionException e) {}
		}
		
		return true;
	}

	/**
	 * Verificar se o autômato é mínimo.
	 * @return true se o autômato é mínimo.
	 */
	public boolean isMinimal() {
		return true; // TODO
	}
	
	/**
	 * Calcular o autômato complemento.
	 * @return o autômato resultante do complemento.
	 */
	public FiniteAutomaton complement() {
		try {
			FiniteAutomaton automaton = (FiniteAutomaton) this.clone();
			automaton.complete();
			
			for(State s : automaton.states) {
				s.setIsFinal(!s.isFinal());
			}
			
			return automaton;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	/**
	 * Calcular o autômato da união com o autômato especificado.
	 * @param automaton autômato finito.
	 * @return o autômato resultante da interseção.
	 */
	public FiniteAutomaton union(final FiniteAutomaton automaton) {
		int stateNumber = 1;
		try {
			// Clonar o autômato atual
			FiniteAutomaton unionAutomaton = (FiniteAutomaton) this.clone();
			
			// Renomear os estados do atual
			for(State s : unionAutomaton.states) {
				s.setName("q" + stateNumber);
				stateNumber++;
			}

			// Renomear os estados do outro autômato e adicionar ao da união
			for(State s : automaton.states) {
				s.setName("q" + stateNumber);
				stateNumber++;
				unionAutomaton.states.add(s);
			}
			
			// Criar o estado inicial a partir de um dos estados iniciais
			State initialState = (State) unionAutomaton.initialState.clone();
			initialState.setName("q0");
			initialState.setIsFinal(automaton.initialState.isFinal() || initialState.isFinal());
			TransitionMap transitions = initialState.getTransitionMap();
			
			// Copiar as transições do outro estado inicial
			for(char symbol : automaton.getAlphabet().toCharArray()) {
				try {
					for(State target : automaton.initialState.transit(symbol)) {
						transitions.add(symbol, target);
					}
				} catch (InvalidTransitionException e) {}
			}
			
			// Definir o novo estado inicial e atualizar o alfabeto
			unionAutomaton.states.add(initialState);
			unionAutomaton.initialState = initialState;
			unionAutomaton.buildAlphabet();		
			
			return unionAutomaton;
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
		try {
			FiniteAutomaton a = (FiniteAutomaton) this.clone();
			FiniteAutomaton b = (FiniteAutomaton) automaton.clone();
			FiniteAutomaton union = a.complement().union(b.complement());
			
			return union.complement();
		} catch (CloneNotSupportedException e) {}
		
		return null;
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
