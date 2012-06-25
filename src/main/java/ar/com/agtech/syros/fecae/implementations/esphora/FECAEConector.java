package ar.com.agtech.syros.fecae.implementations.esphora;

import java.io.Serializable;

import ar.com.agtech.syros.fecae.elements.Importe;
import ar.com.agtech.syros.fecae.implementations.FECAEGateway;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.FESimpleResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraConnectionException;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraException;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraInternalException;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoConcepto;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoDocumento;

/**
 * @author Jorge Morando
 * @deprecated Se implement&oacute; flujo optimizado utilizando {@link FECAEGateway} <br>
 * FECAEConector ser&aacute; eliminado en versiones posteriores a la 1.1.0
 * @version 0.1.3
 */
@Deprecated
public interface FECAEConector extends Serializable {

	/*SOLICITAR*/
	/**
	 * Genera una Factura Electr&oacute;nica Simple.
	 * @param cuitFacturador El nro de cuit del ente que factura
	 * @param cbte El tipo de comprobante que se generar&aacute;
	 * @param ptoVta El punto de venta en el que se registra la compra
	 * @param cpto El concepto que se factura
	 * @param tipoDoc El tipo de documento del cliente
	 * @param nroDoc El n&uacute;mero de documento del cliente
	 * @param nroCbte El n&uacute;mero de comprobante que se factura
	 * @param importe El importe que se factura
	 * @return FESimpleResponse;
	 * @throws EsphoraException
	 */
	FESimpleResponse generarFESimple(Long cuitFacturador, 
			TipoComprobante cbte,
			Integer ptoVta,
			TipoConcepto cpto, 
			TipoDocumento tipoDoc, long nroDoc,
			long nroCbte,
			Importe importe) throws EsphoraInternalException;
	
	/**
	 * Establece la conexi&oacute;n con el ws de Esphora
	 * @throws EsphoraConnectionException
	 */
	void init() throws EsphoraInternalException;
	
	/**
	 * Establece la conexi&oacute;n con el ws de Esphora teniendo en cuenta el wsdl aportado
	 * @param wsdlLocation el url del wsdl que al que se quiere apuntar
	 * @throws EsphoraConnectionException
	 */
	void init(String wsdlLocation) throws EsphoraInternalException;
}
