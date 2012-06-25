/**
 * 
 */
package ar.com.agtech.syros.fecae.exceptions;

/**
 * @author Jorge Morando
 *
 */
public class InvalidCUITException extends Exception {


	private static final long serialVersionUID = 1810077515869774517L;

	public InvalidCUITException(String message){
		super(message);
	}
	
	public InvalidCUITException(String message,Throwable cause){
		super(message,cause);
	}
	
}
