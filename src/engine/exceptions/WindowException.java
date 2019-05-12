package engine.exceptions;

/**
 *
 * Exception relative &#224; la cr&#233;ation de la fen&#234;tre
 *
 * @version 1.0 3 mai 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class WindowException extends RuntimeException {

	/**
	 *
	 * Cr&#233;e une WindowCreateException levant un message d'erreur.
	 *
	 * @param errorMessage le message d'erreur &#224; lever.
	 *
	 */
	public WindowException(String errorMessage){
		super(errorMessage);
	}
}
