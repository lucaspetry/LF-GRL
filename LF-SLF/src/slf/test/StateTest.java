package slf.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import slf.automaton.State;
import slf.exception.InvalidTransitionException;

public class StateTest {

	@Before
	public void setUp() throws Exception {		
		HashMap<Character, State> aTransitions = new HashMap<Character, State>();
		HashMap<Character, State> bTransitions = new HashMap<Character, State>();
		HashMap<Character, State> cTransitions = new HashMap<Character, State>();

		this.a = new State("aState", false, aTransitions);
		this.b = new State("bState", true, bTransitions);
		this.c = new State("cState", false, cTransitions);
		
		aTransitions.put('a', this.a);
		aTransitions.put('b', this.b);
		bTransitions.put('a', this.b);
		bTransitions.put('b', this.c);
		cTransitions.put('b', this.b);
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
	public void testTransit() {
		try {
			assertEquals(this.b, this.a.transit('b'));
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
	public void testEqualsObject() {
		assertEquals(false, this.a.equals(this.b));
		assertEquals(true, this.a.equals(this.a));
	}
	
	private State a;
	private State b;
	private State c;

}
