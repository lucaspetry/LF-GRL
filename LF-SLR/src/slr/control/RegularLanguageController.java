package slr.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import slr.RegularDevice;
import slr.automaton.FiniteAutomaton;
import slr.exception.FiniteAutomatonNotFoundException;
import slr.exception.InvalidProductionException;
import slr.exception.InvalidRegularExpressionException;
import slr.exception.RegularDeviceExistingException;
import slr.exception.RegularDeviceNotFoundException;
import slr.expression.RegularExpression;
import slr.grammar.RegularGrammar;

/**
 * Controlador de linguagens regulares.
 */
public class RegularLanguageController {

	private Map<String, FiniteAutomaton> finiteAutomata;
	private Map<String, RegularDevice> regularDevices;
	
	public RegularLanguageController() {
		this.finiteAutomata = new HashMap<String, FiniteAutomaton>();
		this.regularDevices = new HashMap<String, RegularDevice>();
	}
	
	public String insertRegularGrammar(final String productions) throws RegularDeviceExistingException, InvalidProductionException {
		String label = "[ G ] " + productions;
		
		RegularGrammar grammar = new RegularGrammar(productions);
		
		if(this.regularDevices.containsKey(label))
			throw new RegularDeviceExistingException();
		
		this.regularDevices.put(label, grammar);
		
		return label;
	}
	
	public String insertRegularExpression(final String regularExpression) throws RegularDeviceExistingException, InvalidRegularExpressionException {
		String label = "[ E ] " + regularExpression;
		
		RegularExpression expression = new RegularExpression(regularExpression);
		
		if(this.regularDevices.containsKey(label))
			throw new RegularDeviceExistingException();
		
		this.regularDevices.put(label, expression);
		
		return label;
	}

	public String generateFiniteAutomaton(final String regularDeviceLabel) throws RegularDeviceNotFoundException {
		if(this.regularDevices.containsKey(regularDeviceLabel))
			throw new RegularDeviceNotFoundException();
		
		FiniteAutomaton automaton = this.regularDevices.get(regularDeviceLabel).toFiniteAutomaton();
		this.finiteAutomata.put(automaton.getName(), automaton);
		
		return automaton.getName();
	}
	
	public void removeRegularDevice(final String regularDeviceLabel) {
		if(this.regularDevices.containsKey(regularDeviceLabel))
			this.regularDevices.remove(regularDeviceLabel);
	}
	
	public void removeFiniteAutomaton(final String automatonLabel) {
		if(this.finiteAutomata.containsKey(automatonLabel))
			this.finiteAutomata.remove(automatonLabel);
	}
	
	public FiniteAutomaton getFiniteAutomaton(final String automatonLabel) throws FiniteAutomatonNotFoundException {
		if(this.finiteAutomata.containsKey(automatonLabel))
			throw new FiniteAutomatonNotFoundException();
		
		return this.finiteAutomata.get(automatonLabel);
	}
	
	public String[][] getFiniteAutomatonTransitionTable(final String automatonLabel) throws FiniteAutomatonNotFoundException {
		if(this.finiteAutomata.containsKey(automatonLabel))
			throw new FiniteAutomatonNotFoundException();
		
		return this.finiteAutomata.get(automatonLabel).toTransitionsTable();
	}
	
	public String getRegularDeviceTextForm(final String regularDeviceLabel) throws RegularDeviceNotFoundException {
		if(!this.regularDevices.containsKey(regularDeviceLabel))
			throw new RegularDeviceNotFoundException();
		
		return this.regularDevices.get(regularDeviceLabel).toString();
	}
	
	public List<String> findPatternOccurrences(final String regularExpressionLabel, final String text) {
		return null; // TODO
	}
	
	public boolean areEquivalent(final String regularDeviceLabel1, final String regularDeviceLabel2) {
		return false; // TODO
	}
	
	public List<FiniteAutomaton> determinizeFiniteAutomaton(final FiniteAutomaton automaton) throws Exception {
		List<FiniteAutomaton> automata = new ArrayList<FiniteAutomaton>();

		try {
			if(!automaton.isDeterministic()) {
				FiniteAutomaton automatonD = (FiniteAutomaton) automaton.clone();
				automatonD.determinize();
				automata.add(automatonD);
				this.finiteAutomata.put(automatonD.getName(), automatonD);
			} else {
				throw new Exception("O autômato já é determinístico!");
			}
		} catch (CloneNotSupportedException e) {}

		return automata;
	}

	public List<FiniteAutomaton> minimizeFiniteAutomaton(final FiniteAutomaton automaton) throws Exception {
		List<FiniteAutomaton> automata = new ArrayList<FiniteAutomaton>();

		try {
			if(!automaton.isMinimal()) {
				FiniteAutomaton automatonM = (FiniteAutomaton) automaton.clone();
				automatonM.minimize();
				automata.add(automatonM);
				this.finiteAutomata.put(automatonM.getName(), automatonM);
			} else {
				throw new Exception("O autômato já é mínimo!");
			}
		} catch (CloneNotSupportedException e) {}

		return automata;
	}

	public List<FiniteAutomaton> complementFiniteAutomaton(final FiniteAutomaton automaton) {
		List<FiniteAutomaton> automata = new ArrayList<FiniteAutomaton>();
		automata.add(automaton.complement());
		
		return automata;
	}

	public List<FiniteAutomaton> intersectFiniteAutomaton(final FiniteAutomaton automaton1, final FiniteAutomaton automaton2) {
		List<FiniteAutomaton> automata = new ArrayList<FiniteAutomaton>();
		automata.add(automaton1.intersection(automaton2));
		
		return automata;
	}
	
}
