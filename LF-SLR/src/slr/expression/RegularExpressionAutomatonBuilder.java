package slr.expression;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import slr.automaton.FiniteAutomaton;
import slr.automaton.State;
import slr.automaton.TransitionMap;

public class RegularExpressionAutomatonBuilder {

	private RegularExpression regularExpression;
	
	public RegularExpressionAutomatonBuilder(RegularExpression regularExpression) {
		this.regularExpression = regularExpression;
	}
	
	public FiniteAutomaton buildAutomaton() {
		SyntaxTree tree = this.regularExpression.getSyntaxTree();
		String alphabet = this.regularExpression.getTerminals();
		
		Set<BinaryTreeNode<Character>> initialComposition = tree.getRoot().getReachableNodes();
		Set<HashSet<BinaryTreeNode<Character>>> remainingCompositions = new HashSet<HashSet<BinaryTreeNode<Character>>>();
		Map<HashSet<BinaryTreeNode<Character>>, State> states = new HashMap<HashSet<BinaryTreeNode<Character>>, State>();
		State initialState = new State("Q0", false, new TransitionMap());
		
		return new FiniteAutomaton(null, null); // TODO
	}
	
}
