package slr.expression;

import slr.automaton.FiniteAutomaton;

public class RegularExpressionAutomatonBuilder {

	private RegularExpression regularExpression;
	
	public RegularExpressionAutomatonBuilder(RegularExpression regularExpression) {
		this.regularExpression = regularExpression;
	}
	
	public FiniteAutomaton buildAutomaton() {
		SyntaxTree tree = this.regularExpression.getSyntaxTree();
		
		return null; // TODO
	}
	
}
