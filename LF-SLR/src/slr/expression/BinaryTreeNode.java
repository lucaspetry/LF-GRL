package slr.expression;

import java.util.HashSet;
import java.util.Set;

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
	 * Obter o número do nodo.
	 * @return número.
	 */
	public int getNumber() {
		return this.number;
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
	
	/**
	 * Costurar o nodo.
	 * @param parent nodo pai.
	 * @param lastLeftChildParent último nodo pai de um filho da esquerda.
	 */
	public void sewNode(BinaryTreeNode<T> parent, BinaryTreeNode<T> lastLeftChildParent) {
		if(parent != null) { // Nodo intermediário ou folha
			if(!this.value.equals(RegularExpression.CONCATENATION) &&
				!this.value.equals(RegularExpression.OR)) { // Se for *, ? ou folha, então costura
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
			} else {
				this.setSeam(lastLeftChildParent);
			}
		}
	}

	/**
	 * Obter as folhas a partir desse nodo.
	 * @return conjunto de nodos folha.
	 */
	public Set<BinaryTreeNode<T>> getLeaves() {
		boolean leftNode = this.getLeftNode() != null;
		boolean rightNode = this.getRightNode() != null;
						
		if(!leftNode && !rightNode) { // Nodo folha
			Set<BinaryTreeNode<T>> nodes = new HashSet<BinaryTreeNode<T>>();
			nodes.add(this);
			return nodes;
		} else if(leftNode && rightNode) { // Dois filhos existem
			Set<BinaryTreeNode<T>> nodesLeft = this.getLeftNode().getLeaves();
			nodesLeft.addAll(this.getRightNode().getLeaves());
			return nodesLeft;
		} else if(leftNode && !rightNode) { // Apenas filho esquerdo existe
			return this.getLeftNode().getLeaves();
		} else { // Apenas filho direito existe
			return this.getRightNode().getLeaves();
		}
	}
	
	/**
	 * Obter os nodos alcançáveis a partir desse através das rotinas Descer e Subir.
	 * @return conjunto de nodos alcançáveis.
	 */
	public Set<BinaryTreeNode<T>> getReachableNodes() {
		if(this.isLeaf())
			return this.getReachableNodesUp();
		
		return this.getReachableNodesDown();
	}

	/**
	 * Obter os nodos alcançáveis a partir desse através da rotina Descer.
	 * @return conjunto de nodos alcançáveis.
	 */
	private Set<BinaryTreeNode<T>> getReachableNodesDown() {
		
		if(this.isLeaf()) { // Nodo folha
			Set<BinaryTreeNode<T>> nodes = new HashSet<BinaryTreeNode<T>>();
			nodes.add(this);
			return nodes;
			
		} else if(this.value.equals(RegularExpression.KLEENE_STAR_CLOSURE) ||
					this.value.equals(RegularExpression.OPTIONAL)) { // * ou ?
			Set<BinaryTreeNode<T>> left = this.getLeftNode().getReachableNodesDown();
			if(this.getSeam() != null)
				left.addAll(this.getSeam().getReachableNodesUp());
			return left;
			
		} else if(this.value.equals(RegularExpression.OR)) { // |
			Set<BinaryTreeNode<T>> left = this.getLeftNode().getReachableNodesDown();
			left.addAll(this.getRightNode().getReachableNodesDown());
			return left;
			
		} else { // .
			return this.getLeftNode().getReachableNodesDown();
		}
	}

	/**
	 * Obter os nodos alcançáveis a partir desse através da rotina Subir.
	 * @return conjunto de nodos alcançáveis.
	 */
	private Set<BinaryTreeNode<T>> getReachableNodesUp() {
		
		if(this.value.equals(RegularExpression.EPSILON)) { // Epsilon
			Set<BinaryTreeNode<T>> nodes = new HashSet<BinaryTreeNode<T>>();
			nodes.add(this);
			return nodes;
		} else if(this.isLeaf()) { // Nodo folha
			return this.getSeam().getReachableNodesUp();
		} else if(this.value.equals(RegularExpression.KLEENE_STAR_CLOSURE)) { // *
			Set<BinaryTreeNode<T>> left = this.getLeftNode().getReachableNodesDown();
			left.addAll(this.getSeam().getReachableNodesUp());
			return left;
			
		} else if(this.value.equals(RegularExpression.OPTIONAL)) { // ?
			return this.getSeam().getReachableNodesUp();			
			
		} else if(this.value.equals(RegularExpression.OR)) { // |
			BinaryTreeNode<T> node = this.getRightNode();
			
			while(node.getRightNode() != null)
				node = node.getRightNode();

			return node.getSeam().getReachableNodesUp();
			
		} else { // .
			return this.getRightNode().getReachableNodesDown();
			
		}
	}
	
}
