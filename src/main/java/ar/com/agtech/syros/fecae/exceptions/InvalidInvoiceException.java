/**
 * 
 */
package ar.com.agtech.syros.fecae.exceptions;


/**
 * @author Jorge Morando
 *
 */
public class InvalidInvoiceException extends FECAEException {

	private static final long serialVersionUID = -6831675063869939076L;

	public InvalidInvoiceException(String message){
		super(message);
	}
	
	public InvalidInvoiceException(String message,Throwable cause){
		super(message,cause);
	}
	
}
