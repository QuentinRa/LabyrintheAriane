package engine.exceptions;

/**
 *
 * InvalidDataException est une exception lev&#233;e si les donn&#233;es ne sont
 * pas conformes &#224; ce que l'on attendait.
 *
 * @version 1.0 6 avril 2019
 * @author Quentin Ramsamy--Ageorges
 *
 */
public class InvalidDataException extends RuntimeException{

	/**
	 *
	 * Cr&#233;e l'exception
	 *
	 * @param message le message de l'exception
	 *
	 */
	public InvalidDataException(String message){
		super(message);
	}
}