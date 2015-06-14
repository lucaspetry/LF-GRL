package slr.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import slr.control.UIController;

/**
 * Janela de inserção de texto para busca de padrões.
 */
public class TextInputWindow extends JFrame {

	private static final long serialVersionUID = -196063334821282285L;
    private JButton btnCancel;
    private JButton btnOk;
    private JLabel labelER;
    private JLabel labelErValue;
    private JPanel panelText;
    private JScrollPane scrollPaneText;
    private JTextArea textAreaText;
    private UIController uiController;
    
	/**
     * Creates new form TextInputWindow
     */
    public TextInputWindow(UIController uiController) {
    	this.uiController = uiController;
        initComponents();
        this.setActionCommands();
        this.setLocationRelativeTo(null);
    }

    public void setRegularExpression(String regex) {
    	this.labelErValue.setText(regex);
    	this.textAreaText.setText("");
    }
    
    private void initComponents() {
    	panelText = new JPanel();
        scrollPaneText = new JScrollPane();
        textAreaText = new JTextArea();
        btnCancel = new JButton();
        btnOk = new JButton();
        labelER = new JLabel();
        labelErValue = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reconhecimento de Padrões em Texto");

        btnCancel.setText("Cancelar");

        btnOk.setText("Ok");

        labelER.setText("ER:");

        labelErValue.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        labelErValue.setText("Expressão Regular");

        panelText.setBorder(javax.swing.BorderFactory.createTitledBorder("Texto"));

        scrollPaneText.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        textAreaText.setColumns(20);
        textAreaText.setLineWrap(true);
        textAreaText.setRows(5);
        textAreaText.setMargin(new java.awt.Insets(3, 3, 3, 3));
        scrollPaneText.setViewportView(textAreaText);

        javax.swing.GroupLayout panelTextLayout = new javax.swing.GroupLayout(panelText);
        panelText.setLayout(panelTextLayout);
        panelTextLayout.setHorizontalGroup(
            panelTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTextLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneText, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTextLayout.setVerticalGroup(
            panelTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTextLayout.createSequentialGroup()
                .addComponent(scrollPaneText, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(labelER)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelErValue)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelER)
                    .addComponent(labelErValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
    }

	private void setActionCommands() {
		this.btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TextInputWindow.this.uiController.findPatternOccurrences(
						"[ E ] " + TextInputWindow.this.labelErValue.getText(), TextInputWindow.this.textAreaText.getText());
			}
		});
		
		this.btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TextInputWindow.this.dispose();
			}
		});
	}
	
}
