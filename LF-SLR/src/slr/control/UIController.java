package slr.control;

import javax.swing.JOptionPane;

import slr.automaton.FiniteAutomaton;
import slr.gui.FiniteAutomatonWindow;
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

	public void showRegularDeviceEditionWindow(final boolean isRegularExpression, final String regularDeviceLabel) {
		String description = this.mainController.getRegularDevice(regularDeviceLabel);
		
		if(description != null) {
			this.regularDeviceEditionWindow.setEditionMode(true);
			this.regularDeviceEditionWindow.setDeviceType(isRegularExpression);
			this.regularDeviceEditionWindow.setDeviceLabel(regularDeviceLabel);
			this.regularDeviceEditionWindow.setDeviceDescription(description);
			this.regularDeviceEditionWindow.setVisible(true);
		}
	}
	
	public void showFiniteAutomatonWindow(String automatonLabel) {
		new FiniteAutomatonWindow(automatonLabel,
				this.mainController.getFiniteAutomaton(automatonLabel)).setVisible(true);
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

	public void updateRegularDeviceFromList(String regularDeviceOldLabel, String regularDeviceNewLabel) {
		this.mainWindow.updateRegularDevice(regularDeviceOldLabel, regularDeviceNewLabel);
	}
	
	public void insertRegularDevice(boolean isRegularExpression, String description) {
		this.mainController.insertRegularDevice(isRegularExpression, description);
	}

	public void removeRegularDevice(String regularDeviceLabel) {
		this.mainController.removeRegularDevice(regularDeviceLabel);
	}
	
	public void updateRegularDevice(boolean isRegularExpression, String regularDeviceOldLabel, String regularDeviceDescription) {
		this.mainController.updateRegularDevice(isRegularExpression, regularDeviceOldLabel, regularDeviceDescription);
	}

	public void insertFiniteAutomatonToList(String automatonLabel) {
		this.mainWindow.insertFiniteAutomaton(automatonLabel);
	}

	public void removeFiniteAutomatonFromList(String automatonLabel) {
		this.mainWindow.removeFiniteAutomaton(automatonLabel);
	}
	
	public void generateFiniteAutomaton(String regularDeviceLabel) {
		this.mainController.generateFiniteAutomaton(regularDeviceLabel);
	}

	public String[][] getFiniteAutomaton(String automatonLabel) {
		return this.mainController.getFiniteAutomaton(automatonLabel);
	}
	
}
