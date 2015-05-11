package slf.test;

import static org.junit.Assert.*;

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

		this.a = new State("aState", false, aTransitions);
		this.b = new State("bState", true, bTransitions);
		this.c = new State("cState", false, cTransitions);
		
		aTransitions.add('a', this.a);
		aTransitions.add('b', this.b);
		bTransitions.add('a', this.b);
		bTransitions.add('b', this.c);
		cTransitions.add('b', this.c);
		cTransitions.add('b', this.b);
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
			assertEquals(this.b, this.a.transit('b').get(0));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}

	@Test
	public void testTransitMultiple() {
		try {
			assertEquals(2, this.c.transit('b').size());
			assertEquals(this.c, this.c.transit('b').get(0));
			assertEquals(this.b, this.c.transit('b').get(1));
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
