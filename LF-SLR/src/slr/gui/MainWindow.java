package slr.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import slr.control.UIController;

/**
 * Janela principal.
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 4113893869813535193L;
	private JButton btnComplement;
    private JButton btnDeterminize;
    private JButton btnEquals;
    private JButton btnGenerateFiniteAutomaton;
    private JButton btnInsertReRg;
    private JButton btnIntersection;
    private JButton btnMinimize;
    private JButton btnPatternOccurrencesText;
    private JButton btnRemoveFA;
    private JButton btnRemoveReRg;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JList<String> listFiniteAutomata;
    private JList<String> listReRg;
    private JPanel panelAutomataOperations;
    private JPanel panelErGrOperations;
    private JPanel panelFAView;
    private JPanel panelFiniteAutomata;
    private JPanel panelReRgView;
    private JPanel panelRegularExpressionsGrammars;
    private JScrollPane scrollPaneFA;
    private JScrollPane scrollPaneReRg;
    private JTable tableFA;
    private JTextArea textAreaReRg;
	private DefaultListModel<String> regularDevices;
	private DefaultListModel<String> finiteAutomata;
	private UIController uiController;
	
	public MainWindow(final UIController uiController) {
		this.uiController = uiController;
		this.regularDevices = new DefaultListModel<String>();
		this.finiteAutomata = new DefaultListModel<String>();
		this.initComponents();
		this.setActionCommands();
		this.setLocationRelativeTo(null);

		this.btnPatternOccurrencesText.setEnabled(false);
		this.btnEquals.setEnabled(false);
		this.btnGenerateFiniteAutomaton.setEnabled(false);
	}

	private void initComponents() {
		panelErGrOperations = new JPanel();
        btnGenerateFiniteAutomaton = new JButton();
        btnPatternOccurrencesText = new JButton();
        btnEquals = new JButton();
        panelAutomataOperations = new JPanel();
        btnIntersection = new JButton();
        btnDeterminize = new JButton();
        btnComplement = new JButton();
        btnMinimize = new JButton();
        panelFAView = new JPanel();
        scrollPaneFA = new JScrollPane();
        tableFA = new JTable();
        panelFiniteAutomata = new JPanel();
        jScrollPane2 = new JScrollPane();
        listFiniteAutomata = new JList<String>();
        btnRemoveFA = new JButton();
        panelRegularExpressionsGrammars = new JPanel();
        jScrollPane1 = new JScrollPane();
        listReRg = new JList<String>();
        btnInsertReRg = new JButton();
        btnRemoveReRg = new JButton();
        panelReRgView = new JPanel();
        scrollPaneReRg = new JScrollPane();
        textAreaReRg = new JTextArea();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Linguagens Formais");
        setResizable(false);

        panelErGrOperations.setBorder(BorderFactory.createTitledBorder("Operações Sobre Expressões e Gramáticas"));

        btnGenerateFiniteAutomaton.setText("Gerar Autômato Finito");

        btnPatternOccurrencesText.setText("Buscar Padrões em Texto");

        btnEquals.setText("Equivalentes?");

        GroupLayout panelErGrOperationsLayout = new GroupLayout(panelErGrOperations);
        panelErGrOperations.setLayout(panelErGrOperationsLayout);
        panelErGrOperationsLayout.setHorizontalGroup(
            panelErGrOperationsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelErGrOperationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelErGrOperationsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnPatternOccurrencesText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEquals, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGenerateFiniteAutomaton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelErGrOperationsLayout.setVerticalGroup(
            panelErGrOperationsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelErGrOperationsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPatternOccurrencesText)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEquals)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerateFiniteAutomaton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelAutomataOperations.setBorder(BorderFactory.createTitledBorder("Operações Sobre Autômatos Finitos"));

        btnIntersection.setText("Interseção");

        btnDeterminize.setText("Determinizar");

        btnComplement.setText("Complemento");

        btnMinimize.setText("Minimizar");

        GroupLayout panelAutomataOperationsLayout = new GroupLayout(panelAutomataOperations);
        panelAutomataOperations.setLayout(panelAutomataOperationsLayout);
        panelAutomataOperationsLayout.setHorizontalGroup(
            panelAutomataOperationsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelAutomataOperationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAutomataOperationsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panelAutomataOperationsLayout.createSequentialGroup()
                        .addComponent(btnDeterminize, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMinimize, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnIntersection, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnComplement, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAutomataOperationsLayout.setVerticalGroup(
            panelAutomataOperationsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelAutomataOperationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAutomataOperationsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeterminize)
                    .addComponent(btnMinimize))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnComplement)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIntersection)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelFAView.setBorder(BorderFactory.createTitledBorder("Visualização do Autômato Finito"));

        tableFA.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollPaneFA.setViewportView(tableFA);

        GroupLayout panelFAViewLayout = new GroupLayout(panelFAView);
        panelFAView.setLayout(panelFAViewLayout);
        panelFAViewLayout.setHorizontalGroup(
            panelFAViewLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelFAViewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneFA, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelFAViewLayout.setVerticalGroup(
            panelFAViewLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelFAViewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneFA, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelFiniteAutomata.setBorder(BorderFactory.createTitledBorder("Autômatos Finitos"));

        listFiniteAutomata.setToolTipText("Clique duplo para visualizar um autômato em uma nova janela");
        jScrollPane2.setViewportView(listFiniteAutomata);

        btnRemoveFA.setText("Remover");
        btnRemoveFA.setToolTipText("Remover o autômato selecionado");

        GroupLayout panelFiniteAutomataLayout = new GroupLayout(panelFiniteAutomata);
        panelFiniteAutomata.setLayout(panelFiniteAutomataLayout);
        panelFiniteAutomataLayout.setHorizontalGroup(
            panelFiniteAutomataLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, panelFiniteAutomataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFiniteAutomataLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiniteAutomataLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRemoveFA, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelFiniteAutomataLayout.setVerticalGroup(
            panelFiniteAutomataLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, panelFiniteAutomataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveFA)
                .addContainerGap())
        );

        panelRegularExpressionsGrammars.setBorder(BorderFactory.createTitledBorder("Expressões e Gramáticas Regulares"));

        listReRg.setToolTipText("Clique duplo para editar uma expressão/gramática");
        jScrollPane1.setViewportView(listReRg);

        btnInsertReRg.setText("Inserir ER/GR");
        btnInsertReRg.setToolTipText("Adicionar uma nova expressão ou gramática regular");

        btnRemoveReRg.setText("Remover");
        btnRemoveReRg.setToolTipText("Remover a expressão/gramática regular selecionada");

        GroupLayout panelRegularExpressionsGrammarsLayout = new GroupLayout(panelRegularExpressionsGrammars);
        panelRegularExpressionsGrammars.setLayout(panelRegularExpressionsGrammarsLayout);
        panelRegularExpressionsGrammarsLayout.setHorizontalGroup(
            panelRegularExpressionsGrammarsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
            .addGroup(panelRegularExpressionsGrammarsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRegularExpressionsGrammarsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelRegularExpressionsGrammarsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnInsertReRg, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveReRg, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelRegularExpressionsGrammarsLayout.setVerticalGroup(
            panelRegularExpressionsGrammarsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, panelRegularExpressionsGrammarsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegularExpressionsGrammarsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertReRg)
                    .addComponent(btnRemoveReRg))
                .addContainerGap())
        );

        panelReRgView.setBorder(BorderFactory.createTitledBorder("Visualização da Expressão/Gramática Regular"));

        scrollPaneReRg.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        textAreaReRg.setEditable(false);
        textAreaReRg.setColumns(20);
        textAreaReRg.setLineWrap(true);
        textAreaReRg.setRows(5);
        textAreaReRg.setMargin(new java.awt.Insets(3, 3, 3, 3));
        scrollPaneReRg.setViewportView(textAreaReRg);

        GroupLayout panelReRgViewLayout = new GroupLayout(panelReRgView);
        panelReRgView.setLayout(panelReRgViewLayout);
        panelReRgViewLayout.setHorizontalGroup(
            panelReRgViewLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelReRgViewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneReRg, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelReRgViewLayout.setVerticalGroup(
            panelReRgViewLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelReRgViewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneReRg, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelErGrOperations, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRegularExpressionsGrammars, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelReRgView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelFiniteAutomata, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelAutomataOperations, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFAView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panelErGrOperations, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelAutomataOperations, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelFiniteAutomata, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRegularExpressionsGrammars, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelReRgView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFAView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
	}

	private void setActionCommands() {
		this.btnInsertReRg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainWindow.this.uiController.showRegularDeviceEditionWindow();
			}
		});

		this.btnRemoveReRg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(String label : MainWindow.this.listReRg.getSelectedValuesList())
					MainWindow.this.uiController.removeRegularDevice(label);
				MainWindow.this.textAreaReRg.setText("");
			}
		});
		
		this.listReRg
		.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				int items = MainWindow.this.listReRg
						.getSelectedValuesList().size();

				switch (items) {
					case 0:
						MainWindow.this.btnPatternOccurrencesText.setEnabled(false);
						MainWindow.this.btnEquals.setEnabled(false);
						MainWindow.this.btnGenerateFiniteAutomaton.setEnabled(false);
						break;
					case 1:
						String item = MainWindow.this.listReRg.getSelectedValuesList().get(0);
						MainWindow.this.textAreaReRg.setText(item.substring(6));
						MainWindow.this.btnPatternOccurrencesText.setEnabled(item.contains("[ E ]"));
						MainWindow.this.btnEquals.setEnabled(false);
						MainWindow.this.btnGenerateFiniteAutomaton.setEnabled(true);
						break;
					default:
						String item2 = MainWindow.this.listReRg.getSelectedValuesList().get(0);
						MainWindow.this.textAreaReRg.setText(item2.substring(6));
						MainWindow.this.btnPatternOccurrencesText.setEnabled(false);
						MainWindow.this.btnEquals.setEnabled(true);
						MainWindow.this.btnGenerateFiniteAutomaton.setEnabled(false);
						break;
				}
			}
		});
		
		this.listReRg.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					String reRg = MainWindow.this.listReRg.getSelectedValue();

					if (reRg != null)
						MainWindow.this.uiController.showRegularDeviceEditionWindow(reRg.contains("[ E ]"), reRg);
				}
			}
		});
		
//		this.btnAddRE.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				String re = JOptionPane.showInputDialog(null, "",
//						"Inserir uma Expressão Regular",
//						JOptionPane.QUESTION_MESSAGE);
//
//				while (re != null && !re.isEmpty()
//						&& MainWindow.this.regularExpressions.contains(re)) {
//					JOptionPane.showMessageDialog(null,
//							"Expressão regular já existente!", "Atenção",
//							JOptionPane.WARNING_MESSAGE);
//					re = JOptionPane.showInputDialog(null, "",
//							"Inserir uma Expressão Regular",
//							JOptionPane.QUESTION_MESSAGE);
//				}
//
//				if (re != null && !re.isEmpty())
//					MainWindow.this.regularExpressions.addElement(re);
//
//				MainWindow.this.listRegularExpressions
//						.setModel(MainWindow.this.regularExpressions);
//			}
//		});
//
//		this.btnRemoveRE.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				List<String> selected = MainWindow.this.listRegularExpressions
//						.getSelectedValuesList();
//
//				for (String item : selected)
//					MainWindow.this.regularExpressions.removeElement(item);
//
//				MainWindow.this.listRegularExpressions
//						.setModel(MainWindow.this.regularExpressions);
//			}
//		});
//
//		this.listRegularExpressions.addMouseListener(new MouseListener() {
//			public void mouseReleased(MouseEvent arg0) {
//			}
//
//			public void mousePressed(MouseEvent arg0) {
//			}
//
//			public void mouseExited(MouseEvent arg0) {
//			}
//
//			public void mouseEntered(MouseEvent arg0) {
//			}
//
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2 && !e.isConsumed()) {
//					e.consume();
//					String re = MainWindow.this.listRegularExpressions
//							.getSelectedValue();
//					int indexRe = MainWindow.this.regularExpressions
//							.indexOf(re);
//
//					if (re != null) {
//						String novaRe = (String) JOptionPane.showInputDialog(
//								null, "", "Editar Expressão Regular",
//								JOptionPane.QUESTION_MESSAGE, null, null, re);
//
//						if (novaRe != null && !novaRe.isEmpty()) {
//							if (!re.equals(novaRe) && MainWindow.this.regularExpressions
//									.contains(novaRe)) {
//								JOptionPane.showMessageDialog(null,
//										"Expressão regular já existente!",
//										"Atenção", JOptionPane.WARNING_MESSAGE);
//							} else {
//								MainWindow.this.regularExpressions.set(indexRe,
//										novaRe);
//								MainWindow.this.listRegularExpressions
//										.setModel(MainWindow.this.regularExpressions);
//							}
//						}
//					}
//				}
//			}
//		});
//
//		this.listRegularExpressions
//				.addListSelectionListener(new ListSelectionListener() {
//					public void valueChanged(ListSelectionEvent arg0) {
//						int items = MainWindow.this.listRegularExpressions
//								.getSelectedValuesList().size();
//
//						switch (items) {
//							case 0:
//								MainWindow.this.setUnaryOperation(false);
//								MainWindow.this.setNaryOperation(false);
//								break;
//							case 1:
//								MainWindow.this.setUnaryOperation(true);
//								MainWindow.this.setNaryOperation(false);
//								break;
//							default:
//								MainWindow.this.setUnaryOperation(false);
//								MainWindow.this.setNaryOperation(true);
//								break;
//						}
//					}
//				});
	}
	
	public void insertRegularDevice(String regularDeviceLabel) {
		this.regularDevices.addElement(regularDeviceLabel);
		this.listReRg.setModel(this.regularDevices);
	}

	public void insertFiniteAutomaton(String automatonLabel) {
		this.finiteAutomata.addElement(automatonLabel);
		this.listFiniteAutomata.setModel(this.finiteAutomata);
	}

	public void removeRegularDevice(String regularDeviceLabel) {
		this.regularDevices.removeElement(regularDeviceLabel);
		this.listReRg.setModel(this.regularDevices);
	}

	public void removeFiniteAutomaton(String automatonLabel) {
		this.finiteAutomata.removeElement(automatonLabel);
		this.listFiniteAutomata.setModel(this.finiteAutomata);
	}
	
	public void updateRegularDevice(String regularDeviceOldLabel, String regularDeviceNewLabel) {
		int index = this.regularDevices.indexOf(regularDeviceOldLabel);
		this.regularDevices.set(index, regularDeviceNewLabel);
	}
	
}
