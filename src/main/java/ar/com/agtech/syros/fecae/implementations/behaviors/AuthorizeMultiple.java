/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.behaviors;

import java.util.List;

import ar.com.agtech.syros.fecae.elements.Cuit;
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
	 * @see ar.com.agtech.syros.fecae.implementations.esphora.invoices.ComprobanteFiscal
	 * @return List&lt;I extends Invoice&gt; invoices
	 */
	<C extends ComprobanteFiscal> EsphoraResponse authorize(TipoComprobante cbte, Integer ptoVta, Cuit cuitFaturador, List<C> comprobantes, Integer lastCompNumber) throws EsphoraInternalException;
}
