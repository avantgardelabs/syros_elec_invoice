/**
 * 
 */
package ar.com.agtech.syros.fecae.exceptions;


/**
 * @author Jorge Morando
 *
 */
public class InvalidInvoiceSalePointException extends InvalidInvoiceException {

	private static final long serialVersionUID = 610993068828726970L;

	public InvalidInvoiceSalePointException(String message){
		super(message);
	}
	
	public InvalidInvoiceSalePointException(String message,Throwable cause){
		super(message,cause);
	}
	
}
