package slr.gui;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
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
    private JScrollPane scrollPaneText;
    private JTextArea textAreaText;
    private UIController uiController;
    
	/**
     * Creates new form TextInputWindow
     */
    public TextInputWindow(UIController uiController) {
    	this.uiController = uiController;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        scrollPaneText = new JScrollPane();
        textAreaText = new JTextArea();
        btnCancel = new JButton();
        btnOk = new JButton();
        labelER = new JLabel();
        labelErValue = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reconhecimento de Padrões em Texto");

        scrollPaneText.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        textAreaText.setColumns(20);
        textAreaText.setLineWrap(true);
        textAreaText.setRows(5);
        textAreaText.setMargin(new java.awt.Insets(3, 3, 3, 3));
        scrollPaneText.setViewportView(textAreaText);

        btnCancel.setText("Cancelar");

        btnOk.setText("Ok");

        labelER.setText("ER:");

        labelErValue.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        labelErValue.setText("Expressão Regular");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneText, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelER)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelErValue)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelER)
                    .addComponent(labelErValue))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneText, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
    }

}
