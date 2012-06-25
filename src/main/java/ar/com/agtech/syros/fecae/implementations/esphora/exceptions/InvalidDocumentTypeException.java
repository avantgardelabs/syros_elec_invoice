/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.exceptions;

import ar.com.agtech.syros.fecae.exceptions.FECAEException;

/**
 * @author Jorge Morando
 *
 */
public class InvalidDocumentTypeException extends FECAEException {

	private static final long serialVersionUID = 610993068828726970L;

	public InvalidDocumentTypeException(String message){
		super(message);
	}
	
	public InvalidDocumentTypeException(String message,Throwable cause){
		super(message,cause);
	}
	
}
