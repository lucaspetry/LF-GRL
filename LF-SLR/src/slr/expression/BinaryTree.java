package slr.expression;


/**
 * Árvore binária.
 * @param <T>
 */
public class BinaryTree<T> {

	private BinaryTreeNode<T> root;
	
	/**
	 * Construtor.
	 */
	public BinaryTree() {
		this.root = null;
	}
	
	/**
	 * Construtor.
	 * @param root raiz da árvore.
	 */
	public BinaryTree(BinaryTreeNode<T> root) {
		this.root = root;
	}
	
	/**
	 * Obter a raiz da árvore.
	 * @return nodo raiz da árvore.
	 */
	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	/**
	 * Definir a raiz da árvore.
	 * @param root nodo raiz da árvore.
	 */
	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}

	/**
	 * Imprimir a árvore.
	 */
	public void print() {
		this.getRoot().print();
	}
	
}
