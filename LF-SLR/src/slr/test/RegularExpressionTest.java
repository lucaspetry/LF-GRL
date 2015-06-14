package slr.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import slr.automaton.FiniteAutomaton;
import slr.exception.InvalidRegularExpressionException;
import slr.expression.RegularExpression;

public class RegularExpressionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected=InvalidRegularExpressionException.class)
	public void testConstructor() throws InvalidRegularExpressionException {
		new RegularExpression("((A|b)+ab)?a*");
	}

	@Test(expected=InvalidRegularExpressionException.class)
	public void testConstructor2() throws InvalidRegularExpressionException {
		new RegularExpression("((a|b)+ab)?a*(");
	}

	@Test
	public void testConstructor3() {
		try {
			new RegularExpression("((a|b)+ab)?a*");
		} catch (InvalidRegularExpressionException e) {
			fail();
		}
	}
	
	@Test
	public void testStandardize() throws InvalidRegularExpressionException {
		RegularExpression regex = new RegularExpression("((a|b)+ab)?a*");
		regex.standardize();
		assertEquals("((a|b).(a|b)*.a.b)?.a*", regex.toString());

		RegularExpression regex2 = new RegularExpression("ab*c|aba((ba)+c)");
		regex2.standardize();
		assertEquals("a.b*.c|a.b.a.((b.a).(b.a)*.c)", regex2.toString());

		RegularExpression regex3 = new RegularExpression("ab*c | aba\n((ba)+c)");
		regex3.standardize();
		assertEquals("a.b*.c|a.b.a.((b.a).(b.a)*.c)", regex3.toString());
	}

	@Test
	public void testToFiniteAutomaton() throws InvalidRegularExpressionException {
		RegularExpression regex = new RegularExpression("");
		FiniteAutomaton fa = regex.toFiniteAutomaton();

		assertEquals(false, fa.recognize(""));
		assertEquals(false, fa.recognize("ababbaab"));
		assertEquals(false, fa.recognize("&"));
		assertEquals(false, fa.recognize("a"));
		assertEquals(false, fa.recognize("b"));
	}

	@Test
	public void testToFiniteAutomaton2() throws InvalidRegularExpressionException {
		RegularExpression regex = new RegularExpression("(0*(1(01*0)*1)*0*)*");
		FiniteAutomaton fa = regex.toFiniteAutomaton();

		assertEquals(false, fa.recognize("abba"));
		assertEquals(false, fa.recognize("1"));
		assertEquals(false, fa.recognize("10"));
		assertEquals(false, fa.recognize("100010"));
		assertEquals(true, fa.recognize(""));
		assertEquals(true, fa.recognize("0"));
		assertEquals(true, fa.recognize("0000"));
		assertEquals(true, fa.recognize("11"));
		assertEquals(true, fa.recognize("1001"));
		assertEquals(true, fa.recognize("110110"));
	}

	@Test
	public void testToFiniteAutomaton3() throws InvalidRegularExpressionException {
		RegularExpression regex = new RegularExpression("b*a(b*ab*ab*)*b*");
		FiniteAutomaton fa = regex.toFiniteAutomaton();

		assertEquals(false, fa.recognize(""));
		assertEquals(false, fa.recognize("bbb"));
		assertEquals(false, fa.recognize("bbabbabbb"));
		assertEquals(false, fa.recognize("aa"));
		assertEquals(true, fa.recognize("a"));
		assertEquals(true, fa.recognize("bbbabb"));
		assertEquals(true, fa.recognize("ababbaaa"));
		assertEquals(true, fa.recognize("bbbbbba"));
		assertEquals(true, fa.recognize("baabbab"));
		assertEquals(true, fa.recognize("aaaaa"));
	}

	@Test
	public void testToFiniteAutomaton4() throws InvalidRegularExpressionException {
		RegularExpression regex = new RegularExpression("(aa | bb)*");
		FiniteAutomaton fa = regex.toFiniteAutomaton();

		assertEquals(false, fa.recognize("a"));
		assertEquals(false, fa.recognize("bbb"));
		assertEquals(false, fa.recognize("aabbabb"));
		assertEquals(false, fa.recognize("babb"));
		assertEquals(true, fa.recognize(""));
		assertEquals(true, fa.recognize("bb"));
		assertEquals(true, fa.recognize("bbaaaa"));
		assertEquals(true, fa.recognize("aabbaa"));
		assertEquals(true, fa.recognize("aaaaaaaa"));
	}
	
}
