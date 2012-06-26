/**
 * 
 */
package ar.com.agtech.syros.esphora.conector.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.com.agtech.syros.fecae.elements.Cuit;
import ar.com.agtech.syros.fecae.elements.Importe;
import ar.com.agtech.syros.fecae.elements.types.TipoImporte;
import ar.com.agtech.syros.fecae.elements.types.TipoIva;
import ar.com.agtech.syros.fecae.exceptions.FECAEException;
import ar.com.agtech.syros.fecae.implementations.EsphoraGateway;
import ar.com.agtech.syros.fecae.implementations.FECAEGateway;
import ar.com.agtech.syros.fecae.implementations.behaviors.EsphoraResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.EsphoraError;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.EsphoraObservacion;
import ar.com.agtech.syros.fecae.implementations.esphora.invoices.ComprobanteFiscal;
import ar.com.agtech.syros.fecae.implementations.esphora.invoices.FA;
import ar.com.agtech.syros.fecae.implementations.esphora.invoices.FB;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoConcepto;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoDocumento;

/**
 * @author german
 *
 */
public class TestFB {
	
	private static Cuit cuitFacturador;
	private static Cuit cuitCliente;
	
	private static final Logger log = Logger.getLogger(TestFB.class);

	private static FECAEGateway gateway;
	
	private static TipoIva tipoIva;
	
	private static String importeStr;
	private static Importe importe;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cuitFacturador = new Cuit(30710370792L);
		cuitCliente = new Cuit(33679836299L);
		gateway = new EsphoraGateway();
		gateway.init();
		tipoIva = TipoIva.VEINTIUNO;
		importeStr = "7117.7043";
		//El objeto importe discrimina IVA automáticamente ya que le indicamos que se le pasa un valor NETO
		importe = new Importe(Importe.calcularBruto(new BigDecimal(importeStr), tipoIva) , tipoIva, TipoImporte.BRUTO);
	}
	
	@Test
	public void FBSimple() {
		try {
			
			log.debug("Generando Factura B iva no discriminado");
			
			FB fb = new FB(cuitFacturador, TipoDocumento.OTRO, null, TipoConcepto.PRODUCTOS,1 , importe);
			
			FB fbResp = gateway.authorize(fb);
			
			log.info("RESULTADO: "+fbResp.getResultado());
			log.info("ESTADO: "+fbResp.getEstado());
			log.info("CAE: "+fbResp.getCae());
			log.info("Vto CAE: "+fbResp.getVtoCae());
			List<EsphoraObservacion> errores = fbResp.getObservaciones();
			if(errores!=null){
				for (EsphoraObservacion error : errores) {
					log.error(error.getMessage());
				}
			}
		} catch (FECAEException e) {
			log.error("ERROR",e);
			fail(e.getMessage());
		} 
	}
	
//	@Test
//	public void FAMasiva() {
//		try {
////			log.debug("Sleeping for 45 secs due to ESPHORA reconnection problem");
////			Thread.sleep(45000);
//			log.debug("Generando Lote Facturas A iva discriminado");
//			
//			List<FB> facturasB = new ArrayList<FB>();
//			
//			FB fb = new FB(TipoDocumento.CUIT, cuitCliente, TipoConcepto.PRODUCTOS, importe);
//			facturasB.add(fb);
//			FB fb2 = new FB(TipoDocumento.CUIT, cuitCliente, TipoConcepto.PRODUCTOS, importe);
//			facturasB.add(fb2);
//			FB fb3 = new FB(TipoDocumento.CUIT, cuitCliente, TipoConcepto.PRODUCTOS, importe);
//			facturasB.add(fb3);
//			
//			EsphoraResponse response = gateway.authorize(TipoComprobante.FACTURA_B, 1, cuitFacturador, facturasB);
//			
//			log.info("ESTADO: "+response.getEstado());
//			log.info("RESULTADO: "+response.getResultado());
//			
//			log.info("TOTAL APROVADAS: "+response.getTotalApproved());
//			for (ComprobanteFiscal cfAccepted : response.getAllApproved()) {
//				log.info("CAE:"+cfAccepted.getCae()+" - "+cfAccepted.getVtoCae());
//			}
//			
//			log.info("TOTAL RECHAZADAS: "+response.getTotalRejected());
//			for (ComprobanteFiscal cfRejected : response.getAllRejected()) {
//				List<EsphoraObservacion> obs = cfRejected.getObservaciones();
//				if(obs!=null){
//					for (EsphoraObservacion o : obs) {
//						log.error(o.getMessage());
//					}
//				}
//			}
//			
//			List<EsphoraError> errores = response.getGlobalErrors();
//			if(errores!=null){
//				for (EsphoraError error : errores) {
//					log.error(error.getMessage());
//				}
//			}
//			
//			assertNotNull("La respuesta de es nula", response);
//			assertNull("Se obtovieron errores en la llamada al servicio",response.getGlobalErrors());
//		} catch (FECAEException e) {
//			log.error("ERROR",e);
//			fail(e.getMessage());
//		} catch (InterruptedException e) {
//			log.error("ERROR",e);
//			fail(e.getMessage());
//		} 
//	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	

}
