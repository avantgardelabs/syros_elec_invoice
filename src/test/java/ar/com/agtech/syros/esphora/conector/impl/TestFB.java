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
import org.junit.BeforeClass;
import org.junit.Test;

import ar.com.agtech.syros.fecae.elements.Cuit;
import ar.com.agtech.syros.fecae.elements.Dni;
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
import ar.com.agtech.syros.fecae.implementations.esphora.invoices.FB;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoConcepto;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoDocumento;

/**
 * @author german
 *
 */
public class TestFB {
	
	private static final Logger log = Logger.getLogger(TestFB.class);
	private static Cuit cuitFacturador;
	private static Cuit cuitCliente;
	private static Dni dniCliente;
	private static FECAEGateway gateway;
	private static TipoIva tipoIva;
	private static String importeStr;
	private static Importe importe;
	private static int sleepTime;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		cuitFacturador = new Cuit(30525902672L);
		cuitCliente = new Cuit(33679836299L);
		dniCliente = new Dni(16933413L);
		gateway = new EsphoraGateway();
		tipoIva = TipoIva.CERO;
		importeStr = "640";
		//El objeto importe discrimina IVA automáticamente ya que le indicamos que se le pasa un valor NETO
		importe = new Importe(new Importe.Bruto(new BigDecimal(importeStr)) , tipoIva);
		sleepTime = 60;
	}
	
	@Before
	public void printSeparator(){
		System.out.println("-----------------------------------------------------------------------");
	}
	
//	@Test
//	public void FBSimple() {
//		try {
//			
//			log.debug("Generando Factura B iva no discriminado");
//			
//			FB fb = new FB(cuitFacturador, TipoDocumento.OTRO, null, TipoConcepto.PRODUCTOS,1 , importe);
//			
//			FB fbResp = gateway.authorize(fb);
//			
//			log.info("RESULTADO: "+fbResp.getResultado());
//			log.info("ESTADO: "+fbResp.getEstado());
//			log.info("CAE: "+fbResp.getCae());
//			log.info("Vto CAE: "+fbResp.getVtoCae());
//			List<EsphoraObservacion> errores = fbResp.getObservaciones();
//			if(errores!=null){
//				for (EsphoraObservacion error : errores) {
//					log.error(error.getMessage());
//				}
//			}
//		} catch (FECAEException e) {
//			log.error("ERROR",e);
//			fail(e.getMessage());
//		} 
//	}
	
	@Test
	public void FBMasiva() {
		try {
			gateway.init();
//			log.debug("Sleeping for "+sleepTime+" secs due to ESPHORA reconnection problem");
//			int sleepMillis = sleepTime * 1000;
//			Thread.sleep(sleepMillis);
			log.debug("Generando Lote Facturas B iva no discriminado");
			
			List<FB> facturasB = new ArrayList<FB>();
			
			FB fb = new FB(TipoDocumento.DNI, dniCliente, TipoConcepto.SERVICIOS, importe);
			facturasB.add(fb);
			log.debug("APAGAR");
			Thread.sleep(10000);
			EsphoraResponse response = gateway.authorize(TipoComprobante.FACTURA_B, 15, cuitFacturador, facturasB);
			
			log.info("ESTADO: "+response.getEstado());
			log.info("RESULTADO: "+response.getResultado());
			
			log.info("TOTAL APROVADAS: "+response.getTotalApproved());
			for (ComprobanteFiscal cfAccepted : response.getAllApproved()) {
				log.info("CAE:"+cfAccepted.getCae()+" - "+cfAccepted.getVtoCae());
			}
			
			log.info("TOTAL RECHAZADAS: "+response.getTotalRejected());
			for (ComprobanteFiscal cfRejected : response.getAllRejected()) {
				List<EsphoraObservacion> obs = cfRejected.getObservaciones();
				if(obs!=null){
					for (EsphoraObservacion o : obs) {
						log.error(o.getMessage());
					}
				}
			}
			
			List<EsphoraError> errores = response.getGlobalErrors();
			if(errores!=null){
				for (EsphoraError error : errores) {
					log.error(error.getMessage());
				}
			}
			
			assertNotNull("La respuesta de es nula", response);
			assertNull("Se obtovieron errores en la llamada al servicio",errores);
		} catch (FECAEException e) {
			log.error("ERROR",e);
			fail(e.getMessage());
		} catch (Exception e) {
			log.error("ERROR",e);
			fail(e.getMessage());
		} 
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	

}
