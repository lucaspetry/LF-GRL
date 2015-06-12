package slr.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import slr.exception.InvalidRegularExpressionException;
import slr.expression.BinaryTreeNode;
import slr.expression.RegularExpression;
import slr.expression.SyntaxTree;

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
	public void testGetSyntaxTree() throws InvalidRegularExpressionException {
		RegularExpression regex = new RegularExpression("0*(1(01*0)*1)*0*");
		SyntaxTree tree = regex.getSyntaxTree();
		BinaryTreeNode<Character> node = tree.getRoot().getRightNode().getLeftNode().getLeftNode().getRightNode().getRightNode();
		
		System.out.println(node.getValue() + " - Cost: " + node.getSeam());
	}
	
}
