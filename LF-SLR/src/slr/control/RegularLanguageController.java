package slr.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	/**
	 * Construtor.
	 */
	public RegularLanguageController() {
		this.finiteAutomata = new HashMap<String, FiniteAutomaton>();
		this.regularDevices = new HashMap<String, RegularDevice>();
	}

	/**
	 * Inserir uma gramática regular.
	 * @param productions produções da gramática.
	 * @throws RegularDeviceExistingException caso a gramática já exista.
	 * @throws InvalidProductionException caso o conjunto de produções seja inválido.
	 */
	public String insertRegularGrammar(String productions) throws RegularDeviceExistingException, InvalidProductionException {
		String label = "[ G ] " + productions;
		
		RegularGrammar grammar = new RegularGrammar(productions);
		
		if(this.regularDevices.containsKey(label))
			throw new RegularDeviceExistingException();
		
		this.regularDevices.put(label, grammar);
		
		return label;
	}

	/**
	 * Inserir uma expressão regular.
	 * @param regularExpression descrição textual da expressão.
	 * @throws RegularDeviceExistingException caso a expressão já exista.
	 * @throws InvalidRegularExpressionException caso a expressão seja inválida.
	 */
	public String insertRegularExpression(String regularExpression) throws RegularDeviceExistingException, InvalidRegularExpressionException {
		String label = "[ E ] " + regularExpression;
		
		RegularExpression expression = new RegularExpression(regularExpression);
		
		if(this.regularDevices.containsKey(label))
			throw new RegularDeviceExistingException();
		
		this.regularDevices.put(label, expression);
		
		return label;
	}

	/**
	 * Gerar um autômato finito para um dispositivo regular.
	 * @param regularDeviceLabel nome do dispositivo.
	 * @throws RegularDeviceNotFoundException caso o dispositivo regular não seja encontrado.
	 */
	public String generateFiniteAutomaton(String regularDeviceLabel) throws RegularDeviceNotFoundException {
		if(!this.regularDevices.containsKey(regularDeviceLabel))
			throw new RegularDeviceNotFoundException();
		
		FiniteAutomaton automaton = this.regularDevices.get(regularDeviceLabel).toFiniteAutomaton();
		this.finiteAutomata.put(automaton.getName(), automaton);
		
		return automaton.getName();
	}

	/**
	 * Remover um dispositivo regular.
	 * @param regularDeviceLabel nome do dispositivo regular.
	 */
	public void removeRegularDevice(String regularDeviceLabel) {
		if(this.regularDevices.containsKey(regularDeviceLabel))
			this.regularDevices.remove(regularDeviceLabel);
	}

	/**
	 * Remover um autômato finito.
	 * @param automatonLabel nome do autômato.
	 */
	public void removeFiniteAutomaton(String automatonLabel) {
		if(this.finiteAutomata.containsKey(automatonLabel))
			this.finiteAutomata.remove(automatonLabel);
	}

	/**
	 * Obter o autômato.
	 * @param automatonLabel nome do autômato.
	 * @return autômato finito.
	 * @throws FiniteAutomatonNotFoundException caso o autômato não seja encontrado.
	 */
	public FiniteAutomaton getFiniteAutomaton(String automatonLabel) throws FiniteAutomatonNotFoundException {
		if(!this.finiteAutomata.containsKey(automatonLabel))
			throw new FiniteAutomatonNotFoundException();
		
		return this.finiteAutomata.get(automatonLabel);
	}

	/**
	 * Obter a tabela de transições do autômato.
	 * @param automatonLabel nome do autômato.
	 * @return tabela de transições.
	 * @throws FiniteAutomatonNotFoundException caso o autômato não seja encontrado.
	 */
	public String[][] getFiniteAutomatonTransitionTable(String automatonLabel) throws FiniteAutomatonNotFoundException {
		if(!this.finiteAutomata.containsKey(automatonLabel))
			throw new FiniteAutomatonNotFoundException();
		
		return this.finiteAutomata.get(automatonLabel).toTransitionsTable();
	}
	
	/**
	 * Obter a forma textual de um dispositivo regular.
	 * @param regularDeviceLabel dispositivo regular.
	 * @return forma textual do dispositivo.
	 * @throws RegularDeviceNotFoundException caso o dispositivo não seja encontrado.
	 */
	public String getRegularDeviceTextForm(String regularDeviceLabel) throws RegularDeviceNotFoundException {
		if(!this.regularDevices.containsKey(regularDeviceLabel))
			throw new RegularDeviceNotFoundException();
		
		return this.regularDevices.get(regularDeviceLabel).toString();
	}
	
	/**
	 * Buscar ocorrência de padrões em texto.
	 * @param automaton autômato.
	 * @param text texto a ser analisado.
	 * @return conjunto de padrões encontrados.
	 */
	public Set<String> findPatternOccurrences(FiniteAutomaton automaton, String text) {
		Set<String> possibleSentences = new HashSet<String>();
		String alphabet = automaton.getAlphabet();
		
		for(int sentenceLength = 1; sentenceLength <= text.length(); sentenceLength++) {
			for(int i = sentenceLength; i <= text.length(); i++) {
				String entry = text.substring(i - sentenceLength, i);
				if(alphabet.contains(entry.charAt(0) + ""))
					possibleSentences.add(entry);
			}
		}
		
		Set<String> sentences = new HashSet<String>();
		
		for(String entry : possibleSentences) {
			if(automaton.recognize(entry))
				sentences.add(entry);
		}
		
		return sentences;
	}
	
	/**
	 * Verificar se a linguagem de dois autômatos é igual.
	 * @param fa1 autômato 1.
	 * @param fa2 autômato 2.
	 * @return true caso a linguagem seja igual.
	 */
	public boolean finiteAutomataAreEquivalent(FiniteAutomaton fa1, FiniteAutomaton fa2) {
		return fa1.isEquivalentTo(fa2);
	}

	/**
	 * Determinizar o autômato finito.
	 * @param automaton autômato.
	 * @return lista de autômatos correspondentes à determinização.
	 */
	public List<String> determinizeFiniteAutomaton(FiniteAutomaton automaton) throws Exception {
		List<String> automataLabels = new ArrayList<String>();

		try {
			if(!automaton.isDeterministic()) {
				FiniteAutomaton automatonD = (FiniteAutomaton) automaton.clone();
				automatonD.determinize();
				automatonD.setReferencedAutomaton(automaton.getName());
				automataLabels.add(automatonD.getName());
				this.finiteAutomata.put(automatonD.getName(), automatonD);
			} else {
				throw new Exception("O autômato já é determinístico!");
			}
		} catch (CloneNotSupportedException e) {}

		return automataLabels;
	}

	/**
	 * Minimizar o autômato finito.
	 * @param automaton autômato.
	 * @return lista de autômatos correspondentes à minimização.
	 */
	public List<String> minimizeFiniteAutomaton(FiniteAutomaton automaton) throws Exception {
		List<String> automataLabels = new ArrayList<String>();
		boolean isDeterministic = automaton.isDeterministic();

		if(!isDeterministic) {
			FiniteAutomaton automatonD = (FiniteAutomaton) automaton.clone();
			automatonD.determinize();
			automatonD.setReferencedAutomaton(automaton.getName());
			automataLabels.add(automatonD.getName());
			this.finiteAutomata.put(automatonD.getName(), automatonD);
			automaton = automatonD;
		}
		
		try {
			if(!isDeterministic || !automaton.hasMinimalStateSet()) {
				FiniteAutomaton automatonM = (FiniteAutomaton) automaton.clone();
				automatonM.minimize();
				automatonM.setReferencedAutomaton(automaton.getName());
				automataLabels.add(automatonM.getName());
				this.finiteAutomata.put(automatonM.getName(), automatonM);
			} else {
				throw new Exception("O autômato já é mínimo!");
			}
		} catch (CloneNotSupportedException e) {}

		return automataLabels;
	}

	/**
	 * Complementar o autômato finito.
	 * @param automaton autômato.
	 * @return lista de autômatos correspondentes ao complemento.
	 */
	public List<String> complementFiniteAutomaton(FiniteAutomaton automaton) {
		List<String> automataLabels = new ArrayList<String>();
		boolean isDeterministic = automaton.isDeterministic();

		if(!isDeterministic) {
			FiniteAutomaton automatonD;
			try {
				automatonD = (FiniteAutomaton) automaton.clone();
				automatonD.determinize();
				automatonD.setReferencedAutomaton(automaton.getName());
				automataLabels.add(automatonD.getName());
				this.finiteAutomata.put(automatonD.getName(), automatonD);
				automaton = automatonD;
			} catch (CloneNotSupportedException e) {}
		}
		
		FiniteAutomaton fa = automaton.complement();
		fa.setReferencedAutomaton(automaton.getName());
		this.finiteAutomata.put(fa.getName(), fa);
		automataLabels.add(fa.getName());
		
		return automataLabels;
	}

	/**
	 * Interceptar dois autômatos finitos.
	 * @param automaton1 autômato 1.
	 * @param automaton2 autômato 2.
	 * @return lista de autômatos correspondentes à interseção.
	 */
	public List<String> intersectFiniteAutomaton(FiniteAutomaton automaton1, FiniteAutomaton automaton2) {
		List<String> automataLabels = new ArrayList<String>();

		for(FiniteAutomaton fa : automaton1.intersection(automaton2)) {
			this.finiteAutomata.put(fa.getName(), fa);
			automataLabels.add(fa.getName());
		}
		
		return automataLabels;
	}
	
}
