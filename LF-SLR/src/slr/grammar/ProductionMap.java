package slr.grammar;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import slr.exception.InvalidProductionException;

/**
 * Mapa de produções.
 * @author lucas
 *
 */
public class ProductionMap {

	Map<Character, Set<String>> productions;
	
	/**
	 * Construtor.
	 */
	public ProductionMap() {
		this.productions = new HashMap<Character, Set<String>>();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
	    ProductionMap productionMap = new ProductionMap();
	    
	    for(char k : this.productions.keySet()) {
	    	try {
				for(String s : this.get(k))
					productionMap.add(k, s);
			} catch (InvalidProductionException e) {
				throw new CloneNotSupportedException();
			}
	    }
	    
	    return productionMap;
	}
	
	@Override
	public String toString() {
		String line = "";
	    for(char k : this.productions.keySet()) {
	    	line += k + " -> ";
	    	try {
				for(String s : this.get(k)) {
					line += s + " | ";
				}
			} catch (InvalidProductionException e) {}
	    	line = line.substring(0, line.length() - 3);
	    	line += "\n";
	    }
	    
    	return line;
	}
	
	/**
	 * Adicionar produção.
	 * @param leftSide símbolo não terminal.
	 * @param rightSide símbolo terminal seguido ou não de um símbolo não terminal.
	 */
	public void add(char leftSide, String rightSide) {
		if(this.productions.containsKey(leftSide)) {
			Set<String> productions = this.productions.get(leftSide);
			
			if(!productions.contains(rightSide))
				productions.add(rightSide);
		} else {
			Set<String> productions = new TreeSet<String>();
			productions.add(rightSide);
			this.productions.put(leftSide, productions);
		}
	}

	/**
	 * Remover produção.
	 * @param leftSide símbolo não terminal.
	 * @param rightSide símbolo terminal seguido ou não de um símbolo não terminal.
	 * @throws InvalidProductionException se a produção não existe.
	 */
	public void remove(char leftSide, String rightSide) throws InvalidProductionException {
		if(!this.productions.containsKey(leftSide))
			throw new InvalidProductionException();
		
		if(!this.productions.get(leftSide).contains(rightSide))
			throw new InvalidProductionException();
		
		Set<String> productions = this.productions.get(leftSide);
		
		if(productions.size() == 1)
			this.productions.remove(leftSide);
	}

	/**
	 * Obter as transições possíveis por um símbolo.
	 * @param leftSide símbolo de transição.
	 * @return conjunto de estados de destino.
	 * @throws InvalidProductionException caso não exista transição pelo símbolo.
	 */
	public Set<String> get(char leftSide) throws InvalidProductionException {
		if(!this.productions.containsKey(leftSide))
			throw new InvalidProductionException();
		
		return this.productions.get(leftSide);
	}
	
}
