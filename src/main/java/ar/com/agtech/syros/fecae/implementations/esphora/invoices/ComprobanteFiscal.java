package ar.com.agtech.syros.fecae.implementations.esphora.invoices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.agtech.syros.fecae.elements.Cuit;
import ar.com.agtech.syros.fecae.elements.GenericID;
import ar.com.agtech.syros.fecae.elements.Identification;
import ar.com.agtech.syros.fecae.elements.Importe;
import ar.com.agtech.syros.fecae.elements.types.TipoCategoria;
import ar.com.agtech.syros.fecae.exceptions.FECAEException;
import ar.com.agtech.syros.fecae.exceptions.InvalidIDException;
import ar.com.agtech.syros.fecae.exceptions.InvalidInvoiceClientIDTypeException;
import ar.com.agtech.syros.fecae.exceptions.InvalidInvoiceConceptTypeException;
import ar.com.agtech.syros.fecae.exceptions.InvalidInvoiceException;
import ar.com.agtech.syros.fecae.exceptions.InvalidInvoiceFeeException;
import ar.com.agtech.syros.fecae.exceptions.InvalidInvoiceNumberException;
import ar.com.agtech.syros.fecae.exceptions.InvalidInvoiceSalePointException;
import ar.com.agtech.syros.fecae.exceptions.InvalidInvoiceTypeException;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.Obs;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.EsphoraObservacion;
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
	
	private TipoCategoria categoria;
	
	private Importe importe;
	
	private TipoComprobante tipo;
	
	private TipoMoneda moneda;
	
	private Cuit cuitFacturador;
	
	private Integer puntoDeVenta;
	
	private Integer numeroComprobante;
	
	private TipoConcepto concepto;
	
	private TipoDocumento tipoDocumentoDeCliente;
	
	private Identification documentoDeCliente;
	
	private List<EsphoraObservacion> observaciones;
	
	private String cae;
	
	private int estado;
	
	private String resultado;
	
	private Date vtoCae;
	
	private boolean masiva;
	
	/**
	 * Crea un Comprobante Fiscal.
	 */
	public ComprobanteFiscal() {
		this.moneda=TipoMoneda.PESOS_ARGENTINOS;
		this.masiva=false;
	}
	
	/**
	 * @return the importe
	 */
	public Importe getImporte() {
		return importe;
	}

	/**
	 * @return the tipo
	 */
	public TipoComprobante getTipo() {
		return tipo;
	}

	/**
	 * @return the moneda
	 */
	public TipoMoneda getMoneda() {
		return moneda;
	}

	/**
	 * @return the cuitFacturador
	 */
	public Cuit getCuitFacturador() {
		return cuitFacturador;
	}

	/**
	 * @return the puntoDeVenta
	 */
	public Integer getPuntoDeVenta() {
		return puntoDeVenta;
	}

	/**
	 * @return the numeroComprobante
	 */
	public Integer getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * @return the concepto
	 */
	public TipoConcepto getConcepto() {
		return concepto;
	}

	/**
	 * @return the tipoDocumentoDeCliente
	 */
	public TipoDocumento getTipoDocumentoDeCliente() {
		return tipoDocumentoDeCliente;
	}

	/**
	 * @return the documentoDeCliente
	 */
	public Identification getDocumentoDeCliente() {
		return documentoDeCliente;
	}

	/**
	 * @return the observaciones
	 */
	public List<EsphoraObservacion> getObservaciones() {
		if(observaciones==null) 
			observaciones= new ArrayList<EsphoraObservacion>();
		return observaciones;
	}

	/**
	 * @return the cae
	 */
	public String getCae() {
		return cae;
	}

	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}
	
	/**
	 * @return the estado
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * @return the vtoCae
	 */
	public Date getVtoCae() {
		return vtoCae;
	}

	/**
	 * @return the masiva
	 */
	public boolean isMasiva() {
		return masiva;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(Importe importe) {
		this.importe = importe;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoComprobante tipo) {
		this.tipo = tipo;
		if(tipo!=null){
			if(tipo.equals(TipoComprobante.FACTURA_A) || tipo.equals(TipoComprobante.NOTA_CREDITO_A) || tipo.equals(TipoComprobante.NOTA_DEBITO_A))
				this.categoria = TipoCategoria.A;
			if(tipo.equals(TipoComprobante.FACTURA_B) || tipo.equals(TipoComprobante.NOTA_CREDITO_B) || tipo.equals(TipoComprobante.NOTA_DEBITO_B))
				this.categoria = TipoCategoria.B;
			if(tipo.equals(TipoComprobante.FACTURA_C) || tipo.equals(TipoComprobante.NOTA_CREDITO_C) || tipo.equals(TipoComprobante.NOTA_DEBITO_C))
				this.categoria = TipoCategoria.C;
		}
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(TipoMoneda moneda) {
		this.moneda = moneda;
	}

	/**
	 * @param cuitFacturador the cuitFacturador to set
	 */
	public void setCuitFacturador(Cuit cuitFacturador) {
		this.cuitFacturador = cuitFacturador;
	}

	/**
	 * @param puntoDeVenta the puntoDeVenta to set
	 */
	public void setPuntoDeVenta(Integer puntoDeVenta) {
		this.puntoDeVenta = puntoDeVenta;
	}

	/**
	 * @param numeroComprobante the numeroComprobante to set
	 */
	public void setNumeroComprobante(Integer numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(TipoConcepto concepto) {
		this.concepto = concepto;
	}

	/**
	 * @param tipoDocumentoDeCliente the tipoDocumentoDeCliente to set
	 */
	public void setTipoDocumentoDeCliente(TipoDocumento tipoDocumentoDeCliente) {
		this.tipoDocumentoDeCliente = tipoDocumentoDeCliente;
	}

	/**
	 * @param documentoDeCliente the documentoDeCliente to set
	 */
	public void setDocumentoDeCliente(Identification documentoDeCliente) {
		if(documentoDeCliente == null)
			this.documentoDeCliente = new GenericID();
		else
			this.documentoDeCliente = documentoDeCliente;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(List<EsphoraObservacion> observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @param cae the cae to set
	 */
	public void setCae(String cae) {
		this.cae = cae;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	/**
	 * @param estado the estado to set
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	/**
	 * @param vtoCae the vtoCae to set
	 */
	public void setVtoCae(Date vtoCae) {
		this.vtoCae = vtoCae;
	}

	/**
	 * @param masiva the masiva to set
	 */
	public void setMasiva(boolean masiva) {
		this.masiva = masiva;
	}
	
	/**
	 * @param masiva the masiva to set
	 */
	public void addObservacion(Obs obs) {
		this.getObservaciones().add(new EsphoraObservacion(obs));
	}
	
	public boolean isComplete() throws FECAEException{
		boolean complete = true;
		if(!isMasiva()){
			cuitFacturador.validar();
			if(puntoDeVenta==null) throw new InvalidInvoiceSalePointException("Missing Sale Point");
		}
		if(tipo==null) throw new InvalidInvoiceTypeException("Missing Invoice Type");
		validarCategoriaConTipoDeComprobante();
		documentoDeCliente.validar();
		if(concepto==null) throw new InvalidInvoiceConceptTypeException("Missing Concept Type");
		if(numeroComprobante==null) throw new InvalidInvoiceNumberException("Missing Invoice Number");
		if(importe==null) throw new InvalidInvoiceFeeException("Missing Fee Amount");
		return complete;
	}
	
	private void validarCategoriaConTipoDeComprobante() throws InvalidInvoiceException, InvalidIDException{
		if(tipoDocumentoDeCliente==null) throw new InvalidInvoiceClientIDTypeException("Missing ID Type");
		if(categoria==null) throw new InvalidInvoiceException("Invalid Invoice Category");
		if(categoria.equals(TipoCategoria.A)){
			if(tipoDocumentoDeCliente.equals(TipoDocumento.OTRO)){ //si es A y no viene con tipoDeDocumento valido para a 
				throw new InvalidInvoiceClientIDTypeException("Invoice type A must have a valid ID Type");
			}else{
				if(documentoDeCliente==null) throw new InvalidIDException("Missing ID number");
			}
		}else{
			if(tipoDocumentoDeCliente.equals(TipoDocumento.OTRO)){
				if(!(documentoDeCliente instanceof GenericID))
					throw new InvalidIDException("Invoice ID not generic type");
			}else{
				if(documentoDeCliente instanceof GenericID)
					throw new InvalidIDException("Invoice ID generic type given but " + tipoDocumentoDeCliente.getClass().getSimpleName() + " specified");
			}
		}
	}
	
}
