package slr.control;

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
	
}
