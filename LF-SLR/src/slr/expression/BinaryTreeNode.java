package slr.expression;

/**
 * Nodo de árvore binária.
 * @param <T>
 */
public class BinaryTreeNode<T> {

	private static int NODE_NUMBER = 1;
	private T value;
	private BinaryTreeNode<T> seam;
	private BinaryTreeNode<T> leftNode;
	private BinaryTreeNode<T> rightNode;
	private int number;
	
	/**
	 * Construtor.
	 * @param value valor do nodo.
	 */
	public BinaryTreeNode(T value) {
		this.setValue(value);
		this.setSeam(null);
		this.setLeftNode(null);
		this.setRightNode(null);
		this.number = NODE_NUMBER;
		NODE_NUMBER++;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof BinaryTreeNode<?>) {
			BinaryTreeNode<?> node = (BinaryTreeNode<?>) o;
			if(node.number == this.number)
				return true;
		}
		
		return false;
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
		return this.value;
	}

	/**
	 * Definir o valor do nodo.
	 * param value valor do nodo.
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
	/**
	 * Obter o nodo da costura.
	 * @return costura.
	 */
	public BinaryTreeNode<T> getSeam() {
		return this.seam;
	}

	/**
	 * Definir a costura.
	 * @param seam nodo de costura.
	 */
	public void setSeam(BinaryTreeNode<T> seam) {
		this.seam = seam;
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
	
	public void sewNode(BinaryTreeNode<T> parent, BinaryTreeNode<T> lastLeftChildParent) {
		if(parent != null) { // Nodo intermediário ou folha
			if(!this.value.equals(RegularExpression.CONCATENATION)) { // Se for *, ? ou folha, então costura
				if(this.equals(parent.getLeftNode()))
					this.setSeam(parent);
				else
					this.setSeam(lastLeftChildParent);
			}

			if(this.getLeftNode() != null) { // Costura filho da esquerda
				this.getLeftNode().sewNode(this, this);
			}

			if(this.getRightNode() != null) { // Costura filho da direita
				this.getRightNode().sewNode(this, lastLeftChildParent);
			}
		} else { // Nodo raiz da árvore
			if(this.getLeftNode() != null) {
				this.getLeftNode().sewNode(this, this);
			}

			if(this.getRightNode() != null) {
				this.getRightNode().sewNode(this, lastLeftChildParent);
			}
		}
	}
	
}
