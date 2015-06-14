package slr.control;

import java.util.List;

import javax.swing.JOptionPane;

import slr.gui.FiniteAutomatonWindow;
import slr.gui.MainWindow;
import slr.gui.RegularDeviceEditionWindow;
import slr.gui.TextInputWindow;

/**
 * Controlador da interface gráfica.
 */
public class UIController {

	private MainController mainController;
	private MainWindow mainWindow;
	private RegularDeviceEditionWindow regularDeviceEditionWindow;
	private TextInputWindow textInputWindow;
	
	public UIController(MainController mainController) {
		this.mainController = mainController;
		this.mainWindow = new MainWindow(this);
		this.regularDeviceEditionWindow = new RegularDeviceEditionWindow(this);
		this.textInputWindow = new TextInputWindow(this);
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
		String description = regularDeviceLabel.substring(6);//this.mainController.getRegularDevice(regularDeviceLabel);
		
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

	public void showTextInputWindow(String regularExpressionLabel) {
		this.textInputWindow.setRegularExpression(regularExpressionLabel.substring(6));
		this.textInputWindow.setVisible(true);
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

	public void removeFiniteAutomaton(String automatonLabel) {
		this.mainController.removeFiniteAutomaton(automatonLabel);
	}
	
	public void generateFiniteAutomaton(String regularDeviceLabel) {
		this.mainController.generateFiniteAutomaton(regularDeviceLabel);
	}

	public String[][] getFiniteAutomaton(String automatonLabel) {
		return this.mainController.getFiniteAutomaton(automatonLabel);
	}
	
	public void determinizeFiniteAutomaton(String automatonLabel) {
		this.mainController.determinizeFiniteAutomaton(automatonLabel);
	}

	public void minimizeFiniteAutomaton(String automatonLabel) {
		this.mainController.minimizeFiniteAutomaton(automatonLabel);
	}

	public void complementFiniteAutomaton(String automatonLabel) {
		this.mainController.complementFiniteAutomaton(automatonLabel);
	}

	public void intersectFiniteAutomata(String automatonLabel1, String automatonLabel2) {
		this.mainController.intersectFiniteAutomata(automatonLabel1, automatonLabel2);
	}
	
	public boolean areDevicesEquals(String regularDeviceLabel1, String regularDeviceLabel2) {
		return this.mainController.areDevicesEquals(regularDeviceLabel1, regularDeviceLabel2);
	}

	public void findPatternOccurrences(String regularDeviceLabel, String text) {
		this.mainController.findPatternOccurrences(regularDeviceLabel, text);
	}
	
}
