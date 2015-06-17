package slr.control;

import java.util.Set;

import slr.exception.FiniteAutomatonNotFoundException;
import slr.exception.InvalidProductionException;
import slr.exception.InvalidRegularExpressionException;
import slr.exception.RegularDeviceExistingException;
import slr.exception.RegularDeviceNotFoundException;

/**
 * Controlador principal.
 */
public class MainController {

	RegularLanguageController rlController;
	UIController uiController;
	
	/**
	 * Construtor.
	 */
	public MainController() {
		this.rlController = new RegularLanguageController();
		this.uiController = new UIController(this);
	}
	
	/**
	 * Executar a aplicação.
	 */
	public void execute() {
		this.uiController.showMainWindow();
	}
	
	/**
	 * Inserir um dispositivo regular.
	 * @param isRegularExpression true se é uma expressão regular.
	 * @param description descrição textual do dispositivo.
	 */
	public void insertRegularDevice(boolean isRegularExpression, String description) {
		try {
			String label = "";
			
			if(isRegularExpression)
				label = this.rlController.insertRegularExpression(description);
			else
				label = this.rlController.insertRegularGrammar(description);
			
			this.uiController.insertRegularDeviceToList(label);
			this.uiController.disposeRegularDeviceEditionWindow();
		} catch (RegularDeviceExistingException
				| InvalidRegularExpressionException | InvalidProductionException e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * Remover dispositivo regular.
	 * @param regularDeviceLabel nome do dispositivo.
	 */
	public void removeRegularDevice(String regularDeviceLabel) {
		this.rlController.removeRegularDevice(regularDeviceLabel);
		this.uiController.removeRegularDeviceFromList(regularDeviceLabel);
	}
	
	/**
	 * Atualizar dispositivo regular.
	 * @param isRegularExpression true se é uma expressão regular.
	 * @param regularDeviceOldLabel nome antigo do dispositivo.
	 * @param regularDeviceDescription descrição textual do dispositivo.
	 */
	public void updateRegularDevice(boolean isRegularExpression, String regularDeviceOldLabel, String regularDeviceDescription) {
		try {
			String label = "";
			
			if(isRegularExpression)
				label = this.rlController.insertRegularExpression(regularDeviceDescription);
			else
				label = this.rlController.insertRegularGrammar(regularDeviceDescription);
			
			this.uiController.updateRegularDeviceFromList(regularDeviceOldLabel, label);
			this.removeRegularDevice(regularDeviceOldLabel);
			this.uiController.disposeRegularDeviceEditionWindow();
		} catch (RegularDeviceExistingException
				| InvalidRegularExpressionException | InvalidProductionException e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
	}
	
	/**
	 * Obter um dispositivo regular.
	 * @param regularDeviceLabel nome do dispositivo.
	 * @return nome do dispositivo.
	 */
	public String getRegularDeviceLabel(String regularDeviceLabel) {
		try {
			return this.rlController.getRegularDeviceTextForm(regularDeviceLabel);
		} catch (RegularDeviceNotFoundException e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
		return null;
	}

	/**
	 * Obter as transições do autômato finito.
	 * @param automatonLabel nome do autômato.
	 * @return tabela de transições do autômato.
	 */
	public String[][] getFiniteAutomatonTransitions(String automatonLabel) {
		try {
			return this.rlController.getFiniteAutomatonTransitionTable(automatonLabel);
		} catch (FiniteAutomatonNotFoundException e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Gerar um autômato finito para um dispositivo regular.
	 * @param regularDeviceLabel nome do dispositivo.
	 */
	public void generateFiniteAutomaton(String regularDeviceLabel) {
		try {
			String label = this.rlController.generateFiniteAutomaton(regularDeviceLabel);
			this.uiController.insertFiniteAutomatonToList(label);
		} catch (RegularDeviceNotFoundException e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * Determinizar o autômato finito.
	 * @param automatonLabel nome do autômato.
	 */
	public void determinizeFiniteAutomaton(String automatonLabel) {
		try {
			for(String label : this.rlController.determinizeFiniteAutomaton(this.rlController.getFiniteAutomaton(automatonLabel)))
				this.uiController.insertFiniteAutomatonToList(label);
		} catch (Exception e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * Minimizar o autômato finito.
	 * @param automatonLabel nome do autômato.
	 */
	public void minimizeFiniteAutomaton(String automatonLabel) {
		try {
			for(String label : this.rlController.minimizeFiniteAutomaton(this.rlController.getFiniteAutomaton(automatonLabel)))
				this.uiController.insertFiniteAutomatonToList(label);
		} catch (Exception e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * Complementar o autômato finito.
	 * @param automatonLabel nome do autômato.
	 */
	public void complementFiniteAutomaton(String automatonLabel) {
		try {
			for(String label : this.rlController.complementFiniteAutomaton(this.rlController.getFiniteAutomaton(automatonLabel)))
				this.uiController.insertFiniteAutomatonToList(label);
		} catch (Exception e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
	}

	/**
	 * Interceptar dois autômatos finitos.
	 * @param automatonLabel1 nome do autômato 1.
	 * @param automatonLabel2 nome do autômato 2.
	 */
	public void intersectFiniteAutomata(String automatonLabel1, String automatonLabel2) {
		try {
			for(String label : this.rlController.intersectFiniteAutomaton(
					this.rlController.getFiniteAutomaton(automatonLabel1), this.rlController.getFiniteAutomaton(automatonLabel2)))
				this.uiController.insertFiniteAutomatonToList(label);
		} catch (FiniteAutomatonNotFoundException e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
	}
	
	/**
	 * Remover autômato finito.
	 * @param automatonLabel nome do autômato.
	 */
	public void removeFiniteAutomaton(String automatonLabel) {
		this.rlController.removeFiniteAutomaton(automatonLabel);
		this.uiController.removeFiniteAutomatonFromList(automatonLabel);
	}

	/**
	 * Verificar se dois dispositivos regulares são equivalentes.
	 * @param regularDeviceLabel1 nome do dispositivo 1.
	 * @param regularDeviceLabel2 nome do dispositivo 2.
	 * @return true caso a linguagem dos dispositivos seja igual.
	 */
	public boolean areEquivalent(String regularDeviceLabel1, String regularDeviceLabel2) {
		try {
			String fa1 = this.rlController.generateFiniteAutomaton(regularDeviceLabel1);
			String fa2 = this.rlController.generateFiniteAutomaton(regularDeviceLabel2);
			this.uiController.insertFiniteAutomatonToList(fa1);
			this.uiController.insertFiniteAutomatonToList(fa2);
			
			return this.rlController.finiteAutomataAreEquivalent(
					this.rlController.getFiniteAutomaton(fa1), this.rlController.getFiniteAutomaton(fa2));
		} catch (RegularDeviceNotFoundException | FiniteAutomatonNotFoundException e) {
			this.uiController.showErrorMessage(e.getMessage());
			return false;
		}
	}

	/**
	 * Buscar ocorrência de padrões em texto.
	 * @param regularDeviceLabel nome do dispositivo que define a linguagem.
	 * @param text texto a ser analisado.
	 */
	public void findPatternOccurrences(String regularDeviceLabel, String text) {
		try {
			String label = this.rlController.generateFiniteAutomaton(regularDeviceLabel);
			this.uiController.insertFiniteAutomatonToList(label);
			Set<String> patterns = this.rlController.findPatternOccurrences(
					this.rlController.getFiniteAutomaton(label), text);


			if(patterns.size() > 0) {
				String patternsMessage = "";
				
				int i = 0;
				
				for(String s : patterns) {
					i++;
					patternsMessage += s + ", ";
					
					if(i % 5 == 0)
						patternsMessage += "\n";
				}
				
				if(i % 5 == 0)
					patternsMessage = patternsMessage.substring(0, patternsMessage.length() - 3);
				else
					patternsMessage = patternsMessage.substring(0, patternsMessage.length() - 2);
					
				this.uiController.showInformationMessage("Padrões encontrados:\n" + patternsMessage);
			} else {
				this.uiController.showInformationMessage("Não foram encontrados padrões no texto!");
			}		
		} catch (RegularDeviceNotFoundException | FiniteAutomatonNotFoundException e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
	}
	
}
