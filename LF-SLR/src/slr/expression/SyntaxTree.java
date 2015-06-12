package slr.expression;

import java.util.Set;

/**
 * Árvore sintática.
 */
public class SyntaxTree extends BinaryTree<Character> {

	private StringBuilder regex;
	
	/**
	 * Construtor.
	 * @param regex expressão regular.
	 */
	public SyntaxTree(RegularExpression regex) {
		regex.standardize();
		this.regex = new StringBuilder(regex.toString());
		this.buildTree();
		this.sewTree();
	}
	
	/**
	 * Obter os nodos folhas da árvore.
	 * @return conjunto de folhas.
	 */
	public Set<BinaryTreeNode<Character>> getLeaves() {
		return this.getRoot().getLeaves();
	}
	
	/**
	 * Construir a árvore
	 * @param regex
	 */
	private void buildTree() {
		this.setRoot(expr());
	}
	
	/**
	 * Costurar a árvore.
	 */
	private void sewTree() {
		if(this.getRoot() != null)
			this.getRoot().sewNode(null, new BinaryTreeNode<Character>(RegularExpression.EPSILON));
	}

	/**
	 * Método auxiliar: parênteses e caractere.
	 * @return nodo criado.
	 */
	private BinaryTreeNode<Character> atom() {
		BinaryTreeNode<Character> node = null;
		char c = peek();
		if(c == RegularExpression.PARENTHESIS_OPENING) {
			c = pop();
			node = expr();
			c = pop();
			if(c != RegularExpression.PARENTHESIS_CLOSING) {
				assert false;
			}
		} else if(RegularExpression.ALPHABET.contains(c + "")) {
			c = pop();
			node = new BinaryTreeNode<Character>(c);
		}
		return node;
	}

	/**
	 * Método auxiliar: fechamento ou opção.
	 * @return nodo criado.
	 */
	private BinaryTreeNode<Character> single() {
		BinaryTreeNode<Character> left = atom();
		Character c = peek();
		BinaryTreeNode<Character> node = null;
		if(c.equals(RegularExpression.KLEENE_STAR_CLOSURE) || c.equals(RegularExpression.OPTIONAL)) {
			c = pop();
			node = new BinaryTreeNode<Character>(c);
			node.setLeftNode(left);
		} else {
			node = left;
		}
		return node;
	}

	/**
	 * Método auxiliar: concatenação.
	 * @return nodo criado.
	 */
	private BinaryTreeNode<Character> concat() {
		BinaryTreeNode<Character> left = single();
		Character c = peek();
		BinaryTreeNode<Character> node = null;
		if(c.equals(RegularExpression.CONCATENATION)) {
			c = pop();
			node = new BinaryTreeNode<Character>(c);
			node.setLeftNode(left);
			BinaryTreeNode<Character> right = concat();
			node.setRightNode(right);
		} else {
			node = left;
		}
		return node;
	}

	/**
	 * Método auxiliar: expressão.
	 * @return nodo criado.
	 */
	private BinaryTreeNode<Character> expr() {
		BinaryTreeNode<Character> left = concat();
		BinaryTreeNode<Character> node = null;
		Character c = peek();
		if(c.equals(RegularExpression.OR)) {
			c = pop();
			node = new BinaryTreeNode<Character>(c);
			node.setLeftNode(left);
			BinaryTreeNode<Character> right = expr();
			node.setRightNode(right);
		} else {
			node = left;
		}
		return node;
	}

	/**
	 * Obter o próximo caractere.
	 * @return próximo caractere.
	 */
	private Character peek() {
		if(this.regex.length() > 0)
			return this.regex.charAt(0);
		
		return '\0';
	}

	/**
	 * Remover o próximo caractere.
	 * @return caractere removido.
	 */
	private Character pop() {
		Character c = this.regex.charAt(0);
		this.regex.deleteCharAt(0);
		return c;
	}

}
