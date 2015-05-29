package slr.control;

/**
 * Controlador principal.
 * @author lucas
 *
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
