package slr.control;

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
	
	/**
	 * Construtor
	 * @param mainController controlador principal.
	 */
	public UIController(MainController mainController) {
		this.mainController = mainController;
		this.mainWindow = new MainWindow(this);
		this.regularDeviceEditionWindow = new RegularDeviceEditionWindow(this);
		this.textInputWindow = new TextInputWindow(this);
	}
	
	/**
	 * Exibir a janela principal.
	 */
	public void showMainWindow() {
		this.mainWindow.setVisible(true);
	}

	/**
	 * Exibir a janela de inserção/edição de dispositivos regulares.
	 */
	public void showRegularDeviceEditionWindow() {
		this.regularDeviceEditionWindow.setEditionMode(false);
		this.regularDeviceEditionWindow.setDeviceType(true);
		this.regularDeviceEditionWindow.setDeviceDescription("");
		this.regularDeviceEditionWindow.setVisible(true);
	}

	/**
	 * Exibir a janela de inserção/edição de dispositivos regulares.
	 * @param isRegularExpression true se é expressão regular.
	 * @param regularDeviceLabel nome do dispositivo regular.
	 */
	public void showRegularDeviceEditionWindow(boolean isRegularExpression, String regularDeviceLabel) {
		String description = regularDeviceLabel.substring(6);//this.mainController.getRegularDevice(regularDeviceLabel);
		
		if(description != null) {
			this.regularDeviceEditionWindow.setEditionMode(true);
			this.regularDeviceEditionWindow.setDeviceType(isRegularExpression);
			this.regularDeviceEditionWindow.setDeviceLabel(regularDeviceLabel);
			this.regularDeviceEditionWindow.setDeviceDescription(description);
			this.regularDeviceEditionWindow.setVisible(true);
		}
	}

	/**
	 * Exibir a janela de visualização de autômato finito.
	 * @param automatonLabel nome do autômato.
	 */
	public void showFiniteAutomatonWindow(String automatonLabel) {
		new FiniteAutomatonWindow(automatonLabel,
				this.mainController.getFiniteAutomatonTransitions(automatonLabel)).setVisible(true);
	}

	/**
	 * Exibir a janela de busca de padrões em texto.
	 * @param regularExpressionLabel nome da expressão regular.
	 */
	public void showTextInputWindow(String regularExpressionLabel) {
		this.textInputWindow.setRegularExpression(regularExpressionLabel.substring(6));
		this.textInputWindow.setVisible(true);
	}
	
	/**
	 * Fechar janela de inserção/edição de dispositivos regulares.
	 */
	public void disposeRegularDeviceEditionWindow() {
		this.regularDeviceEditionWindow.dispose();
	}
	
	/**
	 * Exibir mensagem de erro.
	 * @param message mensagem.
	 */
	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Exibir mensagem informativa.
	 * @param message mensagem.
	 */
	public void showInformationMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Informação", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Inserir dispositivo regular na lista de dispositivos.
	 * @param regularDeviceLabel nome do dispositivo.
	 */
	public void insertRegularDeviceToList(String regularDeviceLabel) {
		this.mainWindow.insertRegularDevice(regularDeviceLabel);
	}

	/**
	 * Remover dispositivo regular da lista de dispositivos.
	 * @param regularDeviceLabel nome do dispositivo.
	 */
	public void removeRegularDeviceFromList(String regularDeviceLabel) {
		this.mainWindow.removeRegularDevice(regularDeviceLabel);
	}

	/**
	 * Atualizar dispositivo regular na lista de dispositivos.
	 * @param regularDeviceOldLabel nome antigo do dispositivo.
	 * @param regularDeviceNewLabel novo nome do dispositivo.
	 */
	public void updateRegularDeviceFromList(String regularDeviceOldLabel, String regularDeviceNewLabel) {
		this.mainWindow.updateRegularDevice(regularDeviceOldLabel, regularDeviceNewLabel);
	}

	/**
	 * Inserir dispositivo regular.
	 * @param isRegularExpression true se é expressão regular.
	 * @param description descrição textual do dispositivo.
	 */
	public void insertRegularDevice(boolean isRegularExpression, String description) {
		this.mainController.insertRegularDevice(isRegularExpression, description);
	}

	/**
	 * Remover dispositivo regular.
	 * @param regularDeviceLabel nome do dispositivo.
	 */
	public void removeRegularDevice(String regularDeviceLabel) {
		this.mainController.removeRegularDevice(regularDeviceLabel);
	}
	
	/**
	 * Atualizar dispositivo regular.
	 * @param isRegularExpression true se é expressão regular.
	 * @param regularDeviceOldLabel nome antigo do dispositivo.
	 * @param regularDeviceNewLabel novo nome do dispositivo.
	 */
	public void updateRegularDevice(boolean isRegularExpression, String regularDeviceOldLabel, String regularDeviceDescription) {
		this.mainController.updateRegularDevice(isRegularExpression, regularDeviceOldLabel, regularDeviceDescription);
	}

	/**
	 * Inserir autômato finito na lista de autômatos.
	 * @param automatonLabel nome do autômato.
	 */
	public void insertFiniteAutomatonToList(String automatonLabel) {
		this.mainWindow.insertFiniteAutomaton(automatonLabel);
	}

	/**
	 * Remover autômato finito da lista de autômatos.
	 * @param automatonLabel nome do autômato.
	 */
	public void removeFiniteAutomatonFromList(String automatonLabel) {
		this.mainWindow.removeFiniteAutomaton(automatonLabel);
	}

	/**
	 * Remover autômato finito.
	 * @param automatonLabel nome do autômato.
	 */
	public void removeFiniteAutomaton(String automatonLabel) {
		this.mainController.removeFiniteAutomaton(automatonLabel);
	}

	/**
	 * Gerar autômato finito.
	 * @param regularDeviceLabel nome do dispositivo regular.
	 */
	public void generateFiniteAutomaton(String regularDeviceLabel) {
		this.mainController.generateFiniteAutomaton(regularDeviceLabel);
	}

	/**
	 * Obter transições do autômato finito.
	 * @param automatonLabel nome do autômato.
	 * @return tabela de transições do autômato.
	 */
	public String[][] getFiniteAutomatonTransitions(String automatonLabel) {
		return this.mainController.getFiniteAutomatonTransitions(automatonLabel);
	}
	
	/**
	 * Determinizar um autômato.
	 * @param automatonLabel nome do autômato.
	 */
	public void determinizeFiniteAutomaton(String automatonLabel) {
		this.mainController.determinizeFiniteAutomaton(automatonLabel);
	}

	/**
	 * Minimizar um autômato.
	 * @param automatonLabel nome do autômato.
	 */
	public void minimizeFiniteAutomaton(String automatonLabel) {
		this.mainController.minimizeFiniteAutomaton(automatonLabel);
	}

	/**
	 * Complementar um autômato.
	 * @param automatonLabel nome do autômato.
	 */
	public void complementFiniteAutomaton(String automatonLabel) {
		this.mainController.complementFiniteAutomaton(automatonLabel);
	}

	/**
	 * Interceptar dois autômatos.
	 * @param automatonLabel1 nome do autômato 1.
	 * @param automatonLabel2 nome do autômato 2.
	 */
	public void intersectFiniteAutomata(String automatonLabel1, String automatonLabel2) {
		this.mainController.intersectFiniteAutomata(automatonLabel1, automatonLabel2);
	}
	
	/**
	 * Verificar se dois dispositivos são equivalentes
	 * @param regularDeviceLabel1 nome do dispositivo regular 1.
	 * @param regularDeviceLabel2 nome do dispositivo regular 2.
	 * @return true caso a linguagem seja igual.
	 */
	public boolean areEquivalent(String regularDeviceLabel1, String regularDeviceLabel2) {
		return this.mainController.areEquivalent(regularDeviceLabel1, regularDeviceLabel2);
	}

	/**
	 * Buscar ocorrências de padrão em texto.
	 * @param regularDeviceLabel nome do dispositivo.
	 * @param text texto a ser analisado.
	 */
	public void findPatternOccurrences(String regularDeviceLabel, String text) {
		this.mainController.findPatternOccurrences(regularDeviceLabel, text);
	}
	
}
