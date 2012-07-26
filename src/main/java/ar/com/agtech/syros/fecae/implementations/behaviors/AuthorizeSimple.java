/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.behaviors;

import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraInternalException;
import ar.com.agtech.syros.fecae.implementations.esphora.invoices.ComprobanteFiscal;

/**
 * @author Jorge Morando
 * 
 */
public interface AuthorizeSimple {
	
	/**
	 * Autoriza una factura.
	 * 
	 * @see ar.com.agtech.syros.fecae.implementations.esphora.invoices.ComprobanteFiscal
	 * @return
	 */
	<C extends ComprobanteFiscal> C authorize(C comprobante) throws EsphoraInternalException;
	
}
