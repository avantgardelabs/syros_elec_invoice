/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.exceptions;

/**
 * @author Jorge Morando
 *
 */
public class EsphoraException extends Exception {

	private static final long serialVersionUID = 9097198287865132201L;

	public EsphoraException(String message){
		super(message);
	}
	
	public EsphoraException(String message,Throwable cause){
		super(message,cause);
	}
	
}
