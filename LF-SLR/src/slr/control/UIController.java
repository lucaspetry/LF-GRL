package slr.control;

import slr.gui.MainWindow;
import slr.gui.RegularDeviceEditionWindow;

public class UIController {

	private MainWindow mainWindow;
	private RegularDeviceEditionWindow regularDeviceEditionWindow;
	
	public UIController() {
		this.mainWindow = new MainWindow(this);
		this.regularDeviceEditionWindow = new RegularDeviceEditionWindow(this);
	}
	
	public void showMainWindow() {
		this.mainWindow.setVisible(true);
	}

	public void showRegularDeviceEditionWindow() {
		this.regularDeviceEditionWindow.setVisible(true);
	}
}
