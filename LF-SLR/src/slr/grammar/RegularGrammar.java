package slr.grammar;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import slr.automaton.FiniteAutomaton;
import slr.automaton.State;
import slr.automaton.TransitionMap;
import slr.exception.InvalidProductionException;
import slr.expression.RegularExpression;

/**
 * Gramática regular.
 * @author lucas
 *
 */
public class RegularGrammar {

	public static final String DERIVATION = "->";
	public static final String TERMINALS = "abcdefghijklmnopqrstuvwxyz0123456789" + RegularExpression.EPSILON;
	public static final String NONTERMINALS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private char initialSymbol;
	private ProductionMap productions;
	
	/**
	 * Construtor.
	 * @param productions produções na forma textual.
	 * @throws InvalidProductionException caso as produções sejam inválidas.
	 */
	public RegularGrammar(final String productions) throws InvalidProductionException {
		this.productions = new ProductionMap();
		this.buildProductions(productions);
	}

	/**
	 * Construtor.
	 * @param productions mapa de produções.
	 * @param initialSymbol símbolo inicial da gramática.
	 * @throws InvalidProductionException caso as produções sejam inválidas.
	 */
	public RegularGrammar(final ProductionMap productions, final char initialSymbol) throws InvalidProductionException {
		this.productions = productions;
		this.initialSymbol = initialSymbol;
		
		this.productions.get(initialSymbol);
		this.checkProductions(this.toString());
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		ProductionMap productions = (ProductionMap) this.productions.clone();
		
	    try {
			return new RegularGrammar(productions, this.initialSymbol);
		} catch (InvalidProductionException e) {
			throw new CloneNotSupportedException();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(this.productions.toString());
		int index = builder.indexOf(this.initialSymbol + " " + DERIVATION);
		String grammar = builder.substring(index) + builder.substring(0, index+1);
		return grammar.substring(0, grammar.length()-1);
	}
	
	/**
	 * Obter o símbolo inicial.
	 * @return símbolo inicial da gramática.
	 */
	public char getInitialSymbol() {
		return this.initialSymbol;
	}
	
	/**
	 * Construir as produções da gramática.
	 * @param productions produções na forma textual.
	 * @throws InvalidProductionException caso as produções sejam inválidas.
	 */
	private void buildProductions(final String productions) throws InvalidProductionException {
		this.checkProductions(productions);
		
		Scanner scanner = new Scanner(productions);
		boolean firstProduction = true;
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			line = line.replaceAll("(\\s)+", "");
			
			String[] sides = line.split("->");
			String[] rightSide = sides[1].split("\\" + RegularExpression.OR);
			char leftSide = sides[0].charAt(0);
			
			if(firstProduction) {
				this.initialSymbol = leftSide;
				firstProduction = false;
			}
									
			for(int i = 0; i < rightSide.length; i++) {
				this.productions.add(leftSide, rightSide[i]);
			}
		}
		
		scanner.close();
	}

	/**
	 * Verificar as produções na forma textual.
	 * @param productions produções na forma textual.
	 * @throws InvalidProductionException caso as produções sejam inválidas.
	 */
	private void checkProductions(final String productions) throws InvalidProductionException {
		Scanner scanner = new Scanner(productions);
		Set<Character> nonTerminalsLeft = new TreeSet<Character>();
		Set<Character> nonTerminalsRight = new TreeSet<Character>();

		try {
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				line = line.replaceAll("(\\s)+", "");
				String[] parts = line.split("\\" + RegularExpression.OR + "|->");

				if(parts.length < 2 || parts[0].equals("") || !NONTERMINALS.contains(parts[0]))
					throw new InvalidProductionException();

				nonTerminalsLeft.add(parts[0].charAt(0));

				for(int i = 1; i < parts.length; i++) {
					if(!TERMINALS.contains(parts[i].subSequence(0, 1)))
						throw new InvalidProductionException();

					if(parts[i].length() == 2) {
						if(!NONTERMINALS.contains(parts[i].subSequence(1, 2)))
							throw new InvalidProductionException();

						nonTerminalsRight.add(parts[i].charAt(1));
					} else if(parts[i].length() > 2) {
						throw new InvalidProductionException();					
					}
				}
			}
			
			if(!nonTerminalsLeft.containsAll(nonTerminalsRight))
				throw new InvalidProductionException();
		} finally {
			scanner.close();
		}
	}

	/**
	 * Converter a gramática em um autômato finito.
	 * @return autômato finito equivalente.
	 */
	public FiniteAutomaton toFiniteAutomaton() {
		Map<String, State> states = new HashMap<String, State>();
		Map<String, TransitionMap> transitionMaps = new HashMap<String, TransitionMap>();
		
		// Criar os estados e mapas de transição
		for(char leftSide : this.productions.getLeftSides()) {
			transitionMaps.put(leftSide + "", new TransitionMap());
			states.put(leftSide + "", new State(leftSide + "", false, transitionMaps.get(leftSide + "")));
		}
		
		// Criar o estado final
		transitionMaps.put("F0", new TransitionMap());
		State finalState = new State("F0", true, transitionMaps.get("F0"));
		
		// Definir o estado inicial como final se Epsilon pertence à linguagem
		try {
			if(this.productions.get(this.initialSymbol).contains(RegularExpression.EPSILON + ""))
				states.get(this.initialSymbol + "").setIsFinal(true);
		} catch (InvalidProductionException e) {}

		// Adicionar as transições do autômato
		for(char leftSide : this.productions.getLeftSides()) {
			TransitionMap t = transitionMaps.get(leftSide + "");
						
			try {
				for(String rightSide : this.productions.get(leftSide)) {
					if(rightSide.length() == 1) {
						t.add(rightSide.charAt(0), finalState);
					} else {
						t.add(rightSide.charAt(0), states.get(rightSide.charAt(1) + ""));
					}
				}
			} catch (InvalidProductionException e) {}
		}
		
		return new FiniteAutomaton(
				new TreeSet<State>(states.values()), states.get(this.initialSymbol + ""));
	}
}
