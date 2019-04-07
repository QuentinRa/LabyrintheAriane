package source.tools.exceptions;

/**
*
* InvalidDataException est une exception lev&#2333;e si les donn&#2333;es ne sont
* pas conformes à ce que l'on attendait.
*
* @version 1.0 6 avril 2019
* @author Quentin Ramsamy--Ageorges
*
*/
public class InvalidDataException extends RuntimeException{

	/**
	*
	* Cr&#2333;e l'exception
	*
	* @param message le message de l'exception
	*
	*/
	public InvalidDataException(String message){
		super(message);
	}
}