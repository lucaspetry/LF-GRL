package slr.grammar;

import java.util.Scanner;

import slr.automaton.FiniteAutomaton;
import slr.exception.InvalidProductionException;

/**
 * Gramática regular.
 * @author lucas
 *
 */
public class RegularGrammar {

	public static final String DERIVATION = "->";
	public static final char OR = '|';
	public static final String TERMINALS = "abcdefghijklmnopqrstuvwxyz0123456789&";
	public static final String NONTERMINALS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private char initialSymbol;
	private ProductionMap productions;
	
	public RegularGrammar(final String productions) throws InvalidProductionException {
		this.productions = new ProductionMap();
		this.buildProductions(productions);
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
	 * Converter a gramática em um autômato finito.
	 * @return autômato finito equivalente.
	 */
	public FiniteAutomaton toFiniteAutomaton() {
		return null; // TODO
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
			String[] rightSide = sides[1].split("\\|");
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

		try {
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				line = line.replaceAll("(\\s)+", "");
				String[] parts = line.split("\\||->");

				if(parts.length < 2 || parts[0].equals("") || !NONTERMINALS.contains(parts[0]))
					throw new InvalidProductionException();

				for(int i = 1; i < parts.length; i++) {
					if(!TERMINALS.contains(parts[i].subSequence(0, 1)))
						throw new InvalidProductionException();

					if(parts[i].length() == 2) {
						if(!NONTERMINALS.contains(parts[i].subSequence(1, 2)))
							throw new InvalidProductionException();			
					} else if(parts[i].length() > 2) {
						throw new InvalidProductionException();					
					}
				}
			}
		} finally {
			scanner.close();
		}
	}
	
}
