/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.behaviors;

import java.util.List;

import ar.com.agtech.syros.fecae.elements.GatewayResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.EsphoraError;
import ar.com.agtech.syros.fecae.implementations.esphora.invoices.ComprobanteFiscal;


/**
 * @author Jorge Morando
 */
public interface EsphoraResponse extends GatewayResponse {
	
	public static final int ESTADO_NOK = 0;
	public static final int ESTADO_OK = 1;
	public static final int ESTADO_ERROR = 666;
	
	public static final String RESULTADO_RECHAZADA = "R";
	public static final String RESULTADO_APROBADA = "A";
	public static final String RESULTADO_PARCIAL = "P";
	
	int getTotalApproved();

	int getTotalRejected();
	
	String getResultado();
	
	boolean hasGlobalErrors();
	
	<C extends ComprobanteFiscal> List<C> getAllApproved();
	
	<C extends ComprobanteFiscal> List<C> getAllRejected();
	
	List<EsphoraError> getGlobalErrors();
	
}
