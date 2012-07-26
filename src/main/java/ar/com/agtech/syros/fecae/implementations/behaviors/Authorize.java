/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.behaviors;

import ar.com.agtech.syros.fecae.elements.Cuit;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraInternalException;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;


/**
 * @author Jorge Morando
 */
public interface Authorize extends AuthorizeSimple, AuthorizeMultiple {
	
	/**
	 * Verifica el &uacute;ltimo n&uacute;mero de comprobante de un tipo espec&iacute;fico
	 * 
	 * @see ar.com.agtech.syros.fecae.implementations.esphora.invoices.ComprobanteFiscal
	 * @return
	 */
	Integer obtenerUltimoComprobante(TipoComprobante tipoCbte, Integer ptoVta, Cuit cuitFacturador) throws EsphoraInternalException;
	
}
