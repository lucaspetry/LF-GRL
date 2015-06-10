package slr;

import slr.automaton.FiniteAutomaton;

/**
 * Dispositivo regular.
 */
public interface RegularDevice {

	/**
	 * Converter o dispositivo em um autômato finito.
	 * @return autômato finito equivalente.
	 */
	public FiniteAutomaton toFiniteAutomaton();
	
}
