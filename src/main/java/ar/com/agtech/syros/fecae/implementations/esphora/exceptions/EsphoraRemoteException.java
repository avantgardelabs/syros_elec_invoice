/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.exceptions;

/**
 * @author Jorge Morando
 *
 */
public class EsphoraRemoteException extends EsphoraException {

	private static final long serialVersionUID = 9097198287865132201L;

	public EsphoraRemoteException(String message){
		super(message);
	}
	
	public EsphoraRemoteException(String message,Throwable cause){
		super(message,cause);
	}
	
}
