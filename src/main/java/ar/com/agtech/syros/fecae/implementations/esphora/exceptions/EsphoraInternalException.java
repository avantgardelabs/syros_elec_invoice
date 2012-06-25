/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.exceptions;

/**
 * @author Jorge Morando
 *
 */
public class EsphoraInternalException extends EsphoraException {


	private static final long serialVersionUID = 7917517589611593228L;

	public EsphoraInternalException(String message){
		super(message);
	}
	
	public EsphoraInternalException(String message,Throwable cause){
		super(message,cause);
	}
	
}
