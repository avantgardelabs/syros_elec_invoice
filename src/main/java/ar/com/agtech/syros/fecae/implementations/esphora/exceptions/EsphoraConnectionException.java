/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.exceptions;

/**
 * @author Jorge Morando
 *
 */
public class EsphoraConnectionException extends EsphoraRemoteException {

	private static final long serialVersionUID = 9097198287865132201L;

	public EsphoraConnectionException(String message){
		super(message);
	}
	
	public EsphoraConnectionException(String message,Throwable cause){
		super(message,cause);
	}
	
}
