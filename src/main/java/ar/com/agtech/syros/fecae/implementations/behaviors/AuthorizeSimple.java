/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.behaviors;

import ar.com.agtech.syros.fecae.implementations.esphora.invoices.ComprobanteFiscal;

/**
 * @author Jorge Morando
 * 
 */
public interface AuthorizeSimple {
	
	/**
	 * Autoriza una factura.
	 * 
	 * @see ar.com.agtech.syros.esphora.conector.ComprobanteFiscal.Invoice
	 * @return
	 */
	<I extends ComprobanteFiscal> I authorize(I invoice);
	
}
