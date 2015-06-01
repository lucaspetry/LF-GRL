package slr.control;

/**
 * Controlador principal.
 */
public class MainController {

	UIController uiController;
	
	/**
	 * Construtor.
	 */
	public MainController() {
		this.uiController = new UIController();
	}
	
	/**
	 * Executar a aplicação.
	 */
	public void execute() {
		this.uiController.showMainWindow();
	}
	
}
