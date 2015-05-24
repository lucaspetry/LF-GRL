package slr;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import slr.control.MainController;
import slr.exception.InvalidProductionException;
import slr.grammar.RegularGrammar;

/**
 * Sistema de linguagens regulares.
 * @author lucas
 *
 */
public class SLRApp {

	/**
	 * Método principal.
	 * @param args argumentos.
	 * @throws InvalidProductionException 
	 */
	public static void main(String[] args) throws InvalidProductionException {
		// Definir o tema nativo do sistema operacional
		String osName = System.getProperty("os.name").toLowerCase();
		try {
			if(osName.indexOf( "win" ) >= 0)
				UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
			else if(osName.indexOf( "nux" ) >= 0)
				UIManager.setLookAndFeel( "com.sun.java.swing.plaf.gtk.GTKLookAndFeel" );
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		String productions = "S -> aA|bB\n" +
							 "A -> aA|bB|a\n" +
							 "B-> bB|b";
		
		RegularGrammar g = new RegularGrammar(productions);
		System.out.println(g);

		// Executar o controlador principal
		new MainController().execute();
	}

}
