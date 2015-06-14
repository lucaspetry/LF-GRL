package slr.control;

import java.util.List;

import slr.automaton.FiniteAutomaton;
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

	public void removeRegularDevice(String regularDeviceLabel) {
		this.rlController.removeRegularDevice(regularDeviceLabel);
		this.uiController.removeRegularDeviceFromList(regularDeviceLabel);
	}
	
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
	
	public String getRegularDevice(final String regularDeviceLabel) {
		try {
			return this.rlController.getRegularDeviceTextForm(regularDeviceLabel);
		} catch (RegularDeviceNotFoundException e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
		return null;
	}

	public String[][] getFiniteAutomaton(final String automatonLabel) {
		try {
			return this.rlController.getFiniteAutomatonTransitionTable(automatonLabel);
		} catch (FiniteAutomatonNotFoundException e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
		return null;
	}
	
	public void generateFiniteAutomaton(String regularDeviceLabel) {
		try {
			String label = this.rlController.generateFiniteAutomaton(regularDeviceLabel);
			this.uiController.insertFiniteAutomatonToList(label);
		} catch (RegularDeviceNotFoundException e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
	}

	public void determinizeFiniteAutomaton(String automatonLabel) {
		try {
			for(String label : this.rlController.determinizeFiniteAutomaton(this.rlController.getFiniteAutomaton(automatonLabel)))
				this.uiController.insertFiniteAutomatonToList(label);
		} catch (Exception e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
	}

	public void minimizeFiniteAutomaton(String automatonLabel) {
		try {
			for(String label : this.rlController.minimizeFiniteAutomaton(this.rlController.getFiniteAutomaton(automatonLabel)))
				this.uiController.insertFiniteAutomatonToList(label);
		} catch (Exception e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
	}

	public void complementFiniteAutomaton(String automatonLabel) {
		try {
			for(String label : this.rlController.complementFiniteAutomaton(this.rlController.getFiniteAutomaton(automatonLabel)))
				this.uiController.insertFiniteAutomatonToList(label);
		} catch (Exception e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
	}

	public void intersectFiniteAutomata(String automatonLabel1, String automatonLabel2) {
		try {
			for(String label : this.rlController.intersectFiniteAutomaton(
					this.rlController.getFiniteAutomaton(automatonLabel1), this.rlController.getFiniteAutomaton(automatonLabel2)))
				this.uiController.insertFiniteAutomatonToList(label);
		} catch (FiniteAutomatonNotFoundException e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
	}
	
	public void removeFiniteAutomaton(String automatonLabel) {
		this.rlController.removeFiniteAutomaton(automatonLabel);
		this.uiController.removeFiniteAutomatonFromList(automatonLabel);
	}

	public boolean areDevicesEquals(String regularDeviceLabel1, String regularDeviceLabel2) {
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

	public void findPatternOccurrences(String regularDeviceLabel, String text) {
		try {
			String label = this.rlController.generateFiniteAutomaton(regularDeviceLabel);
			this.uiController.insertFiniteAutomatonToList(label);
			List<String> patterns = this.rlController.findPatternOccurrences(
					this.rlController.getFiniteAutomaton(label), text);
			
			String patternsMessage = "";
			
			for(String s : patterns)
				patternsMessage += s + ", ";
			
		} catch (RegularDeviceNotFoundException | FiniteAutomatonNotFoundException e) {
			this.uiController.showErrorMessage(e.getMessage());
		}
	}
	
}
