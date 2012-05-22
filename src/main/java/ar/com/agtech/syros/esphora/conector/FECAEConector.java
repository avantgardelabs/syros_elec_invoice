package ar.com.agtech.syros.esphora.conector;

import java.io.Serializable;

import ar.com.agtech.syros.esphora.conector.elements.FESimpleResponse;
import ar.com.agtech.syros.esphora.conector.elements.FEUltimoResponse;
import ar.com.agtech.syros.esphora.conector.elements.Importe;
import ar.com.agtech.syros.esphora.conector.elements.TipoComprobante;
import ar.com.agtech.syros.esphora.conector.elements.TipoConcepto;
import ar.com.agtech.syros.esphora.conector.elements.TipoDocumento;
import ar.com.agtech.syros.esphora.conector.exceptions.EsphoraConnectionException;
import ar.com.agtech.syros.esphora.conector.exceptions.EsphoraException;

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
			Importe importe) throws EsphoraException;
	
	/*GET PARAMS*/
	
	/**
	 * Retorna una lista con los posibles tipos de concepto aplicable a la facturaci&oacute;n
	 * @param cuit
	 * @return List&lt;ConceptoTipo&gt;
	 * @throws EsphoraException
	 */
//	List<ConceptoTipo> getTiposConcepto(String cuit) throws EsphoraException;
	
	/**
	 * Retorna el numero del ultimo comprobante registrado
	 * @param cbte
	 * @param ptoVta
	 * @param cuitFacturador
	 * @return
	 * @throws EsphoraException
	 */
	FEUltimoResponse getFeCompUltimoAutorizado(TipoComprobante cbte, 
			Integer ptoVta,
			Long cuitFacturador) throws EsphoraException;
	
	/**
	 * Establece la conexi&oacute;n con el ws de Esphora
	 * @throws EsphoraConnectionException
	 */
	void init() throws EsphoraConnectionException;
	
	
	/**
	 * Establece la conexi&oacute;n con el ws de Esphora teniendo en cuenta el wsdl aportado
	 * @param wsdlLocation el url del wsdl que al que se quiere apuntar
	 * @throws EsphoraConnectionException
	 */
	void init(String wsdlLocation) throws EsphoraConnectionException;
}
