/**
 * 
 */
package ar.com.agtech.syros.fecae.exceptions;


/**
 * @author Jorge Morando
 *
 */
public class InvalidInvoiceTypeException extends InvalidInvoiceException {

	private static final long serialVersionUID = 2336401628637714970L;

	public InvalidInvoiceTypeException(String message){
		super(message);
	}
	
	public InvalidInvoiceTypeException(String message,Throwable cause){
		super(message,cause);
	}
	
}
