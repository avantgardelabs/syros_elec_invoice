/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.exceptions;

import ar.com.agtech.syros.fecae.exceptions.FECAEException;

/**
 * @author Jorge Morando
 *
 */
public class InvalidFeeException extends FECAEException {

	private static final long serialVersionUID = 610993068828726970L;

	public InvalidFeeException(String message){
		super(message);
	}
	
	public InvalidFeeException(String message,Throwable cause){
		super(message,cause);
	}
	
}
