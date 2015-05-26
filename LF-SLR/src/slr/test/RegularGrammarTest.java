package slr.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import slr.automaton.FiniteAutomaton;
import slr.exception.InvalidProductionException;
import slr.grammar.ProductionMap;
import slr.grammar.RegularGrammar;

public class RegularGrammarTest {
	
	private RegularGrammar grammarA;

	@Before
	public void setUp() throws InvalidProductionException {
		String productionsA = "S -> aA|bB\n" +
							  "A -> aA|bB|a\n" +
							  "B-> bB|b";
		this.grammarA = new RegularGrammar(productionsA);
	}

	@Test
	public void testConstructor() throws InvalidProductionException {
		String productions = "A -> aA|b|bC\n" +
							  "C -> a|bC";
		new RegularGrammar(productions);
	}

	@Test
	public void testConstructor2() throws InvalidProductionException {
		String productions = "A -> aA|b|bC\n" +
							  "C -> a|bD";
		new RegularGrammar(productions);
	}

	@Test
	public void testConstructor3() throws InvalidProductionException {
		String productions = "A -> aA";
		new RegularGrammar(productions);
	}

	@Test(expected=InvalidProductionException.class)
	public void testConstructor4() throws InvalidProductionException {
		String productions = "A -> aA|b|bC\n" +
	  			 			 "E -> bE|b" + // Sem a quebra de linha!
				  			 "C -> a|bD";
		new RegularGrammar(productions);
	}
	
	@Test(expected=InvalidProductionException.class)
	public void testConstructor5() throws InvalidProductionException {
		ProductionMap p = new ProductionMap();
		p.add('S', "aS");
		new RegularGrammar(p, 'Z');
	}

	@Test
	public void testConstructor6() throws InvalidProductionException {
		ProductionMap p = new ProductionMap();
		p.add('S', "aS");
		p.add('S', "bA");
		p.add('A', "b");
		new RegularGrammar(p, 'S');
	}
	
	@Test
	public void testToString() {
		assertEquals(this.grammarA.toString().charAt(0), this.grammarA.getInitialSymbol());
		// TODO
	}

	@Test
	public void testToFiniteAutomaton() throws InvalidProductionException {
		String productions = "A -> aA|b|bC\n" +
							 "C -> a|bD";
		RegularGrammar g = new RegularGrammar(productions);
		FiniteAutomaton fG = g.toFiniteAutomaton();
		
		assertEquals(true, fG.recognize("b"));
		assertEquals(true, fG.recognize("aaaab"));
		assertEquals(true, fG.recognize("aaba"));
		assertEquals(false, fG.recognize("aaaabbb"));
		assertEquals(false, fG.recognize("aaaaaa"));
	}

	@Test
	public void testToFiniteAutomaton2() throws InvalidProductionException {
		String productions = "A -> aA|b|bC\n" +
							 "E -> bE|a\n" +
							 "C -> a|bD";
		RegularGrammar g = new RegularGrammar(productions);
		FiniteAutomaton fG = g.toFiniteAutomaton();
		
		assertEquals(true, fG.recognize("b"));
		assertEquals(true, fG.recognize("aaaab"));
		assertEquals(true, fG.recognize("aaba"));
		assertEquals(false, fG.recognize("aaaabbb"));
		assertEquals(false, fG.recognize("aaaaaa"));
	}

}
