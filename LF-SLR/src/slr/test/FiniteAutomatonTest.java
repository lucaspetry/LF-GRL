package slr.test;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import slr.automaton.FiniteAutomaton;
import slr.automaton.State;
import slr.automaton.TransitionMap;

public class FiniteAutomatonTest {

	private FiniteAutomaton automatonA;
	private Set<State> automatonAfinalStates;
	private Set<State> automatonAnotFinalStates;
	
	@Before
	public void setUp() throws Exception {		
		TransitionMap aTransitions = new TransitionMap();
		TransitionMap bTransitions = new TransitionMap();
		TransitionMap cTransitions = new TransitionMap();

		State a = new State("aState", true, aTransitions);
		State b = new State("bState", false, bTransitions);
		State c = new State("cState", false, cTransitions);
		
		Set<State> states = new TreeSet<State>();
		states.add(a);
		states.add(b);
		states.add(c);
		
		aTransitions.add('a', b);
		aTransitions.add('b', a);
		bTransitions.add('a', c);
		bTransitions.add('b', b);
		cTransitions.add('a', a);
		cTransitions.add('b', c);
		
		this.automatonA = new FiniteAutomaton(states, "ab", a);
		this.automatonAfinalStates = new TreeSet<State>();
		this.automatonAfinalStates.add(a);
		this.automatonAnotFinalStates = new TreeSet<State>();
		this.automatonAnotFinalStates.add(b);
		this.automatonAnotFinalStates.add(c);
	}

	@Test
	public void testComplement() {
		FiniteAutomaton automatonComplement = this.automatonA.complement();
		assertEquals(this.automatonAnotFinalStates, automatonComplement.getFinalStates());
		assertEquals(this.automatonAfinalStates, automatonComplement.getNotFinalStates());
	}
	
}
