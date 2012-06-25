/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.exceptions;

/**
 * @author Jorge Morando
 *
 */
public class EsphoraUnhandledException extends EsphoraInternalException {

	private static final long serialVersionUID = 9097198287865132201L;

	public EsphoraUnhandledException(String message){
		super(message);
	}
	
	public EsphoraUnhandledException(String message, Throwable cause){
		super(message,cause);
	}
	
}
