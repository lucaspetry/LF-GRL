package slr.expression;

import java.util.Stack;

import slr.exception.InvalidRegularExpressionException;

/**
 * Expressão regular.
 * @author lucas
 */
public class RegularExpression {

	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
	public static final char CONCATENATION = '.';
	public static final char EPSILON = '&';
	public static final char KLEENE_STAR_CLOSURE = '*';
	public static final char KLEENE_POSITIVE_CLOSURE = '+';
	public static final char OR = '|';
	public static final char OPTIONAL = '?';
	public static final char PARENTHESIS_OPENING = '(';
	public static final char PARENTHESIS_CLOSING = ')';
	private String regularExpression;
	
	/**
	 * Construtor.
	 * @param regularExpression expressão regular na forma textual.
	 * @throws InvalidRegularExpressionException caso a expressão seja inválida.
	 */
	public RegularExpression(String regularExpression) throws InvalidRegularExpressionException {
		this.regularExpression = regularExpression;
		
		if(!this.isValid())
			throw new InvalidRegularExpressionException();
	}
	
	@Override
	public String toString() {
		return this.regularExpression;
	}
	
	/**
	 * Validar a expressão regular (caracteres válidos e número de parênteses).
	 * @return true caso a expressão seja válida.
	 */
	private boolean isValid() {
		String validChars = ALPHABET + CONCATENATION + KLEENE_STAR_CLOSURE 
				+ KLEENE_POSITIVE_CLOSURE + OR + OPTIONAL
				+ PARENTHESIS_OPENING + PARENTHESIS_CLOSING;
		int openingParenthesis = 0;
		
		for(char c : this.regularExpression.toCharArray()) {
			if(!validChars.contains(c + ""))
				return false;
			
			if(c == PARENTHESIS_OPENING)
				openingParenthesis++;
			else if(c == PARENTHESIS_CLOSING)
				openingParenthesis--;
		}
		
		if(openingParenthesis != 0)
			return false;
		
		return true;
	}
	
	/**
	 * Padronizar a expressão removendo fechamentos positivos e adicionando operadores de concatenação.
	 */
	public void standardize() {
		String prevChars = ALPHABET + KLEENE_STAR_CLOSURE + OPTIONAL + PARENTHESIS_CLOSING;
		String nextChars = ALPHABET + PARENTHESIS_OPENING;
		StringBuilder regexBuilder = new StringBuilder(this.regularExpression);
		Stack<Integer> openingParenthesis = new Stack<Integer>();

		for(int i = 0; i < regexBuilder.length() - 1; i++) {
			char currentChar = regexBuilder.charAt(i);
			char nextChar = regexBuilder.charAt(i+1);
			
			
			if(nextChar == KLEENE_POSITIVE_CLOSURE) {
				String rest = regexBuilder.substring(i+2);
				String closurePart = "" + currentChar;
				
				if(currentChar == PARENTHESIS_CLOSING)
					closurePart = regexBuilder.substring(openingParenthesis.peek(), i+1);

				regexBuilder.delete(i+1, regexBuilder.length());
				regexBuilder.append(closurePart + KLEENE_STAR_CLOSURE + rest);
			}

			if(currentChar == PARENTHESIS_OPENING)
				openingParenthesis.add(i);
			else if(currentChar == PARENTHESIS_CLOSING) {
				if(!openingParenthesis.isEmpty())
					openingParenthesis.pop();
			}
		}
		
		for(int i = 0; i < regexBuilder.length() - 1; i++) {
			char currentChar = regexBuilder.charAt(i);
			char nextChar = regexBuilder.charAt(i+1);
			
			if(prevChars.contains(currentChar + "") && nextChars.contains(nextChar + "")) {
				String rest = regexBuilder.substring(i+2);
				regexBuilder.delete(i+1, regexBuilder.length());
				regexBuilder.append("" + CONCATENATION + nextChar);
				regexBuilder.append(rest);
			}
		}
		this.regularExpression = regexBuilder.toString();
	}

}
