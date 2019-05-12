package engine.exceptions;

/**
 *
 * Exception relative au chargement d'une image
 *
 * @version 1.0 3 mai 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class LoadImageException extends RuntimeException {

	/**
	 *
	 * Cr&#233;e une LoadImageException levant un message d'erreur.
	 *
	 * @param errorMessage le message d'erreur &#224; lever.
	 *
	 */
	public LoadImageException(String errorMessage){
		super(errorMessage);
	}
}
