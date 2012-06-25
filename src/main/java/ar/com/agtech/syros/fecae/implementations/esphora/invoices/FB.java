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
public class FB extends ComprobanteFiscal {

	public FB(Long cuitFacturador, TipoDocumento tipoDocCliente,
			Long nroDocCliente, TipoConcepto tipoItem, Integer puntoDeVenta,
			Integer nroComprobante, Importe importe) throws EsphoraInternalException{

		super(cuitFacturador, tipoDocCliente, nroDocCliente, tipoItem,
				TipoComprobante.FACTURA_B, puntoDeVenta, nroComprobante,
				importe);
	}

}
