package slr.gui;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 * Janela de visualização de autômato.
 */
public class FiniteAutomatonWindow extends JFrame {

	private static final long serialVersionUID = -8511116113883937918L;
	private JScrollPane scrollPaneFA;
    private JLabel labelName;
    private JLabel labelNameValue;
    private JTable tableFA;
    
    public FiniteAutomatonWindow(String automatonName, String[][] transitionsTable) {
        initComponents();
        initTransitionsTable(transitionsTable);
        this.labelNameValue.setText(automatonName);
    }

    private void initComponents() {
        scrollPaneFA = new JScrollPane();
        tableFA = new JTable();
        labelName = new JLabel();
        labelNameValue = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualização de Autômato Finito");
        setResizable(false);

        tableFA.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollPaneFA.setViewportView(tableFA);

        labelName.setText("Nome:");

        labelNameValue.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        labelNameValue.setText("Nome do Autômato");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneFA, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelName)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNameValue)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(labelNameValue))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneFA, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    private void initTransitionsTable(final String[][] transitionsTable) {
    	String[][] data = new String[transitionsTable.length - 1][transitionsTable[0].length];
    	
    	for(int i = 1; i < transitionsTable.length; i++)
    		data[i - 1] = transitionsTable[i];
    	
    	this.tableFA = new JTable(data, transitionsTable[0]);
    	this.tableFA.getColumnModel().getColumn(0).setWidth(30);
    	
    	for(int i = 1; i < this.tableFA.getColumnCount(); i++)
    		this.tableFA.getColumnModel().getColumn(i).setWidth(40); // TODO verificar se tamanho está ok.
    }
    
}
