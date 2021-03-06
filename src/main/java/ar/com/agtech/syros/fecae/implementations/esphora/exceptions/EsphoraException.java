/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.exceptions;

import ar.com.agtech.syros.fecae.exceptions.FECAEException;

/**
 * @author Jorge Morando
 *
 */
public class EsphoraException extends FECAEException {

	private static final long serialVersionUID = 9097198287865132201L;

	public EsphoraException(String message){
		super(message);
	}
	
	public EsphoraException(String message,Throwable cause){
		super(message,cause);
	}
	
}
