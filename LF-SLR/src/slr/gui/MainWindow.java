package slr.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import slr.control.UIController;

/**
 * Janela principal.
 * @author lucas
 *
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 4113893869813535193L;
	private JButton btnAddRE;
	private JButton btnAddRG;
	private JButton btnDifferenceMinimalFiniteAutomaton;
	private JButton btnEnumerateSentences;
	private JButton btnEquivalent;
	private JButton btnFiniteAutomaton;
	private JButton btnIntercectionMinimalFiniteAutomaton;
	private JButton btnMinimalFiniteAutomaton;
	private JButton btnPatternOccurrencesText;
	private JButton btnRemoveRE;
	private JScrollPane jScrollPane1;
	private JList<String> listRegularExpressions;
	private JPanel panelBinaryOperations;
	private JPanel panelERsAndGRs;
	private JPanel panelResults;
	private JPanel panelUnaryOperations;
	DefaultListModel<String> regularExpressions;
	private UIController uiController;
	
	public MainWindow(final UIController uiController) {
		this.uiController = uiController;
		this.regularExpressions = new DefaultListModel<String>();
		this.initComponents();
		this.setActionCommands();
		this.setLocationRelativeTo(null);
		this.setUnaryOperation(false);
		this.setNaryOperation(false);
	}

	private void initComponents() {
		panelERsAndGRs = new JPanel();
		jScrollPane1 = new JScrollPane();
		listRegularExpressions = new JList<String>();
		btnAddRE = new JButton();
		btnAddRG = new JButton();
		btnRemoveRE = new JButton();
		panelUnaryOperations = new JPanel();
		btnFiniteAutomaton = new JButton();
		btnMinimalFiniteAutomaton = new JButton();
		btnEnumerateSentences = new JButton();
		btnPatternOccurrencesText = new JButton();
		panelBinaryOperations = new JPanel();
		btnDifferenceMinimalFiniteAutomaton = new JButton();
		btnEquivalent = new JButton();
		btnIntercectionMinimalFiniteAutomaton = new JButton();
		panelResults = new JPanel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Sistema de Linguagens Formais");
		setResizable(false);

		panelERsAndGRs.setBorder(BorderFactory
				.createTitledBorder("Expressões e Gramáticas Regulares"));

		listRegularExpressions
				.setToolTipText("Clique duplo para editar uma expressão");
		jScrollPane1.setViewportView(listRegularExpressions);

		btnAddRE.setText("Inserir ER");
		btnAddRE.setToolTipText("Adicionar uma nova expressão regular");
        btnAddRG.setText("Inserir GR");
        btnAddRG.setToolTipText("Adicionar uma nova gramática regular");

		btnRemoveRE.setText("Remover");
		btnRemoveRE.setToolTipText("Remover a expressão regular selecionada");

		javax.swing.GroupLayout panelRegularExpressionsLayout = new javax.swing.GroupLayout(panelERsAndGRs);
		panelERsAndGRs.setLayout(panelRegularExpressionsLayout);
        panelRegularExpressionsLayout.setHorizontalGroup(
            panelRegularExpressionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelRegularExpressionsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRegularExpressionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegularExpressionsLayout.createSequentialGroup()
                        .addComponent(btnAddRE, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddRG, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveRE, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelRegularExpressionsLayout.setVerticalGroup(
            panelRegularExpressionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegularExpressionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegularExpressionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegularExpressionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddRE)
                        .addComponent(btnRemoveRE))
                    .addComponent(btnAddRG, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

		panelUnaryOperations.setBorder(BorderFactory
				.createTitledBorder("Operações Básicas da Expressão Regular"));

		btnFiniteAutomaton.setText("Autômato Finito");
		btnFiniteAutomaton
				.setToolTipText("Visualizar o autômato finito correspondente à expressão regular");

		btnMinimalFiniteAutomaton.setText("Autômato Finito Mínimo");
		btnMinimalFiniteAutomaton
				.setToolTipText("Visualizar o autômato finito mínimo correspondente à expressão regular");

		btnEnumerateSentences.setText("Enumerar Sentenças");
		btnEnumerateSentences
				.setToolTipText("Enumerar as sentenças de determinado tamanho da linguagem");

		btnPatternOccurrencesText
				.setText("Verificar Ocorrências de Padrão em Texto");
		btnPatternOccurrencesText
				.setToolTipText("Verificar as ocorrências do padrão dado pela expressão regular em um texto");

		GroupLayout panelUnaryOperationsLayout = new GroupLayout(
				panelUnaryOperations);
		panelUnaryOperations.setLayout(panelUnaryOperationsLayout);
		panelUnaryOperationsLayout
				.setHorizontalGroup(panelUnaryOperationsLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								panelUnaryOperationsLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												panelUnaryOperationsLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																btnEnumerateSentences,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																btnPatternOccurrencesText,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																panelUnaryOperationsLayout
																		.createSequentialGroup()
																		.addComponent(
																				btnFiniteAutomaton,
																				GroupLayout.PREFERRED_SIZE,
																				142,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				btnMinimalFiniteAutomaton,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		panelUnaryOperationsLayout
				.setVerticalGroup(panelUnaryOperationsLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								panelUnaryOperationsLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnEnumerateSentences)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnPatternOccurrencesText)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												panelUnaryOperationsLayout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																btnFiniteAutomaton)
														.addComponent(
																btnMinimalFiniteAutomaton))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		panelBinaryOperations
				.setBorder(BorderFactory
						.createTitledBorder("Operações Sobre Várias Expressões Regulares"));

		btnDifferenceMinimalFiniteAutomaton
				.setText("Autômato Finito Mínimo da Diferença");
		btnDifferenceMinimalFiniteAutomaton
				.setToolTipText("Visualizar o autômato finito mínimo correspondente à diferença das expressões regulares");

		btnEquivalent.setText("Equivalentes?");
		btnEquivalent
				.setToolTipText("Verificar se as expressões regulares são equivalentes");

		btnIntercectionMinimalFiniteAutomaton
				.setText("Autômato Finito Mínimo da Interseção");
		btnIntercectionMinimalFiniteAutomaton
				.setToolTipText("Visualizar o autômato finito mínimo correspondente à interseção das expressões regulares");

		GroupLayout panelBinaryOperationsLayout = new GroupLayout(
				panelBinaryOperations);
		panelBinaryOperations.setLayout(panelBinaryOperationsLayout);
		panelBinaryOperationsLayout
				.setHorizontalGroup(panelBinaryOperationsLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								panelBinaryOperationsLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												panelBinaryOperationsLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																btnEquivalent,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																btnIntercectionMinimalFiniteAutomaton,
																GroupLayout.DEFAULT_SIZE,
																345,
																Short.MAX_VALUE)
														.addComponent(
																btnDifferenceMinimalFiniteAutomaton,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));
		panelBinaryOperationsLayout
				.setVerticalGroup(panelBinaryOperationsLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								panelBinaryOperationsLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnEquivalent)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btnIntercectionMinimalFiniteAutomaton)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btnDifferenceMinimalFiniteAutomaton)
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		panelResults.setBorder(BorderFactory.createTitledBorder("Resultados"));

		GroupLayout panelResultsLayout = new GroupLayout(panelResults);
		panelResults.setLayout(panelResultsLayout);
		panelResultsLayout.setHorizontalGroup(panelResultsLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,
						0, Short.MAX_VALUE));
		panelResultsLayout.setVerticalGroup(panelResultsLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0,
						184, Short.MAX_VALUE));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(
														panelResults,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		panelERsAndGRs,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addComponent(
																						panelUnaryOperations,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						panelBinaryOperations,
																						GroupLayout.Alignment.TRAILING,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.TRAILING,
												false)
												.addComponent(
														panelERsAndGRs,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		panelUnaryOperations,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		panelBinaryOperations,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelResults,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addContainerGap()));

		pack();
	}

	private void setActionCommands() {
		this.btnAddRE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String re = JOptionPane.showInputDialog(null, "",
						"Inserir uma Expressão Regular",
						JOptionPane.QUESTION_MESSAGE);

				while (re != null && !re.isEmpty()
						&& MainWindow.this.regularExpressions.contains(re)) {
					JOptionPane.showMessageDialog(null,
							"Expressão regular já existente!", "Atenção",
							JOptionPane.WARNING_MESSAGE);
					re = JOptionPane.showInputDialog(null, "",
							"Inserir uma Expressão Regular",
							JOptionPane.QUESTION_MESSAGE);
				}

				if (re != null && !re.isEmpty())
					MainWindow.this.regularExpressions.addElement(re);

				MainWindow.this.listRegularExpressions
						.setModel(MainWindow.this.regularExpressions);
			}
		});

		this.btnRemoveRE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> selected = MainWindow.this.listRegularExpressions
						.getSelectedValuesList();

				for (String item : selected)
					MainWindow.this.regularExpressions.removeElement(item);

				MainWindow.this.listRegularExpressions
						.setModel(MainWindow.this.regularExpressions);
			}
		});

		this.listRegularExpressions.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					String re = MainWindow.this.listRegularExpressions
							.getSelectedValue();
					int indexRe = MainWindow.this.regularExpressions
							.indexOf(re);

					if (re != null) {
						String novaRe = (String) JOptionPane.showInputDialog(
								null, "", "Editar Expressão Regular",
								JOptionPane.QUESTION_MESSAGE, null, null, re);

						if (novaRe != null && !novaRe.isEmpty()) {
							if (!re.equals(novaRe) && MainWindow.this.regularExpressions
									.contains(novaRe)) {
								JOptionPane.showMessageDialog(null,
										"Expressão regular já existente!",
										"Atenção", JOptionPane.WARNING_MESSAGE);
							} else {
								MainWindow.this.regularExpressions.set(indexRe,
										novaRe);
								MainWindow.this.listRegularExpressions
										.setModel(MainWindow.this.regularExpressions);
							}
						}
					}
				}
			}
		});

		this.listRegularExpressions
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent arg0) {
						int items = MainWindow.this.listRegularExpressions
								.getSelectedValuesList().size();

						switch (items) {
							case 0:
								MainWindow.this.setUnaryOperation(false);
								MainWindow.this.setNaryOperation(false);
								break;
							case 1:
								MainWindow.this.setUnaryOperation(true);
								MainWindow.this.setNaryOperation(false);
								break;
							default:
								MainWindow.this.setUnaryOperation(false);
								MainWindow.this.setNaryOperation(true);
								break;
						}
					}
				});
	}

	private void setUnaryOperation(boolean unaryOperation) {
		if (unaryOperation) {
			this.btnEnumerateSentences.setEnabled(true);
			this.btnPatternOccurrencesText.setEnabled(true);
			this.btnFiniteAutomaton.setEnabled(true);
			this.btnMinimalFiniteAutomaton.setEnabled(true);
		} else {
			this.btnEnumerateSentences.setEnabled(false);
			this.btnPatternOccurrencesText.setEnabled(false);
			this.btnFiniteAutomaton.setEnabled(false);
			this.btnMinimalFiniteAutomaton.setEnabled(false);
		}
	}

	private void setNaryOperation(boolean naryOperation) {
		if (naryOperation) {
			this.btnEquivalent.setEnabled(true);
			this.btnIntercectionMinimalFiniteAutomaton.setEnabled(true);
			this.btnDifferenceMinimalFiniteAutomaton.setEnabled(true);
		} else {
			this.btnEquivalent.setEnabled(false);
			this.btnIntercectionMinimalFiniteAutomaton.setEnabled(false);
			this.btnDifferenceMinimalFiniteAutomaton.setEnabled(false);
		}
	}

}
