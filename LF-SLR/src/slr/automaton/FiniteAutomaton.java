package slr.automaton;

import java.util.HashMap;
import java.util.HashSet;
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

		for(State s1 : states) {
			for(State s2 : states) {
				s1.getTransitionMap().replaceTargets(s2, s2);
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
				for(State target : t.get(symbol)) {
					String sF = s.isFinal() ? "*" : "";
					String tF = target.isFinal() ? "*" : "";
					automaton += "δ(" + sF + s.getName() + ", " + symbol + ") = "
									+ tF + target.getName() + "\n";
				}
			}

			for(State target : t.get(RegularExpression.EPSILON)) {
				String sF = s.isFinal() ? "*" : "";
				String tF = target.isFinal() ? "*" : "";
				automaton += "δ(" + sF + s.getName() + ", " + RegularExpression.EPSILON + ") = "
								+ tF + target.getName() + "\n";
			}
		}
		
		StringBuilder builder = new StringBuilder(automaton);
		String sF = this.initialState.isFinal() ? "*" : "";
		int initialIndex = builder.indexOf("δ(" + sF + this.initialState.getName());
		String rest = builder.substring(0, initialIndex);
		builder.delete(0, initialIndex);
		builder.append(rest);
		
		return builder.toString();
	}
	
	/**
	 * Obter a tabela de transições do autômato.
	 * @return matriz de strings correspondente à tabela de transições.
	 */
	public String[][] toTransitionsTable() { // TODO Refatorar todo o método.
		int epsilonColumn = this.isEpsilonFree() ? 0 : 1;
		String[][] matriz = new String[this.states.size() + 1][1 + this.alphabet.length() + epsilonColumn];
		matriz[0][0] = "δ";

		for(int i = 0; i < this.alphabet.length(); i++) {
			char symbol = this.alphabet.charAt(i);
			matriz[0][i + 1] =  symbol + "";
			
			State[] statesArray = this.states.toArray(new State[1]);

			for(int j = 0; j < statesArray.length; j++) {
				String finalSymbol = statesArray[j].isFinal() ? "*" : "";
				matriz[j + 1][0] = finalSymbol + statesArray[j].getName();
				matriz[j + 1][i + 1] = "";
				
				try {
					Set<State> targets = statesArray[j].transit(symbol);
					
					for(State s : targets)
						matriz[j + 1][i + 1] += s.getName() + ", ";
					
					matriz[j + 1][i + 1] = matriz[j + 1][i + 1].substring(0, matriz[j + 1][i + 1].length() - 2);
				} catch (InvalidTransitionException e) {
					matriz[j + 1][i + 1] = "-";
				}
			}
		}
		
		if(epsilonColumn > 0) {
			matriz[0][matriz[0].length] = "" + RegularExpression.EPSILON;
			matriz[1][matriz[0].length] = "";
			
			try {
				Set<State> targets = this.initialState.transit(RegularExpression.EPSILON);

				for(State s : targets)
					matriz[1][matriz[0].length] += s.getName() + ", ";
				
				matriz[1][matriz[0].length] = matriz[1][matriz[0].length]
						.substring(0, matriz[1][matriz[0].length].length() - 2);
			} catch (InvalidTransitionException e) {
				matriz[1][matriz[0].length] = "-";
			}
		}
		
		
		return matriz;
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
		Set<State> states = new HashSet<State>();
		
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
		Set<State> states = new HashSet<State>();
		
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
	 * Verificar se as linguagens dos autômatos são iguais.
	 * @return true caso as linguagens sejam iguais.
	 */
	public boolean isEquivalentTo(final FiniteAutomaton automaton) {
		return this.contains(automaton) && automaton.contains(this);
	}
	
	/**
	 * Verificar se a linguagem do autômato especificado está contida na linguagem do autômato em questão.
	 * @param automaton autômato a ser verificado.
	 * @return true se T(automaton) está contida na linguagem do autômato.
	 */
	public boolean contains(final FiniteAutomaton automaton) {
		return this.complement().intercection(automaton).isEmpty();
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
		if(this.isDeterministic())
			return;
		
		Set<HashSet<State>> states = new HashSet<HashSet<State>>();
		Set<HashSet<State>> newStates = new HashSet<HashSet<State>>();
		HashSet<State> initial = new HashSet<State>();
		initial.add(this.initialState);
		initial.addAll(this.initialState.getEpsilonClosure());
		newStates.add(initial);
		
		// Agrupar os estados para formar os novos determinísticos
		while(newStates.size() > 0) {
			@SuppressWarnings("unchecked")
			HashSet<State> currentState = (HashSet<State>) newStates.toArray()[0];
			
			for(char symbol : this.getAlphabet().toCharArray()) {
				HashSet<State> newState = new HashSet<State>();
				
				for(State current : currentState) {					
					try {
						for(State target : current.transit(symbol))
							newState.addAll(target.getEpsilonClosure());
					} catch (InvalidTransitionException e) {}
				}

				if(newState.size() > 0) {
					if(!states.contains(newState))
						newStates.add(newState);
				}
			}
						
			states.add(currentState);
			newStates.remove(currentState);
		}

		// Criar os novos estados determinísticos
		Map<HashSet<State>, State> deterministicStates = new HashMap<HashSet<State>, State>();
		State newInitialState = new State("q0", this.initialState.isFinal(), new TransitionMap());

		states.remove(initial);
		deterministicStates.put(initial, newInitialState);
		
		int stateIndex = 1;
		for(HashSet<State> state : states) {
			boolean isFinal = false;
			
			for(State s : state) {
				isFinal = isFinal || s.isFinal();
			}
			deterministicStates.put(state, new State("q" + stateIndex, isFinal, new TransitionMap()));
			
			stateIndex++;
		}
		states.add(initial);
		
		// Criar as transições dos novos estados
		for(HashSet<State> state : states) {
			State deterministicState = deterministicStates.get(state);
			
			for(char symbol : this.getAlphabet().toCharArray()) {
				HashSet<State> targetState = new HashSet<State>();

				for(State s : state) {
					try {
						for(State target : s.transit(symbol))
							targetState.addAll(target.getEpsilonClosure());
					} catch (InvalidTransitionException e) {}
				}

				if(targetState.size() > 0) {
					State deterministicTargetState = deterministicStates.get(targetState);
					deterministicState.getTransitionMap().add(symbol, deterministicTargetState);
				}
			}
		}
		
		this.initialState = newInitialState;
		this.states = new HashSet<State>(deterministicStates.values());
	}

	/**
	 * Minimizar o autômato.
	 */
	public void minimize() {
		this.determinize();
		this.removeUnreachableStates();
		this.removeDeadStates();
		this.complete();
		
		// Eliminar estados equivalentes
		Set<Set<State>> classes = new HashSet<Set<State>>();
		classes.add(this.getFinalStates());
		classes.add(this.getNotFinalStates());
		
		for(Set<State> stateClass : classes) {
			State[] states = stateClass.toArray(new State[1]);
			State prev = states[0];
			
			for(int i = 1; i < states.length; i++) {
				State current = states[i];
				for(char symbol : this.alphabet.toCharArray()) {
					try {
						Set<State> prevStates = prev.transit(symbol);
						Set<State> currentStates = current.transit(symbol);
						
						for(Set<State> targetClass : classes) {
							boolean cP = targetClass.containsAll(prevStates);
							boolean cC = targetClass.containsAll(currentStates);
							if((cP && !cC) || (!cP && cC)) {
								// Minimização
							}
						}
					} catch (InvalidTransitionException e) {}
					
				}
			}
		}
				
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

					if(reachable.size() > 1) // Existe mais do que um alcançável pelo símbolo
						return false;
					
					Set<State> reachableEpsilon = state.transit(RegularExpression.EPSILON);

					if(reachableEpsilon.size() > 0) // Existe uma &-transição
						return false;
				} catch (InvalidTransitionException e) {}
			}
			
			try {
				Set<State> reachableEpsilon = state.transit(RegularExpression.EPSILON);
				
				if(reachableEpsilon.size() > 1) // Existe uma &-transição para mais de um estado
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
		try {
			FiniteAutomaton minimal = (FiniteAutomaton) this.clone();
			minimal.minimize();
			
			if(minimal.states.size() == this.states.size())
				return true;
		} catch (CloneNotSupportedException e) {}
		
		return false;
	}

	/**
	 * Verificar se a linguagem do autômato é vazia.
	 * @return true se a linguagem do autômato é vazia.
	 */
	public boolean isEmpty() {
		try {
			FiniteAutomaton automaton = (FiniteAutomaton) this.clone();
			automaton.removeDeadStates();
			
			if(!automaton.states.contains(automaton.initialState))
				return true;
		} catch (CloneNotSupportedException e) {}
		
		return false;
	}

	/**
	 * Verificar se o autômato não possui &-transições.
	 * @return true se o autômato não possui &-transições.
	 */
	public boolean isEpsilonFree() {
		for(State s : this.states) {
			try {
				if(s.transit(RegularExpression.EPSILON).size() > 0)
					return false;
			} catch (InvalidTransitionException e) {}
		}
		
		return true;
	}
	
	/**
	 * Calcular o autômato complemento.
	 * @return o autômato resultante do complemento.
	 */
	public FiniteAutomaton complement() {
		try {
			FiniteAutomaton automaton = (FiniteAutomaton) this.clone();
			automaton.determinize();
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
