/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.exceptions;

/**
 * @author Jorge Morando
 *
 */
public class EsphoraInternalException extends EsphoraException {

	private static final long serialVersionUID = 9097198287865132201L;

	public EsphoraInternalException(String message){
		super(message);
	}
	
	public EsphoraInternalException(String message,Throwable cause){
		super(message,cause);
	}
	
}
