package slf.gui;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class MainWindow extends JFrame {
	
    public MainWindow() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        panelRegularExpressions = new JPanel();
        jScrollPane1 = new JScrollPane();
        listRegularExpressions = new JList<String>();
        btnAddRE = new JButton();
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

        panelRegularExpressions.setBorder(BorderFactory.createTitledBorder("Expressões Regulares"));

        listRegularExpressions.setToolTipText("Clique duplo para editar uma expressão");
        jScrollPane1.setViewportView(listRegularExpressions);

        btnAddRE.setText("Adicionar");
        btnAddRE.setToolTipText("Adicionar uma nova expressão regular");

        btnRemoveRE.setText("Remover");
        btnRemoveRE.setToolTipText("Remover a expressão regular selecionada");

        GroupLayout panelRegularExpressionsLayout = new GroupLayout(panelRegularExpressions);
        panelRegularExpressions.setLayout(panelRegularExpressionsLayout);
        panelRegularExpressionsLayout.setHorizontalGroup(
            panelRegularExpressionsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
            .addGroup(panelRegularExpressionsLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRegularExpressionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRegularExpressionsLayout.createSequentialGroup()
                        .addComponent(btnAddRE, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveRE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelRegularExpressionsLayout.setVerticalGroup(
            panelRegularExpressionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, panelRegularExpressionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegularExpressionsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddRE)
                    .addComponent(btnRemoveRE))
                .addContainerGap())
        );

        panelUnaryOperations.setBorder(BorderFactory.createTitledBorder("Operações Básicas da Expressão Regular"));

        btnFiniteAutomaton.setText("Autômato Finito");
        btnFiniteAutomaton.setToolTipText("Visualizar o autômato finito correspondente à expressão regular");

        btnMinimalFiniteAutomaton.setText("Autômato Finito Mínimo");
        btnMinimalFiniteAutomaton.setToolTipText("Visualizar o autômato finito mínimo correspondente à expressão regular");

        btnEnumerateSentences.setText("Enumerar Sentenças");
        btnEnumerateSentences.setToolTipText("Enumerar as sentenças de determinado tamanho da linguagem");

        btnPatternOccurrencesText.setText("Verificar Ocorrências de Padrão em Texto");
        btnPatternOccurrencesText.setToolTipText("Verificar as ocorrências do padrão dado pela expressão regular em um texto");

        GroupLayout panelUnaryOperationsLayout = new GroupLayout(panelUnaryOperations);
        panelUnaryOperations.setLayout(panelUnaryOperationsLayout);
        panelUnaryOperationsLayout.setHorizontalGroup(
            panelUnaryOperationsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelUnaryOperationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUnaryOperationsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnEnumerateSentences, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPatternOccurrencesText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelUnaryOperationsLayout.createSequentialGroup()
                        .addComponent(btnFiniteAutomaton, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMinimalFiniteAutomaton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelUnaryOperationsLayout.setVerticalGroup(
            panelUnaryOperationsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelUnaryOperationsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEnumerateSentences)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPatternOccurrencesText)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelUnaryOperationsLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFiniteAutomaton)
                    .addComponent(btnMinimalFiniteAutomaton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBinaryOperations.setBorder(BorderFactory.createTitledBorder("Operações Sobre Duas Expressões Regulares"));

        btnDifferenceMinimalFiniteAutomaton.setText("Autômato Finito Mínimo da Diferença");
        btnDifferenceMinimalFiniteAutomaton.setToolTipText("Visualizar o autômato finito mínimo correspondente à diferença das expressões regulares");

        btnEquivalent.setText("Equivalentes?");
        btnEquivalent.setToolTipText("Verificar se as expressões regulares são equivalentes");

        btnIntercectionMinimalFiniteAutomaton.setText("Autômato Finito Mínimo da Interseção");
        btnIntercectionMinimalFiniteAutomaton.setToolTipText("Visualizar o autômato finito mínimo correspondente à interseção das expressões regulares");

        GroupLayout panelBinaryOperationsLayout = new GroupLayout(panelBinaryOperations);
        panelBinaryOperations.setLayout(panelBinaryOperationsLayout);
        panelBinaryOperationsLayout.setHorizontalGroup(
            panelBinaryOperationsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelBinaryOperationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBinaryOperationsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnEquivalent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIntercectionMinimalFiniteAutomaton, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                    .addComponent(btnDifferenceMinimalFiniteAutomaton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBinaryOperationsLayout.setVerticalGroup(
            panelBinaryOperationsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelBinaryOperationsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEquivalent)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIntercectionMinimalFiniteAutomaton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDifferenceMinimalFiniteAutomaton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelResults.setBorder(BorderFactory.createTitledBorder("Resultados"));

        GroupLayout panelResultsLayout = new GroupLayout(panelResults);
        panelResults.setLayout(panelResultsLayout);
        panelResultsLayout.setHorizontalGroup(
            panelResultsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelResultsLayout.setVerticalGroup(
            panelResultsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 184, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panelResults, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelRegularExpressions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(panelUnaryOperations, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelBinaryOperations, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelRegularExpressions, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelUnaryOperations, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBinaryOperations, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelResults, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

	private static final long serialVersionUID = 4113893869813535193L;
    private JButton btnAddRE;
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
    private JPanel panelRegularExpressions;
    private JPanel panelResults;
    private JPanel panelUnaryOperations;
    
}
