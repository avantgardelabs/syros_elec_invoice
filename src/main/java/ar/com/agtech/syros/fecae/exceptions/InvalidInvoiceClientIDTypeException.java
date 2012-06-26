/**
 * 
 */
package ar.com.agtech.syros.fecae.exceptions;


/**
 * @author Jorge Morando
 *
 */
public class InvalidInvoiceClientIDTypeException extends InvalidInvoiceException {

	private static final long serialVersionUID = 610993068828726970L;

	public InvalidInvoiceClientIDTypeException(String message){
		super(message);
	}
	
	public InvalidInvoiceClientIDTypeException(String message,Throwable cause){
		super(message,cause);
	}
	
}
