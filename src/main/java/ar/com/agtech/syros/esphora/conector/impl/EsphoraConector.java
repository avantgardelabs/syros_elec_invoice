package ar.com.agtech.syros.esphora.conector.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.jws.WebService;
import javax.xml.ws.WebServiceException;

import org.apache.log4j.Logger;

import ar.com.agtech.syros.esphora.conector.FECAEConector;
import ar.com.agtech.syros.esphora.conector.artifacts.AlicIva;
import ar.com.agtech.syros.esphora.conector.artifacts.ArrayOfAlicIva;
import ar.com.agtech.syros.esphora.conector.artifacts.ArrayOfFECAEDetRequest;
import ar.com.agtech.syros.esphora.conector.artifacts.FECAECabRequest;
import ar.com.agtech.syros.esphora.conector.artifacts.FECAEDetRequest;
import ar.com.agtech.syros.esphora.conector.artifacts.FECAERequest;
import ar.com.agtech.syros.esphora.conector.artifacts.FECAEResponse;
import ar.com.agtech.syros.esphora.conector.artifacts.FERecuperaLastCbteResponse;
import ar.com.agtech.syros.esphora.conector.elements.FESimpleResponse;
import ar.com.agtech.syros.esphora.conector.elements.FEUltimoResponse;
import ar.com.agtech.syros.esphora.conector.elements.Importe;
import ar.com.agtech.syros.esphora.conector.elements.TipoComprobante;
import ar.com.agtech.syros.esphora.conector.elements.TipoConcepto;
import ar.com.agtech.syros.esphora.conector.elements.TipoDocumento;
import ar.com.agtech.syros.esphora.conector.elements.TipoMoneda;
import ar.com.agtech.syros.esphora.conector.exceptions.EsphoraConnectionException;
import ar.com.agtech.syros.esphora.conector.exceptions.EsphoraException;
import ar.com.agtech.syros.esphora.conector.services.Wsfev1;
import ar.com.agtech.syros.esphora.conector.services.Wsfev1Service;
import ar.com.agtech.syros.esphora.utils.Util;

/**
 * EsphoraConector.java
 * Manages connection to webservice Esphora and gets  AFIP data for electronic invoicing.
 * @version 1.0
 * @see WebService http://ec2-50-17-85-59.compute-1.amazonaws.com:8080/esphora-conector/ws/wsfev1?wsdl
 *
 */
public class EsphoraConector implements FECAEConector  { 

	private static final long serialVersionUID = 5753481033197088932L;
	
	private static final Logger log = Logger.getLogger(EsphoraConector.class);
	
	private Wsfev1 serviceProxy;
	
	public void init() throws EsphoraConnectionException {
		init(null);
	}
	
	public void init(String wsdlLoc) throws EsphoraConnectionException {
		try {
			String urlLocation = wsdlLoc;
			Wsfev1Service service=null;
			URL wsdlLocation=null;
			if (null != urlLocation && !urlLocation.isEmpty()){
				wsdlLocation = this.getWsdlLocation(urlLocation);				
			} else {
				wsdlLocation = Thread.currentThread().getContextClassLoader().getResource("wsfev1.wsdl");				
			}
			service = new Wsfev1Service(wsdlLocation);
			serviceProxy = service.getWsfev1Port();
			log.info("WS Interface stablished...");
		} catch (WebServiceException e) {
			log.error("Error retreiving WS Port",e);
			throw new EsphoraConnectionException("No se pudo establecer el puente con los WS de Esphora");
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
			) throws EsphoraException{
		/*generamos el cuerpo del request*/
		log.debug("Header Generation...");
		FECAECabRequest cabecera = generarCabecera(cbte,ptoVta);
		if(cabecera==null) throw new EsphoraException("Datos de la cabecera Faltantes");
		log.debug("Body Generation...");
		ArrayOfFECAEDetRequest cuerpo = generarCuerpo(importe, cpto, doc, nroDoc, nroCbte,TipoMoneda.PESOS_ARGENTINOS);
		if (cuerpo == null) throw new EsphoraException("Datos del cuerpo de Factura Faltante");
		log.debug("Request Generation...");
		FECAERequest req = new FECAERequest();
		req.setFeCabReq(cabecera);
		req.setFeDetReq(cuerpo);
		
		log.debug("Requesting...");
		FECAEResponse resp = serviceProxy.fecaeSolicitar(req, cuitFacturador);
		
		return new FESimpleResponse(resp);
	}
	
	public FEUltimoResponse getFeCompUltimoAutorizado(
			TipoComprobante cbte, 
			Integer ptoVta,
			Long cuitFacturador
			){
		int comprobante = Util.obtenerParametroTipoComprobante(cbte);
		FERecuperaLastCbteResponse resp = serviceProxy.feCompUltimoAutorizado(comprobante, ptoVta, cuitFacturador);		
		return new FEUltimoResponse(resp);
	}
	
	
	private static FECAECabRequest generarCabecera(TipoComprobante cbte, int ptoVta){
		if(cbte==null) return null;
		FECAECabRequest header = new FECAECabRequest();
		header.setCantReg(1);
		int comprobante = Util.obtenerParametroTipoComprobante(cbte);
		header.setCbteTipo(comprobante);
		header.setPtoVta(ptoVta);
		return header;
	}
	
	private static ArrayOfFECAEDetRequest generarCuerpo(Importe importe, TipoConcepto cpto, TipoDocumento td, long nroDoc, long nroCbte, TipoMoneda tMon){
		
		if(importe==null || cpto == null || td == null || tMon == null)
			return null;
		
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
		iva.setId(importe.getTipoIva().getId());
		iva.setImporte(importe.getIva().doubleValue());
		objIva.getAlicIva().add(iva);
		cuerpo.setIva(objIva);
		cuerpo.setImpIVA(importe.getIva().doubleValue());
		
		cuerpo.setImpTotal(importe.getTotal().doubleValue());
		
		ArrayOfFECAEDetRequest aofdr = new ArrayOfFECAEDetRequest();
		aofdr.getFECAEDetRequest().add(cuerpo);
		return aofdr;
	}
	
	private URL getWsdlLocation(String location){
		URL url = null;
		String urlWsdl = location;
        try {        	
            url = new URL(urlWsdl);
        } catch (MalformedURLException e) {
        	log.error( "Can not initialize the wsdl from: "+ urlWsdl);            
        }
        return url;		
	}
	
}