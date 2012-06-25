/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.log4j.Logger;

import ar.com.agtech.syros.fecae.elements.Cuit;
import ar.com.agtech.syros.fecae.elements.Importe;
import ar.com.agtech.syros.fecae.exceptions.FECAEException;
import ar.com.agtech.syros.fecae.implementations.behaviors.EsphoraResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.AlicIva;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.ArrayOfAlicIva;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.ArrayOfFECAEDetRequest;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.Err;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FECAECabRequest;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FECAEDetRequest;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FECAERequest;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FECAEResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FERecuperaLastCbteResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.EsphoraError;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.EsphoraObservacion;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.EsphoraSolicitarResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraConnectionException;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraInternalException;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraRemoteException;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraUnhandledException;
import ar.com.agtech.syros.fecae.implementations.esphora.invoices.ComprobanteFiscal;
import ar.com.agtech.syros.fecae.implementations.esphora.services.Wsfev1;
import ar.com.agtech.syros.fecae.implementations.esphora.services.Wsfev1Service;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoConcepto;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoDocumento;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoMoneda;
import ar.com.agtech.syros.fecae.implementations.esphora.utils.Util;

/**
 * Implementaci&oacute;n ESPHORA del Gatway {@link FECAEGateway}  de autorizaci&oacute;n de comprobantes fiscales
 * @author Jorge Morando
 * @see WebService http://ec2-50-17-85-59.compute-1.amazonaws.com:8080/esphora-conector/ws/wsfev1?wsdl
 */
public class EsphoraGateway implements FECAEGateway {

	/*
	 * Logger 
	 */
	private static final Logger log = Logger.getLogger(EsphoraGateway.class);
	
	/*
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1590185495627287987L;
	
	/*
	 * Web Service Proxy
	 */
	private Wsfev1 serviceProxy;
	
	/* (non-Javadoc)
	 * @see ar.com.agtech.syros.esphora.conector.elements.behaviors.WebServiceGateway#init()
	 */
	@Override
	public void init() throws EsphoraInternalException {
		init(null);
	}

	/* (non-Javadoc)
	 * @see ar.com.agtech.syros.esphora.conector.elements.behaviors.WebServiceGateway#init(java.lang.String)
	 */
	@Override
	public void init(String wsdlLoc) throws EsphoraInternalException {
		try {
			String urlLocation = wsdlLoc;
			Wsfev1Service service=null;
			URL wsdlLocation=null;
			if (urlLocation != null && !urlLocation.trim().isEmpty()){
				wsdlLocation = this.getWsdlLocation(urlLocation);				
			} else {
				wsdlLocation = Thread.currentThread().getContextClassLoader().getResource("wsfev1.wsdl");				
			}
			service = new Wsfev1Service(wsdlLocation);
			serviceProxy = service.getWsfev1Port();
			log.info("WS Interface stablished...");
		} catch (SecurityException e) {
			log.error("Error retreiving WSDL",e);
			throw new EsphoraInternalException("Local WSDL could not be retreived",e);
		} catch (WebServiceException e) {
			log.error("Error retreiving WS Port",e);
			EsphoraConnectionException ece = new EsphoraConnectionException("No se pudo establecer el puente con los WS de Esphora",e);
			throw new EsphoraInternalException("No open connection with WSPort",ece);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.agtech.syros.esphora.conector.elements.behaviors.AuthorizeSimple#authorize(ar.com.agtech.syros.esphora.conector.invoices.Comprobante)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <C extends ComprobanteFiscal> C authorize(C cf) throws EsphoraInternalException {
		
		if(cf==null)
			throw new EsphoraInternalException("NULL Invoice given");
		
		if(cf.isMasiva()){
			throw new EsphoraInternalException("Invoice of:"+
			cf.getImporte().getBruto()+
			" is ready for batch electronic authorization and not single authorization, construct "+
			cf.getClass().getSimpleName()+
			" properly");
		}
		List<C> cfList = new ArrayList<C>();
		cfList.add(cf);
		EsphoraResponse response = authorize(cf.getTipo(), cf.getPuntoDeVenta(), cf.getCuitFacturador(), cfList);
		if(response.hasGlobalErrors()){
			for (EsphoraError error : response.getGlobalErrors()) {
				response.getAllApproved().get(0).getObservaciones()
					.add(new EsphoraObservacion(error.getOriginalError()));
			}
		}
		return (C) response.getAllApproved().get(0);
		
	}

	/* (non-Javadoc)
	 * @see ar.com.agtech.syros.esphora.conector.elements.behaviors.AuthorizeMultiple#authorize(java.util.List)
	 */
	@Override
	public <C extends ComprobanteFiscal> EsphoraResponse authorize(
			TipoComprobante tipoCbte, 
			Integer ptoVta, 
			Cuit cuitFacturador, 
			List<C> cfList) throws EsphoraInternalException{
		
		
		int nroCbteSecuencial;
		
		try {
			nroCbteSecuencial = getLastInvoiceNumber(tipoCbte, ptoVta, cuitFacturador);
		} catch (EsphoraRemoteException e) {
			throw new EsphoraInternalException("Remote Exception Handled",e);
		}
		
		/*generamos el cuerpo del request*/
		log.info("Header Generation...");
		FECAECabRequest cabecera = generarCabecera(tipoCbte,ptoVta,cfList.size());
		log.debug("Body Generation...");
		ArrayOfFECAEDetRequest lote = new ArrayOfFECAEDetRequest();
		for (ComprobanteFiscal cf : cfList) {
			nroCbteSecuencial++;
			
			cf.setNumeroComprobante(nroCbteSecuencial);//set correct invoice number
			
			FECAEDetRequest cuerpo = generarCuerpo(cf.getImporte(), 
					cf.getConcepto(), 
					cf.getTipoDocumentoDeCliente(),
					cf.getDocumentoDeCliente().getId(),
					nroCbteSecuencial,
					cf.getMoneda());
			
			lote.getFECAEDetRequest().add(cuerpo);
		}
		
		checkReadyness(cfList);
		
		FECAERequest req = new FECAERequest();
		req.setFeCabReq(cabecera);
		req.setFeDetReq(lote);
		
		log.debug("Requesting...");
		FECAEResponse resp = null;
		
		try {
			resp = serviceProxy.fecaeSolicitar(req, cuitFacturador.getId());
		} catch (SOAPFaultException e) {
			log.error("Remote Error Catched...",e);
			EsphoraRemoteException ere = new EsphoraRemoteException("Incorrect SOAP/xml Response",e);
			throw new EsphoraInternalException("SOAP Response could not be parsed",ere);
		}
		
		return new EsphoraSolicitarResponse<C>(resp,cfList);
	}
	
	
	/* *****************PRIVATE METHODS********************* */
	
	private URL getWsdlLocation(String location) throws EsphoraInternalException{
		URL url = null;
		String urlWsdl = location;
        try {        	
            url = new URL(urlWsdl);
        } catch (MalformedURLException e) {
        	log.error( "Can not initialize the wsdl from: "+ urlWsdl);            
        	throw new EsphoraInternalException("Incorrect WSDL URL", e);
        }
        return url;		
	}
	
	private  <C extends ComprobanteFiscal>  void checkReadyness(List<C> cfList) 
			throws EsphoraInternalException{
		if(cfList!=null && cfList.size()>1){
			try {
				for (C cf : cfList) {
					cf.isComplete();
				}
			} catch (FECAEException e) {
				throw new EsphoraInternalException("Invoices not ready for authorization",e);
			}
		}else if(cfList==null){
			throw new EsphoraInternalException("No invoice given for authorization");
		}
	}
	
	private int getLastInvoiceNumber(TipoComprobante tipoCbte, Integer ptoVta, Cuit cuitFacturador)
		throws EsphoraInternalException, EsphoraRemoteException {
		
		FERecuperaLastCbteResponse ultimoResp;
		
		try {
			ultimoResp = serviceProxy.feCompUltimoAutorizado(tipoCbte.getId(), ptoVta, cuitFacturador.getId());		
		} catch (SOAPFaultException e) {
			log.error("Remote Error Catched...",e);
			EsphoraRemoteException ere = new EsphoraRemoteException("Incorrect SOAP/xml Response",e);
			throw new EsphoraInternalException("SOAP Response could not be parsed",ere);
		}
		

		if(ultimoResp==null){
			throw new EsphoraInternalException("Null response received when trying to connect to esphora");
		}else if(ultimoResp.getErrors() != null){
			List<Err> errors = ultimoResp.getErrors().getErr();
			EsphoraRemoteException stack = null;
			for (Err error : errors) {
				if(stack == null)
					stack = new EsphoraRemoteException(error.getMsg());
				else
					stack = new EsphoraRemoteException(error.getMsg(),stack);
			}
			throw new EsphoraInternalException("Errors received when asking for last invoice number for type="+tipoCbte.getId()+" and ptoVta="+ptoVta, stack );
		}else{
			return ultimoResp.getCbteNro();
		}
	}
	
	private static FECAECabRequest generarCabecera(TipoComprobante cbte, int ptoVta,int cantidad) 
		throws EsphoraUnhandledException{
		try {
			FECAECabRequest header = new FECAECabRequest();
			header.setCantReg(cantidad);
			int comprobante = Util.obtenerParametroTipoComprobante(cbte);
			header.setCbteTipo(comprobante);
			header.setPtoVta(ptoVta);
			return header;
		} catch (Exception e) {
			throw new EsphoraUnhandledException("Unhandled Esphora Exception",e);
		}
	}
	
	private static FECAEDetRequest generarCuerpo(Importe importe, TipoConcepto cpto, TipoDocumento td, long nroDoc, long nroCbte, TipoMoneda tMon)
		throws EsphoraInternalException {
		
		try {
			FECAEDetRequest cuerpo = new FECAEDetRequest();
			
			cuerpo.setConcepto(cpto.getId());
			cuerpo.setDocTipo(td.getId());

			cuerpo.setDocNro(nroDoc);
			cuerpo.setCbteDesde(nroCbte);
			cuerpo.setCbteHasta(nroCbte);
			
			String fechaCbte = Util.dateTransform(new Date());
			cuerpo.setCbteFch(fechaCbte);
			
			if(cpto != TipoConcepto.PRODUCTOS){
				String fechaVtoPago = Util.dateTransform(new Date());
				cuerpo.setFchVtoPago(fechaVtoPago);
				cuerpo.setFchServDesde(Util.dateTransform(new Date()));
				cuerpo.setFchServHasta(Util.dateTransform(new Date()));
			}
			
			cuerpo.setImpOpEx(0.00d);
			cuerpo.setImpTotConc(0.00);
			cuerpo.setImpTrib(0.00d);
			cuerpo.setMonCotiz(1.00d);
			cuerpo.setMonId(tMon.getId());
			
			cuerpo.setImpNeto(importe.getNeto().doubleValue());
			
			
			ArrayOfAlicIva objIva = new ArrayOfAlicIva();
			AlicIva iva = new AlicIva();
			iva.setBaseImp(importe.getNeto().doubleValue());
			iva.setId(Util.obtenerTipoIvaEsphora(importe.getTipoIva()));
			iva.setImporte(importe.getIva().doubleValue());
			objIva.getAlicIva().add(iva);
			cuerpo.setIva(objIva);
			cuerpo.setImpIVA(importe.getIva().doubleValue());
			
			cuerpo.setImpTotal(importe.getBruto().doubleValue());
			
			return cuerpo;
		} catch (Exception e) {
			throw new EsphoraUnhandledException("Unhandled Esphora Exception",e);
		}
	}

}
