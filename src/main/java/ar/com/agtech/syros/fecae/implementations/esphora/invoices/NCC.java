/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.invoices;

import ar.com.agtech.syros.fecae.elements.Cuit;
import ar.com.agtech.syros.fecae.elements.Identification;
import ar.com.agtech.syros.fecae.elements.Importe;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraInternalException;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoConcepto;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoDocumento;

/**
 * @author Jorge Morando
 * 
 */
public class NCC extends ComprobanteFiscal {
	
	/**
	 * <b>NO APTA PARA FACTURACI&Oacute;N ELECTR&Oacute;NICA MASIVA</b><br>
	 * Crea un Objeto de tipo Nota de Cr&eacute;dito C<br>
	 */
	public NCC(){
		super();
	}

	/**
	 * <b>NO APTA PARA FACTURACI&Oacute;N ELECTR&Oacute;NICA MASIVA</b><br>
	 * Crea un Objeto de tipo Nota de Cr&eacute;dito C listo para autorizar electr&oacute;nicamente<br>
	 * @param cuitFacturador
	 * @param tipoDocCliente
	 * @param nroDocCliente
	 * @param tipoConcepto
	 * @param puntoDeVenta
	 * @param nroComprobante
	 * @param importe
	 * @throws EsphoraInternalException
	 */
	public NCC(Cuit cuitFacturador, 
			TipoDocumento tipoDocCliente,
			Identification docCliente, 
			TipoConcepto tipoConcepto, 
			Integer puntoDeVenta, 
			Importe importe) throws EsphoraInternalException{
		super();
		this.setTipo(TipoComprobante.NOTA_CREDITO_C);
		this.setCuitFacturador(cuitFacturador);
		this.setTipoDocumentoDeCliente(tipoDocCliente);
		this.setDocumentoDeCliente(docCliente);
		this.setPuntoDeVenta(puntoDeVenta);
		this.setConcepto(tipoConcepto);
		this.setImporte(importe);
	}
	
	/**
	 * Crea un Objeto de tipo Nota de Cr&eacute;dito C listo para adjuntar a una lote de facturas del mismo tipo <br>
	 * para facturar electr&oacute;nicamente.
	 * @param cuitFacturador
	 * @param tipoDocCliente
	 * @param nroDocCliente
	 * @param tipoConcepto
	 * @param puntoDeVenta
	 * @param nroComprobante
	 * @param importe
	 * @throws EsphoraInternalException
	 */
	public NCC(TipoDocumento tipoDocCliente,
			Identification docCliente,
			TipoConcepto tipoConcepto, 
			Importe importe) throws EsphoraInternalException{
		super();
		this.setMasiva(true);
		this.setTipo(TipoComprobante.NOTA_CREDITO_C);
		this.setTipoDocumentoDeCliente(tipoDocCliente);
		this.setDocumentoDeCliente(docCliente);
		this.setConcepto(tipoConcepto);
		this.setImporte(importe);
	}
}
