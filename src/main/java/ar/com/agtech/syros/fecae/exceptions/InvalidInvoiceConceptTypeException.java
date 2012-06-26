/**
 * 
 */
package ar.com.agtech.syros.fecae.exceptions;


/**
 * @author Jorge Morando
 *
 */
public class InvalidInvoiceConceptTypeException extends InvalidInvoiceException {

	private static final long serialVersionUID = 6364895000617167516L;

	public InvalidInvoiceConceptTypeException(String message){
		super(message);
	}
	
	public InvalidInvoiceConceptTypeException(String message,Throwable cause){
		super(message,cause);
	}
	
}
