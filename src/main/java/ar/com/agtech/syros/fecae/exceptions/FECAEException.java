/**
 * 
 */
package ar.com.agtech.syros.fecae.exceptions;

/**
 * @author Jorge Morando
 *
 */
public class FECAEException extends Exception {

	private static final long serialVersionUID = -5959785970399569255L;

	public FECAEException(String message){
		super(message);
	}
	
	public FECAEException(String message,Throwable cause){
		super(message,cause);
	}
	
}
