/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.exceptions;

import ar.com.agtech.syros.fecae.exceptions.FECAEException;

/**
 * @author Jorge Morando
 *
 */
public class InvalidConceptTypeException extends FECAEException {

	private static final long serialVersionUID = 6364895000617167516L;

	public InvalidConceptTypeException(String message){
		super(message);
	}
	
	public InvalidConceptTypeException(String message,Throwable cause){
		super(message,cause);
	}
	
}
