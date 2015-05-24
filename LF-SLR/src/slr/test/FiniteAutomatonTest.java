package slr.test;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import slr.automaton.FiniteAutomaton;
import slr.automaton.State;
import slr.automaton.TransitionMap;
import slr.expression.RegularExpression;

public class FiniteAutomatonTest {

	private FiniteAutomaton automatonA;
	private FiniteAutomaton automatonB;
	private Set<State> automatonAfinalStates;
	private Set<State> automatonAnotFinalStates;
	
	@Before
	public void setUp() throws Exception {		
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
	public void testDeterminize() {
		// TODO
	}

	@Test
	public void testMinimize() {
		// TODO
	}

	@Test
	public void testIntersection() {
		// TODO
	}
	
	@Test
	public void testIsDeterministic() {
		assertEquals(true, this.automatonA.isDeterministic());
		assertEquals(false, this.automatonB.isDeterministic());

		TransitionMap transitions = new TransitionMap();
		State a = new State("test", false, transitions);
		Set<State> states = new TreeSet<State>();
		states.add(a);
		this.automatonA.getInitialState().getTransitions().put(RegularExpression.EPSILON, states);
		assertEquals(false, this.automatonA.isDeterministic());		
	}

	@Test
	public void testIsMinimal() {
		// TODO
	}

	@Test
	public void testComplement() {
		FiniteAutomaton automatonComplement = this.automatonA.complement();
		assertEquals(this.automatonAnotFinalStates, automatonComplement.getFinalStates());
		assertEquals(this.automatonAfinalStates, automatonComplement.getNotFinalStates());
	}
	
}
