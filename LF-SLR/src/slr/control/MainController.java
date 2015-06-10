package slr.control;

import slr.exception.InvalidProductionException;
import slr.exception.InvalidRegularExpressionException;
import slr.exception.RegularDeviceExistingException;

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

}
