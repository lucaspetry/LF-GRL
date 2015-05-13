package slf.test;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import slf.automaton.State;
import slf.automaton.TransitionMap;
import slf.exception.InvalidTransitionException;

public class StateTest {

	@Before
	public void setUp() throws Exception {		
		TransitionMap aTransitions = new TransitionMap();
		TransitionMap bTransitions = new TransitionMap();
		TransitionMap cTransitions = new TransitionMap();
		TransitionMap dTransitions = new TransitionMap();

		this.a = new State("aState", false, aTransitions);
		this.b = new State("bState", true, bTransitions);
		this.c = new State("cState", false, cTransitions);
		this.d = new State("dState", false, dTransitions);
		
		aTransitions.add('a', this.a);
		aTransitions.add('b', this.b);
		bTransitions.add('a', this.b);
		bTransitions.add('b', this.c);
		cTransitions.add('b', this.c);
		cTransitions.add('b', this.b);
		dTransitions.add('a', this.b);
		dTransitions.add('a', this.d);
		dTransitions.add('b', this.c);
	}

	@Test
	public void testGetName() {
		assertEquals("aState", this.a.getName());
	}

	@Test
	public void testIsFinal() {
		assertTrue(!this.a.isFinal());
		assertTrue(this.b.isFinal());
	}

	@Test
	public void testTransitSingle() {
		try {
			assertEquals(1, this.a.transit('b').size());
			assertEquals(this.b, this.a.transit('b').toArray()[0]);
		} catch (InvalidTransitionException e) {
			fail();
		}
	}

	@Test
	public void testTransitMultiple() {
		try {
			assertEquals(2, this.c.transit('b').size());
			assertTrue(this.c.transit('b').contains(this.b));
			assertTrue(this.c.transit('b').contains(this.c));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}

	@Test
	public void testTransitInvalid() {
		try {
			this.c.transit('a');
			fail();
		} catch (InvalidTransitionException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetReachableStates() {
		Set<State> reachableD = new TreeSet<State>();
		reachableD.add(this.b);
		reachableD.add(this.d);
		reachableD.add(this.c);
		assertEquals(reachableD, this.d.getReachableStates());
		assertEquals(3, this.d.getReachableStates().size());
	}
	
	@Test
	public void testEqualsObject() {
		assertEquals(false, this.a.equals(this.b));
		assertEquals(true, this.a.equals(this.a));
	}
	
	private State a;
	private State b;
	private State c;
	private State d;

}
