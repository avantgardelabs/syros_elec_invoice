package ar.com.agtech.syros.fecae.implementations;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.log4j.Logger;

import ar.com.agtech.syros.fecae.elements.Importe;
import ar.com.agtech.syros.fecae.implementations.esphora.FECAEConector;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.AlicIva;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.ArrayOfAlicIva;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.ArrayOfFECAEDetRequest;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FECAECabRequest;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FECAEDetRequest;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FECAERequest;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FECAEResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FERecuperaLastCbteResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.FESimpleResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.FEUltimoResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraConnectionException;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraInternalException;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraRemoteException;
import ar.com.agtech.syros.fecae.implementations.esphora.services.Wsfev1;
import ar.com.agtech.syros.fecae.implementations.esphora.services.Wsfev1Service;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoConcepto;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoDocumento;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoMoneda;
import ar.com.agtech.syros.fecae.implementations.esphora.utils.Util;

/**
 * EsphoraConector.java
 * Manages connection to webservice Esphora and gets AFIP data for electronic invoicing.
 * @deprecated Se implement&oacute; flujo optimizado utilizando {@link EsphoraGateway}<br>
 * EsphoraConector ser&aacute; eliminado en versiones posteriores a la 1.1.0
 * @version 0.1.3
 * @see WebService http://ec2-50-17-85-59.compute-1.amazonaws.com:8080/esphora-conector/ws/wsfev1?wsdl
 *
 */
@Deprecated
public class EsphoraConector implements FECAEConector  { 

	private static final long serialVersionUID = 5753481033197088932L;
	
	private static final Logger log = Logger.getLogger(EsphoraConector.class);
	
	private Wsfev1 serviceProxy;
	
	public void init() throws EsphoraInternalException {
		init(null);
	}
	
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
	 * @see ar.com.agtech.syros.esphora.conector.FECAEConector#generarFESimple(java.lang.Long, ar.com.agtech.syros.esphora.conector.elements.TipoComprobante, java.lang.Integer, ar.com.agtech.syros.esphora.conector.elements.TipoConcepto, ar.com.agtech.syros.esphora.conector.elements.TipoDocumento, long, long, ar.com.agtech.syros.esphora.conector.elements.Importe)
	 */
	public FESimpleResponse generarFESimple(
			Long cuitFacturador, 
			TipoComprobante cbte,
			Integer ptoVta,
			TipoConcepto cpto, 
			TipoDocumento doc,
			long nroDoc, long nroCbte,
			Importe importe
			) throws EsphoraInternalException{
		
		if(ptoVta == null){
			throw new EsphoraInternalException("El punto de venta es nulo");
		}else{
			if(ptoVta<1)
				throw new EsphoraInternalException("El punto de venta debe ser mayor a 0");
		}
		if(nroDoc<1)
			throw new EsphoraInternalException("El numero de documento es invalido");
		if(nroCbte<1)
			throw new EsphoraInternalException("El numero de comprobante es invalido");
		if(cbte==null) 
			throw new EsphoraInternalException("El concepto es nulo");
		if(importe==null)
			throw new EsphoraInternalException("El importe es nulo");
		if(cpto == null)
			throw new EsphoraInternalException("El concepto es nulo");
		if(doc == null) 
			throw new EsphoraInternalException("El Tipo de Documento es nulo");
		
		/*generamos el cuerpo del request*/
		log.debug("Header Generation...");
		FECAECabRequest cabecera = generarCabecera(cbte,ptoVta);
		log.debug("Body Generation...");
		ArrayOfFECAEDetRequest cuerpo = generarCuerpo(importe, cpto, doc, nroDoc, nroCbte,TipoMoneda.PESOS_ARGENTINOS);
		log.debug("Request Generation...");
		FECAERequest req = new FECAERequest();
		req.setFeCabReq(cabecera);
		req.setFeDetReq(cuerpo);
		
		log.debug("Requesting...");
		FECAEResponse resp = null;
		try {
			resp = serviceProxy.fecaeSolicitar(req, cuitFacturador);
		} catch (SOAPFaultException e) {
			log.error("Remote Error Catched...",e);
			EsphoraRemoteException ere = new EsphoraRemoteException("Incorrect SOAP/xml Response",e);
			throw new EsphoraInternalException("SOAP Response could not be parsed",ere);
		}
		return new FESimpleResponse(resp);
	}
	
	/**
	 * Retorna el numero del ultimo comprobante registrado
	 * @param cbte
	 * @param ptoVta
	 * @param cuitFacturador
	 * @return
	 * @throws EsphoraInternalException
	 */
	public FEUltimoResponse getFeCompUltimoAutorizado(
			TipoComprobante cbte, 
			Integer ptoVta,
			Long cuitFacturador
			) throws EsphoraInternalException{
		int comprobante = Util.obtenerParametroTipoComprobante(cbte);
		FERecuperaLastCbteResponse resp = null;
		try {
			resp = serviceProxy.feCompUltimoAutorizado(comprobante, ptoVta, cuitFacturador);		
		} catch (SOAPFaultException e) {
			log.error("Remote Error Catched...",e);
			EsphoraRemoteException ere = new EsphoraRemoteException("Incorrect SOAP/xml Response",e);
			throw new EsphoraInternalException("SOAP Response could not be parsed",ere);
		}
		return new FEUltimoResponse(resp);
	}
	
	
	/* ******************PRIVATE METHODS****************** */

	private static FECAECabRequest generarCabecera(TipoComprobante cbte, int ptoVta) {
		
		FECAECabRequest header = new FECAECabRequest();
		header.setCantReg(1);
		int comprobante = Util.obtenerParametroTipoComprobante(cbte);
		header.setCbteTipo(comprobante);
		header.setPtoVta(ptoVta);
		return header;
	}
	
	private static ArrayOfFECAEDetRequest generarCuerpo(Importe importe, TipoConcepto cpto, TipoDocumento td, long nroDoc, long nroCbte, TipoMoneda tMon) {
		
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
		
		ArrayOfFECAEDetRequest aofdr = new ArrayOfFECAEDetRequest();
		aofdr.getFECAEDetRequest().add(cuerpo);
		return aofdr;
	}
	
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
	
}