package slr.expression;

/**
 * Nodo de árvore binária.
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

	/**
	 * Obter o valor do nodo.
	 * @return valor.
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Definir o valor do nodo.
	 * param value valor do nodo.
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
	/**
	 * Obter o nodo pai.
	 * @return nodo pai.
	 */
	public BinaryTreeNode<T> getParentNode() {
		return parentNode;
	}

	/**
	 * Definir o nodo pai.
	 * @param parentNode nodo pai.
	 */
	public void setParentNode(BinaryTreeNode<T> parentNode) {
		this.parentNode = parentNode;
	}

	/**
	 * Obter o nodo filho da esquerda.
	 * @return nodo filho da esquerda.
	 */
	public BinaryTreeNode<T> getLeftNode() {
		return leftNode;
	}

	/**
	 * Definir o nodo filho da esquerda.
	 * @param leftNode nodo filho da esquerda.
	 */
	public void setLeftNode(BinaryTreeNode<T> leftNode) {
		this.leftNode = leftNode;
	}

	/**
	 * Obter o nodo filho da direita.
	 * @return nodo filho da direita.
	 */
	public BinaryTreeNode<T> getRightNode() {
		return rightNode;
	}

	/**
	 * Definir o nodo filho da direita.
	 * @param leftNode nodo filho da direita.
	 */
	public void setRightNode(BinaryTreeNode<T> rightNode) {
		this.rightNode = rightNode;
	}
	
	/**
	 * Verificar se o nodo é folha.
	 * @return true caso o nodo não possua filhos.
	 */
	public boolean isLeaf() {
		return this.leftNode == null && this.rightNode == null;
	}
	
}
