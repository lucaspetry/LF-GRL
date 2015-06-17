package slr.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import slr.automaton.FiniteAutomaton;
import slr.automaton.State;
import slr.automaton.TransitionMap;
import slr.exception.InvalidTransitionException;
import slr.expression.RegularExpression;

public class FiniteAutomatonTest {

	private FiniteAutomaton automatonA;
	private FiniteAutomaton automatonB;
	private FiniteAutomaton automatonC;
	private Set<State> automatonAfinalStates;
	private Set<State> automatonAnotFinalStates;
	
	@Before
	public void setUp() throws Exception {
		/**
		 * Autômato A = (a,b)* | #a's é divisível por 3
		 * Autômato B = (a,b)* | 'ab' pertence à sentença
		 */
		TransitionMap aTransitionsA = new TransitionMap();
		TransitionMap bTransitionsA = new TransitionMap();
		TransitionMap cTransitionsA = new TransitionMap();

		State aA = new State("aState", true, aTransitionsA);
		State bA = new State("bState", false, bTransitionsA);
		State cA = new State("cState", false, cTransitionsA);
		
		Set<State> statesA = new TreeSet<State>();
		statesA.add(aA);
		statesA.add(bA);
		statesA.add(cA);
		
		aTransitionsA.add('a', bA);
		aTransitionsA.add('b', aA);
		bTransitionsA.add('a', cA);
		bTransitionsA.add('b', bA);
		cTransitionsA.add('a', aA);
		cTransitionsA.add('b', cA);
		
		this.automatonA = new FiniteAutomaton(statesA, aA);
		this.automatonAfinalStates = new TreeSet<State>();
		this.automatonAfinalStates.add(aA);
		this.automatonAnotFinalStates = new TreeSet<State>();
		this.automatonAnotFinalStates.add(bA);
		this.automatonAnotFinalStates.add(cA);

		TransitionMap aTransitionsB = new TransitionMap();
		TransitionMap bTransitionsB = new TransitionMap();
		TransitionMap cTransitionsB = new TransitionMap();

		State aB = new State("q0", false, aTransitionsB);
		State bB = new State("q1", false, bTransitionsB);
		State cB = new State("q2", true, cTransitionsB);
		
		Set<State> statesB = new TreeSet<State>();
		statesB.add(aB);
		statesB.add(bB);
		statesB.add(cB);
		
		aTransitionsB.add('a', aB);
		aTransitionsB.add('a', bB);
		aTransitionsB.add('b', aB);
		bTransitionsB.add('b', cB);
		cTransitionsB.add('a', cB);
		cTransitionsB.add('b', cB);
		this.automatonB = new FiniteAutomaton(statesB, aB);

		TransitionMap aTransitionsC = new TransitionMap();
		State aC = new State("a", true, aTransitionsC);

		Set<State> statesC = new TreeSet<State>();
		statesC.add(aC);

		aTransitionsC.add('a', aC);
		aTransitionsC.add('b', aC);
		this.automatonC = new FiniteAutomaton(statesC, aC);
	}

	@Test
	public void testGetAlphabet() {
		TransitionMap aTransitions = new TransitionMap();
		TransitionMap bTransitions = new TransitionMap();
		TransitionMap cTransitions = new TransitionMap();
		TransitionMap dTransitions = new TransitionMap();

		State a = new State("q0", false, aTransitions);
		State b = new State("q1", false, bTransitions);
		State c = new State("q2", true, cTransitions);
		State d = new State("q3", false, dTransitions);
		
		Set<State> states = new TreeSet<State>();
		states.add(a);
		states.add(b);
		states.add(c);
		states.add(d);
		
		aTransitions.add('a', a);
		aTransitions.add('a', b);
		bTransitions.add('d', c);
		cTransitions.add('a', c);
		cTransitions.add('c', c);
		dTransitions.add('a', a);
		dTransitions.add(RegularExpression.EPSILON, b);
		FiniteAutomaton f = new FiniteAutomaton(states, a);

		assertEquals(true, f.getAlphabet().contains("a"));
		assertEquals(true, f.getAlphabet().contains("c"));
		assertEquals(true, f.getAlphabet().contains("d"));
		assertEquals(3, f.getAlphabet().length());
	}
	
	@Test
	public void testRecognizeDeterministic() {
		assertEquals(false, this.automatonA.recognize("asdbae"));
		assertEquals(false, this.automatonA.recognize("aababbba"));
		assertEquals(true, this.automatonA.recognize(""));
		assertEquals(true, this.automatonA.recognize("aaabb"));
		assertEquals(true, this.automatonA.recognize("abbababbbbbb"));
		assertEquals(true, this.automatonA.recognize("bbbb"));
	}

	@Test
	public void testRecognizeNondeterministic() {
		assertEquals(false, this.automatonB.recognize(""));
		assertEquals(false, this.automatonB.recognize("bbbbaaa"));
		assertEquals(false, this.automatonB.recognize("aaaa"));
		assertEquals(false, this.automatonB.recognize("bb"));
		assertEquals(true, this.automatonB.recognize("ab"));
		assertEquals(true, this.automatonB.recognize("abbbbbbbaa"));
		assertEquals(true, this.automatonB.recognize("aaaaaabb"));
		assertEquals(true, this.automatonB.recognize("bbbbbaaaaab"));
	}

	@Test
	public void testRecognizeNondeterministicEpsilon() {
		TransitionMap aTransitions = new TransitionMap();
		TransitionMap bTransitions = new TransitionMap();
		TransitionMap cTransitions = new TransitionMap();

		State a = new State("q0", false, aTransitions);
		State b = new State("q1", true, bTransitions);
		State c = new State("q2", true, cTransitions);
		
		Set<State> states = new TreeSet<State>();
		states.add(a);
		states.add(b);
		states.add(c);
		
		aTransitions.add(RegularExpression.EPSILON, b);
		aTransitions.add(RegularExpression.EPSILON, c);
		bTransitions.add('a', b);
		cTransitions.add('b', c);
		FiniteAutomaton f = new FiniteAutomaton(states, a);
				
		assertEquals(false, f.recognize("aaabb"));
		assertEquals(false, f.recognize("ba"));
		assertEquals(true, f.recognize(""));
		assertEquals(true, f.recognize("a"));
		assertEquals(true, f.recognize("aaaaa"));
		assertEquals(true, f.recognize("bbb"));
	}
	
	@Test
	public void testRecognizeNondeterministicEpsilon2() {
		TransitionMap aTransitions = new TransitionMap();
		TransitionMap bTransitions = new TransitionMap();
		TransitionMap cTransitions = new TransitionMap();

		State a = new State("q0", false, aTransitions);
		State b = new State("q1", false, bTransitions);
		State c = new State("q2", true, cTransitions);
		
		Set<State> states = new TreeSet<State>();
		states.add(a);
		states.add(b);
		states.add(c);
		
		aTransitions.add('a', a);
		aTransitions.add(RegularExpression.EPSILON, b);
		bTransitions.add('b', b);
		bTransitions.add(RegularExpression.EPSILON, c);
		cTransitions.add('c', c);
		FiniteAutomaton f = new FiniteAutomaton(states, a);

		assertEquals(false, f.recognize("bbaa"));
		assertEquals(false, f.recognize("cca"));
		assertEquals(false, f.recognize("ccb"));
		assertEquals(true, f.recognize(""));
		assertEquals(true, f.recognize("aa"));
		assertEquals(true, f.recognize("aab"));
		assertEquals(true, f.recognize("abc"));
		assertEquals(true, f.recognize("bb"));
		assertEquals(true, f.recognize("bbcccc"));
		assertEquals(true, f.recognize("c"));
	}

	@Test
	public void testIsEquivalentTo() throws CloneNotSupportedException {
		FiniteAutomaton bDeterministic = (FiniteAutomaton) this.automatonB.clone();
		bDeterministic.determinize();
		
		assertEquals(false, this.automatonA.isEquivalentTo(this.automatonB));
		assertEquals(true, this.automatonB.isEquivalentTo(bDeterministic));
	}
	
	@Test
	public void testContains() {
		assertEquals(false, this.automatonA.contains(this.automatonB));
		assertEquals(false, this.automatonA.contains(this.automatonC));
		assertEquals(true, this.automatonC.contains(this.automatonA));
		assertEquals(true, this.automatonC.contains(this.automatonB));
	}

	@Test
	public void testContains2() {
		List<FiniteAutomaton> intersection = this.automatonA.intersection(this.automatonB);
		FiniteAutomaton fa = intersection.get(intersection.size() - 1);
		assertEquals(false, fa.contains(this.automatonA));
		assertEquals(false, fa.contains(this.automatonB));
		assertEquals(true, this.automatonA.contains(fa));
		assertEquals(true, this.automatonB.contains(fa));
	}

	@Test
	public void testContains3() {
		FiniteAutomaton union = this.automatonA.union(this.automatonB);
		assertEquals(false, this.automatonA.contains(union));
		assertEquals(false, this.automatonB.contains(union));
		assertEquals(true, union.contains(this.automatonA));
		assertEquals(true, union.contains(this.automatonB));
	}

	@Test
	public void testComplete() throws InvalidTransitionException {
		TransitionMap aTransitions = new TransitionMap();
		TransitionMap bTransitions = new TransitionMap();
		TransitionMap cTransitions = new TransitionMap();

		State a = new State("q0", false, aTransitions);
		State b = new State("q1", true, bTransitions);
		State c = new State("q2", true, cTransitions);
		
		Set<State> states = new TreeSet<State>();
		states.add(a);
		states.add(b);
		states.add(c);
		
		aTransitions.add(RegularExpression.EPSILON, b);
		aTransitions.add(RegularExpression.EPSILON, c);
		bTransitions.add('a', b);
		cTransitions.add('b', c);
		FiniteAutomaton f = new FiniteAutomaton(states, a);
		f.complete();

		assertEquals(1, a.transit('a').size());
		assertEquals(1, a.transit('b').size());
		assertEquals(1, b.transit('b').size());
		assertEquals(1, c.transit('a').size());
	}
	
	@Test
	public void testDeterminize() {
		assertEquals(false, this.automatonB.isDeterministic());		
		this.automatonB.determinize();
		assertEquals(true, this.automatonB.isDeterministic());	

		assertEquals(false, this.automatonB.recognize(""));
		assertEquals(false, this.automatonB.recognize("bbbbaaa"));
		assertEquals(false, this.automatonB.recognize("aaaa"));
		assertEquals(false, this.automatonB.recognize("bb"));
		assertEquals(true, this.automatonB.recognize("ab"));
		assertEquals(true, this.automatonB.recognize("abbbbbbbaa"));
		assertEquals(true, this.automatonB.recognize("aaaaaabb"));
		assertEquals(true, this.automatonB.recognize("bbbbbaaaaab"));
	}

	@Test
	public void testDeterminize2() {
		assertEquals(true, this.automatonA.isDeterministic());		
		this.automatonA.determinize();
		assertEquals(true, this.automatonA.isDeterministic());	

		assertEquals(false, this.automatonA.recognize("asdbae"));
		assertEquals(false, this.automatonA.recognize("aababbba"));
		assertEquals(true, this.automatonA.recognize(""));
		assertEquals(true, this.automatonA.recognize("aaabb"));
		assertEquals(true, this.automatonA.recognize("abbababbbbbb"));
		assertEquals(true, this.automatonA.recognize("bbbb"));
	}
	
	@Test
	public void testDeterminize3() {
		TransitionMap aTransitions = new TransitionMap();
		TransitionMap bTransitions = new TransitionMap();
		TransitionMap cTransitions = new TransitionMap();

		State a = new State("q0", false, aTransitions);
		State b = new State("q1", true, bTransitions);
		State c = new State("q2", true, cTransitions);
		
		Set<State> states = new TreeSet<State>();
		states.add(a);
		states.add(b);
		states.add(c);
		
		aTransitions.add(RegularExpression.EPSILON, b);
		aTransitions.add(RegularExpression.EPSILON, c);
		bTransitions.add('a', b);
		cTransitions.add('b', c);
		FiniteAutomaton f = new FiniteAutomaton(states, a);

		assertEquals(false, f.isDeterministic());
		f.determinize();
		assertEquals(true, f.isDeterministic());
		
		assertEquals(false, f.recognize(""));
		assertEquals(false, f.recognize("aaab"));
		assertEquals(false, f.recognize("ba"));
		assertEquals(false, f.recognize("aabbab"));
		assertEquals(true, f.recognize("a"));
		assertEquals(true, f.recognize("bbb"));
	}
	
	@Test
	public void testDeterminize4() {
		TransitionMap aTransitions = new TransitionMap();
		TransitionMap bTransitions = new TransitionMap();
		TransitionMap cTransitions = new TransitionMap();

		State a = new State("q0", false, aTransitions);
		State b = new State("q1", false, bTransitions);
		State c = new State("q2", true, cTransitions);
		
		Set<State> states = new TreeSet<State>();
		states.add(a);
		states.add(b);
		states.add(c);
		
		aTransitions.add('a', b);
		bTransitions.add(RegularExpression.EPSILON, c);
		bTransitions.add('b', b);
		cTransitions.add('a', c);
		cTransitions.add('b', c);
		FiniteAutomaton f = new FiniteAutomaton(states, a);

		assertEquals(false, f.isDeterministic());
		f.determinize();
		assertEquals(true, f.isDeterministic());
		
		assertEquals(false, f.recognize(""));
		assertEquals(false, f.recognize("b"));
		assertEquals(false, f.recognize("baa"));
		assertEquals(true, f.recognize("aabababbabb"));
		assertEquals(true, f.recognize("a"));
		assertEquals(true, f.recognize("abbb"));
	}
	
	@Test
	public void testMinimize() {
		// TODO
	}

	@Test
	public void testIsComplete() {
		TransitionMap aTransitions = new TransitionMap();
		TransitionMap bTransitions = new TransitionMap();
		TransitionMap cTransitions = new TransitionMap();

		State a = new State("q0", false, aTransitions);
		State b = new State("q1", true, bTransitions);
		State c = new State("q2", true, cTransitions);
		
		Set<State> states = new TreeSet<State>();
		states.add(a);
		states.add(b);
		states.add(c);
		
		aTransitions.add(RegularExpression.EPSILON, b);
		aTransitions.add(RegularExpression.EPSILON, c);
		bTransitions.add('a', b);
		cTransitions.add('b', c);
		FiniteAutomaton f = new FiniteAutomaton(states, a);
		
		assertEquals(false, f.isComplete());
		f.complete();
		assertEquals(true, f.isComplete());		
	}

	@Test
	public void testIsDeterministic() {
		assertEquals(true, this.automatonA.isDeterministic());
		assertEquals(false, this.automatonB.isDeterministic());

		TransitionMap transitions = new TransitionMap();
		State a = new State("test", false, transitions);
		Set<State> states = new TreeSet<State>();
		states.add(a);
		this.automatonA.getInitialState().getTransitionMap().getMap().put(RegularExpression.EPSILON, states);
		assertEquals(false, this.automatonA.isDeterministic());		
	}

	@Test
	public void testIsDeterministic2() {
		TransitionMap aTransitions = new TransitionMap();
		TransitionMap bTransitions = new TransitionMap();
		TransitionMap cTransitions = new TransitionMap();

		State a = new State("q0", false, aTransitions);
		State b = new State("q1", true, bTransitions);
		State c = new State("q2", true, cTransitions);
		
		Set<State> states = new TreeSet<State>();
		states.add(a);
		states.add(b);
		states.add(c);
		
		aTransitions.add(RegularExpression.EPSILON, b);
		aTransitions.add(RegularExpression.EPSILON, c);
		bTransitions.add('a', b);
		cTransitions.add('b', c);
		FiniteAutomaton f = new FiniteAutomaton(states, a);
		
		assertEquals(false, f.isDeterministic());
	}

	@Test
	public void testIsDeterministic3() {
		TransitionMap aTransitions = new TransitionMap();
		TransitionMap bTransitions = new TransitionMap();

		State a = new State("q0", false, aTransitions);
		State b = new State("q1", true, bTransitions);
		
		Set<State> states = new TreeSet<State>();
		states.add(a);
		states.add(b);
		
		aTransitions.add(RegularExpression.EPSILON, b);
		bTransitions.add('a', b);
		FiniteAutomaton f = new FiniteAutomaton(states, a);
		
		assertEquals(true, f.isDeterministic());
	}
	
	@Test
	public void testIsMinimal() {
		// TODO
	}

	@Test
	public void testIsEmpty() {
		TransitionMap aTransitions = new TransitionMap();
		TransitionMap bTransitions = new TransitionMap();
		TransitionMap cTransitions = new TransitionMap();

		State a = new State("q0", false, aTransitions);
		State b = new State("q1", true, bTransitions);
		State c = new State("q2", false, cTransitions);
		
		Set<State> states = new TreeSet<State>();
		states.add(a);
		states.add(b);
		states.add(c);
		
		aTransitions.add('a', a);
		aTransitions.add('b', a);
		aTransitions.add(RegularExpression.EPSILON, c);
		bTransitions.add('a', a);
		cTransitions.add('b', c);
		FiniteAutomaton f = new FiniteAutomaton(states, a);
		
		assertEquals(false, this.automatonA.isEmpty());
		assertEquals(false, this.automatonB.isEmpty());
		assertEquals(false, this.automatonC.isEmpty());
		assertEquals(true, f.isEmpty());
	}
	
	@Test
	public void testUnion() {
		FiniteAutomaton union = this.automatonA.union(this.automatonB);
		
		assertEquals(false, union.recognize("aa"));
		assertEquals(false, union.recognize("bbba"));
		assertEquals(true, union.recognize(""));
		assertEquals(true, union.recognize("aaa"));
		assertEquals(true, union.recognize("ab"));
		assertEquals(true, union.recognize("bbaab"));
		assertEquals(true, union.recognize("bbaaabb"));
	}
	
	@Test
	public void testIntersection() {
		List<FiniteAutomaton> intersection = this.automatonA.intersection(this.automatonB);
		FiniteAutomaton fa = intersection.get(intersection.size() - 1);

		assertEquals(false, fa.recognize(""));
		assertEquals(false, fa.recognize("bbbb"));
		assertEquals(false, fa.recognize("aaa"));
		assertEquals(false, fa.recognize("bbaa"));
		assertEquals(false, fa.recognize("bbaab"));
		assertEquals(true, fa.recognize("abaa"));
		assertEquals(true, fa.recognize("aaabbbb"));
		assertEquals(true, fa.recognize("bababaabbaa"));
	}

	@Test
	public void testComplement() {
		FiniteAutomaton automatonComplement = this.automatonA.complement();
		
		assertEquals(true, automatonComplement.recognize("ab"));
		assertEquals(true, automatonComplement.recognize("aa"));
		assertEquals(true, automatonComplement.recognize("aababbba"));
		assertEquals(false, automatonComplement.recognize("asdbae"));
		assertEquals(false, automatonComplement.recognize(""));
		assertEquals(false, automatonComplement.recognize("aaabb"));
		assertEquals(false, automatonComplement.recognize("abbababbbbbb"));
		assertEquals(false, automatonComplement.recognize("bbbb"));
	}
	
}
