/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.invoices;

import ar.com.agtech.syros.fecae.elements.Importe;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraInternalException;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoConcepto;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoDocumento;

/**
 * @author Jorge Morando
 * 
 */
public class FA extends ComprobanteFiscal {

	public FA(Long cuitFacturador, TipoDocumento tipoDocCliente,
			Long nroDocCliente, TipoConcepto tipoConcepto, Integer puntoDeVenta,
			Integer nroComprobante, Importe importe) throws EsphoraInternalException{

		super(cuitFacturador, tipoDocCliente, nroDocCliente, tipoConcepto,
				TipoComprobante.FACTURA_A, puntoDeVenta, nroComprobante,
				importe);
	}

}
