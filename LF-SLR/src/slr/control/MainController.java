package slr.control;

import slr.gui.MainWindow;

/**
 * Controlador principal.
 * @author lucas
 *
 */
public class MainController {

	private MainWindow mainWindow;
	
	/**
	 * Construtor.
	 */
	public MainController() {
		this.mainWindow = new MainWindow();
	}
	
	/**
	 * Executar a aplicação.
	 */
	public void execute() {
		this.mainWindow.setVisible(true);
	}
	
}
