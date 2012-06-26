/**
 * 
 */
package ar.com.agtech.syros.fecae.exceptions;


/**
 * @author Jorge Morando
 *
 */
public class InvalidInvoiceNumberException extends InvalidInvoiceException {

	private static final long serialVersionUID = -1690750835866242609L;

	public InvalidInvoiceNumberException(String message){
		super(message);
	}
	
	public InvalidInvoiceNumberException(String message,Throwable cause){
		super(message,cause);
	}
	
}
