package slr.expression;


/**
 * Árvore Binária.
 * @author lucas
 *
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
	
	public BinaryTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode<T> root) {
		this.root = root;
	}
	
}
