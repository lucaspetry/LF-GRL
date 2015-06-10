package slr.control;

import javax.swing.JOptionPane;

import slr.gui.MainWindow;
import slr.gui.RegularDeviceEditionWindow;

/**
 * Controlador da interface gráfica.
 */
public class UIController {

	private MainController mainController;
	private MainWindow mainWindow;
	private RegularDeviceEditionWindow regularDeviceEditionWindow;
	
	public UIController(MainController mainController) {
		this.mainController = mainController;
		this.mainWindow = new MainWindow(this);
		this.regularDeviceEditionWindow = new RegularDeviceEditionWindow(this);
	}
	
	public void showMainWindow() {
		this.mainWindow.setVisible(true);
	}

	public void showRegularDeviceEditionWindow() {
		this.regularDeviceEditionWindow.setEditionMode(false);
		this.regularDeviceEditionWindow.setDeviceType(true);
		this.regularDeviceEditionWindow.setDeviceDescription("");
		this.regularDeviceEditionWindow.setVisible(true);
	}

	public void showRegularDeviceEditionWindow(final boolean isRegularExpression, final String deviceDescription) {
		this.regularDeviceEditionWindow.setEditionMode(true);
		this.regularDeviceEditionWindow.setDeviceType(isRegularExpression);
		this.regularDeviceEditionWindow.setDeviceDescription(deviceDescription);
		this.regularDeviceEditionWindow.setVisible(true);
	}
	
	public void disposeRegularDeviceEditionWindow() {
		this.regularDeviceEditionWindow.dispose();
	}
	
	public void showErrorMessage(final String message) {
		JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public void showInformationMessage(final String message) {
		JOptionPane.showMessageDialog(null, message, "Informação", JOptionPane.INFORMATION_MESSAGE);
	}

	public void insertRegularDeviceToList(String regularDeviceLabel) {
		this.mainWindow.insertRegularDevice(regularDeviceLabel);
	}

	public void removeRegularDeviceFromList(String regularDeviceLabel) {
		this.mainWindow.removeRegularDevice(regularDeviceLabel);
	}
	
	public void insertRegularDevice(boolean isRegularExpression, String description) {
		this.mainController.insertRegularDevice(isRegularExpression, description);
	}

	public void removeRegularDevice(String regularDeviceLabel) {
		this.mainController.removeRegularDevice(regularDeviceLabel);
	}
	
}
