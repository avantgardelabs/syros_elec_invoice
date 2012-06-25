/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.exceptions;

import ar.com.agtech.syros.fecae.exceptions.FECAEException;

/**
 * @author Jorge Morando
 *
 */
public class InvalidInvoiceNumberException extends FECAEException {

	private static final long serialVersionUID = -1690750835866242609L;

	public InvalidInvoiceNumberException(String message){
		super(message);
	}
	
	public InvalidInvoiceNumberException(String message,Throwable cause){
		super(message,cause);
	}
	
}
