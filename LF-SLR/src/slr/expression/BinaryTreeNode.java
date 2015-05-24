package slr.expression;

/**
 * Nodo de Árvore Binária.
 * @author lucas
 *
 * @param <T>
 */
public class BinaryTreeNode<T> {

	private T value;
	private BinaryTreeNode<T> parentNode;
	private BinaryTreeNode<T> leftNode;
	private BinaryTreeNode<T> rightNode;
	
	/**
	 * Construtor.
	 * @param value valor do nodo.
	 */
	public BinaryTreeNode(T value) {
		this.setValue(value);
		this.setParentNode(null);
		this.setLeftNode(null);
		this.setRightNode(null);
	}
	
	@Override
	public String toString() {
		return this.value.toString();
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public BinaryTreeNode<T> getParentNode() {
		return parentNode;
	}
	
	public void setParentNode(BinaryTreeNode<T> parentNode) {
		this.parentNode = parentNode;
	}

	public BinaryTreeNode<T> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(BinaryTreeNode<T> leftNode) {
		this.leftNode = leftNode;
	}

	public BinaryTreeNode<T> getRightNode() {
		return rightNode;
	}

	public void setRightNode(BinaryTreeNode<T> rightNode) {
		this.rightNode = rightNode;
	}
	
	public boolean isLeaf() {
		return this.leftNode == null && this.rightNode == null;
	}
	
}
