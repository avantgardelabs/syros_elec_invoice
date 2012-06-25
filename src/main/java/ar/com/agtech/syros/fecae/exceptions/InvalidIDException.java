/**
 * 
 */
package ar.com.agtech.syros.fecae.exceptions;

/**
 * @author Jorge Morando
 *
 */
public class InvalidIDException extends FECAEException {

	private static final long serialVersionUID = 1810077515869774517L;

	public InvalidIDException(String message){
		super(message);
	}
	
	public InvalidIDException(String message,Throwable cause){
		super(message,cause);
	}
	
}
