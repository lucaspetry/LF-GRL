package slf.automaton;

/**
 * Transição.
 * @author lucas
 *
 */
public class Transition {

	/**
	 * Construtor.
	 * @param origin estado de origem.
	 * @param destination estado de destino.
	 * @param symbol símbolo para ocorrência da transição.
	 */
	public Transition(State origin, State destination, char symbol) {
		this.origin = origin;
		this.destination = destination;
		this.symbol = symbol;
	}
	
	/**
	 * Obter o estado de origem da transição.
	 * @return estado de origem.
	 */
	public State getOrigin() {
		return this.origin;
	}

	/**
	 * Obter o estado de destino da transição.
	 * @return estado de destino.
	 */
	public State getDestination() {
		return this.destination;
	}
	
	/**
	 * Obter o símbolo de ocorrência da transição.
	 * @return símbolo de ocorrência da transição.
	 */
	public char getSymbol() {
		return this.symbol;
	}
	
	private State origin;
	private State destination;
	private char symbol;
	
}
