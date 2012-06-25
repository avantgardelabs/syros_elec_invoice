package ar.com.agtech.syros.fecae.implementations.esphora.invoices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.agtech.syros.fecae.elements.Importe;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.Obs;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.EsphoraObservacion;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraInternalException;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoConcepto;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoDocumento;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoMoneda;

/**
 * El Comprobante a autorizar 
 * @author Jorge Morando
 *
 */
public abstract class ComprobanteFiscal {

	private Importe importe;
	
	private TipoComprobante tipo;
	
	private TipoMoneda moneda;
	
	private Long cuitFacturador;
	
	private Integer puntoDeVenta;
	
	private Integer numeroComprobante;
	
	private TipoConcepto concepto;
	
	private TipoDocumento tipoDocumentoDeCliente;
	
	private Long documentoDeCliente;
	
	private List<EsphoraObservacion> observaciones;
	
	private String cae;
	
	private String estado;
	
	private Date vtoCae;
	
	/**
	 * @param cuitFacturador
	 * @param tipoDoc
	 * @param nroDoc
	 * @param cpto
	 * @param tipoCbte
	 * @param ptoVta
	 * @param nroCbte
	 * @param importe
	 */
	public ComprobanteFiscal(
			Long cuitFacturador, 
			TipoDocumento tipoDoc,
			Long nroDoc, 
			TipoConcepto cpto, 
			TipoComprobante tipoCbte,
			Integer ptoVta,
			Integer nroCbte,
			Importe importe
			) throws EsphoraInternalException {
		
		if(ptoVta == null){
			throw new EsphoraInternalException("El punto de venta es nulo");
		}else{
			if(ptoVta<1)
				throw new EsphoraInternalException("El punto de venta debe ser mayor a 0");
		}
		if(nroDoc == null) 
			throw new EsphoraInternalException("El Tipo de Documento es nulo");
		if(nroDoc<1)
			throw new EsphoraInternalException("El numero de documento es invalido");
		if(tipoCbte==null) 
			throw new EsphoraInternalException("El concepto es nulo");
		if(importe==null)
			throw new EsphoraInternalException("El importe es nulo");
		if(cpto == null)
			throw new EsphoraInternalException("El concepto es nulo");
		
		
		this.cuitFacturador = cuitFacturador;
		this.tipo = tipoCbte;
		this.numeroComprobante = nroCbte;
		this.puntoDeVenta = ptoVta;
		this.concepto = cpto;
		this.tipoDocumentoDeCliente = tipoDoc;
		this.documentoDeCliente = nroDoc;
		this.importe = importe;
		this.moneda=TipoMoneda.PESOS_ARGENTINOS;
		
		
		
	};
	
	/**
	 * @return El importe completo
	 * @see ar.com.agtech.syros.elements.Importe
	 */
	public Importe getImporte() {
		return importe;
	}

	/**
	 * @return El tipo de comprobante
	 */
	public TipoComprobante getTipo() {
		return tipo;
	}

	/**
	 * @return El tipo de moneda utilizada en el comprobante
	 */
	public TipoMoneda getMoneda() {
		return moneda;
	}

	/**
	 * @return El CUIT de la entidad que libera el comprobante
	 */
	public Long getCuitFacturador() {
		return cuitFacturador;
	}

	/**
	 * @return El n&uacute;mero de punto de venta
	 */
	public Integer getPuntoDeVenta() {
		return puntoDeVenta;
	}

	/**
	 * @return El n&uacute;mero de comprobante
	 */
	public Integer getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * @return El concepto del comprobante
	 */
	public TipoConcepto getConcepto() {
		return concepto;
	}

	/**
	 * @return El tipo de documento especificado
	 */
	public TipoDocumento getTipoDocumentoDeCliente() {
		return tipoDocumentoDeCliente;
	}

	/**
	 * @return El n&uacute;mero del documento especificado 
	 */
	public Long getDocumentoDeCliente() {
		return documentoDeCliente;
	}

	public void setTipo(TipoComprobante tipo) {
		this.tipo = tipo;
	}

	public void setCuitFacturador(Long cuitFacturador) {
		this.cuitFacturador = cuitFacturador;
	}

	public void setNumeroComprobante(Integer numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * @return the observaciones
	 */
	public List<EsphoraObservacion> getObservaciones() {
		if(observaciones==null){
			observaciones= new ArrayList<EsphoraObservacion>();
		}		
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(List<EsphoraObservacion> observaciones) {
		this.observaciones = observaciones;
	}
	
	public void addObservacion(Obs ro){
		if(observaciones==null){
			observaciones= new ArrayList<EsphoraObservacion>();
		}
		addObservacion(new EsphoraObservacion(ro));
	}
	
	public void addObservacion(EsphoraObservacion o){
		if(observaciones==null){
			observaciones= new ArrayList<EsphoraObservacion>();
		}
		observaciones.add(o);
	}

	/**
	 * @return the cae
	 */
	public String getCae() {
		return cae;
	}

	/**
	 * @param cae the cae to set
	 */
	public void setCae(String cae) {
		this.cae = cae;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * @return the vtoCae
	 */
	public Date getVtoCae() {
		return vtoCae;
	}

	/**
	 * @param vtoCae the vtoCae to set
	 */
	public void setVtoCae(Date vtoCae) {
		this.vtoCae = vtoCae;
	}
	
}
