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
	
	int getTotalApproved();

	int getTotalRejected();
	
	String getResultado();
	
	boolean hasGlobalErrors();
	
	<C extends ComprobanteFiscal> List<C> getAllApproved();
	
	<C extends ComprobanteFiscal> List<C> getAllRejected();
	
	List<EsphoraError> getGlobalErrors();
	
}
