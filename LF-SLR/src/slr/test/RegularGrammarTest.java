package slr.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import slr.grammar.RegularGrammar;

public class RegularGrammarTest {
	
	private RegularGrammar grammarA;

	@Before
	public void setUp() throws Exception {
		String productions = "S -> aA|bB\n" +
							 "A -> aA|bB|a\n" +
							 "B-> bB|b";
		
		this.grammarA = new RegularGrammar(productions);
	}

	@Test
	public void testToString() {
		assertEquals(this.grammarA.toString().charAt(0), this.grammarA.getInitialSymbol());
	}

}
