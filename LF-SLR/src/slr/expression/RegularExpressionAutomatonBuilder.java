package slr.expression;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import slr.automaton.FiniteAutomaton;
import slr.automaton.State;
import slr.automaton.TransitionMap;

/**
 * Construtor de autômato finito para expressões regulares. 
 */
public class RegularExpressionAutomatonBuilder {

	private RegularExpression regularExpression;
	
	/**
	 * Construtor.
	 * @param regularExpression expressão regular.
	 */
	public RegularExpressionAutomatonBuilder(RegularExpression regularExpression) {
		this.regularExpression = regularExpression;
	}
	
	/**
	 * Construir o autômato.
	 * @return autômato finito correspondente.
	 */
	public FiniteAutomaton buildAutomaton() {
		SyntaxTree tree = this.regularExpression.getSyntaxTree();
		String alphabet = this.regularExpression.getTerminals();
		
		// Definir um conjunto de estados e o estado inicial
		Map<HashSet<BinaryTreeNode<Character>>, State> states = new HashMap<HashSet<BinaryTreeNode<Character>>, State>();
		State initialState = new State("Q0", false, new TransitionMap());
		
		// Se a árvore é vazia, então T(M) é vazia
		if(tree.getRoot() == null) {
			Set<State> statesEmpty = new HashSet<State>();
			statesEmpty.add(initialState);
			return new FiniteAutomaton(statesEmpty, initialState);
		} else if(tree.getRoot().getValue() == RegularExpression.EPSILON) {
			Set<State> statesEmpty = new HashSet<State>();
			statesEmpty.add(initialState);
			initialState.setIsFinal(true);
			return new FiniteAutomaton(statesEmpty, initialState);			
		}
		
		// Composição do estado inicial
		HashSet<BinaryTreeNode<Character>> initialComposition = (HashSet<BinaryTreeNode<Character>>) tree.getRoot().getReachableNodes();
		states.put(initialComposition, initialState);
		
		Set<HashSet<BinaryTreeNode<Character>>> doneCompositions = new HashSet<HashSet<BinaryTreeNode<Character>>>();
		Set<HashSet<BinaryTreeNode<Character>>> remainingCompositions = new HashSet<HashSet<BinaryTreeNode<Character>>>();
		doneCompositions.add(initialComposition);
		int stateNumber = 1;
		String statePrefix = "Q";
		
		// Definir as transições do estado inicial
		for(char c : alphabet.toCharArray()) {
			HashSet<BinaryTreeNode<Character>> stateComposition = new HashSet<BinaryTreeNode<Character>>();
			boolean stateIsFinal = false;
			
			for(BinaryTreeNode<Character> node : initialComposition) {
				if(node.getValue() == c) {
					for(BinaryTreeNode<Character> reachable : node.getReachableNodes()) {
						stateComposition.add(reachable);
						
						if(reachable.getValue() == RegularExpression.EPSILON)
							stateIsFinal = true;
					}
				} else if(node.getValue() == RegularExpression.EPSILON) {
					initialState.setIsFinal(true);
				}
			}

			if(stateComposition.size() > 0) {
				if(!stateComposition.equals(initialComposition)) {
					State state = new State(statePrefix + stateNumber, stateIsFinal, new TransitionMap());
					initialState.getTransitionMap().add(c, state);
					states.put(stateComposition, state);
					remainingCompositions.add(stateComposition);
					stateNumber++;
				} else {
					initialState.getTransitionMap().add(c, initialState);
				}
			}
		}

		// Definir as transições dos demais estados
		while(remainingCompositions.size() > 0) {
			@SuppressWarnings("unchecked")
			HashSet<BinaryTreeNode<Character>> composition =
				(HashSet<BinaryTreeNode<Character>>) remainingCompositions.toArray()[0];
			State currentState = states.get(composition);
			
			for(char c : alphabet.toCharArray()) {
				HashSet<BinaryTreeNode<Character>> stateComposition = new HashSet<BinaryTreeNode<Character>>();
				boolean stateIsFinal = false;
				
				for(BinaryTreeNode<Character> node : composition) {
					if(node.getValue() == c) {
						for(BinaryTreeNode<Character> reachable : node.getReachableNodes()) {
							stateComposition.add(reachable);
							
							if(reachable.getValue() == RegularExpression.EPSILON)
								stateIsFinal = true;
						}
					}
				}
				
				if(stateComposition.size() > 0) {
					if(!doneCompositions.contains(stateComposition) &&
							!remainingCompositions.contains(stateComposition)) {
						State state = new State(statePrefix + stateNumber, stateIsFinal, new TransitionMap());
						currentState.getTransitionMap().add(c, state);
						states.put(stateComposition, state);
						remainingCompositions.add(stateComposition);
						stateNumber++;
					} else {
						currentState.getTransitionMap().add(c, states.get(stateComposition));
					}
				}
			}
			
			doneCompositions.add(composition);
			remainingCompositions.remove(composition);
		}
		
		return new FiniteAutomaton(new HashSet<State>(states.values()), initialState);
	}
	
}
