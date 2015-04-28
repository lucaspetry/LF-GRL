package slf.control;

import slf.gui.MainWindow;

public class MainController {

	public MainController() {
		this.mainWindow = new MainWindow();
	}
	
	public void execute() {
		this.mainWindow.setVisible(true);
	}
	
	private MainWindow mainWindow;
	
}
