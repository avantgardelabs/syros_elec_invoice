/**
 * 
 */
package ar.com.agtech.syros.fecae.exceptions;


/**
 * @author Jorge Morando
 *
 */
public class InvalidInvoiceFeeException extends InvalidInvoiceException {

	private static final long serialVersionUID = 610993068828726970L;

	public InvalidInvoiceFeeException(String message){
		super(message);
	}
	
	public InvalidInvoiceFeeException(String message,Throwable cause){
		super(message,cause);
	}
	
}
