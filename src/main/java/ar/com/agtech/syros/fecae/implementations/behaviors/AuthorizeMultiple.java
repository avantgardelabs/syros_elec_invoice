/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.behaviors;

import java.util.List;

import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraInternalException;
import ar.com.agtech.syros.fecae.implementations.esphora.invoices.ComprobanteFiscal;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;

/**
 * @author Jorge Morando
 * 
 */
public interface AuthorizeMultiple {
	
	/**
	 * Autoriza multiplesFacturas.
	 * 
	 * @see ar.com.agtech.syros.esphora.conector.ComprobanteFiscal.Invoice
	 * @return List&lt;I extends Invoice&gt; invoices
	 */
	EsphoraResponse authorize(TipoComprobante cbte, Integer ptoVta, Long cuitFaturador, List<? extends ComprobanteFiscal> comprobantes) throws EsphoraInternalException;
}
