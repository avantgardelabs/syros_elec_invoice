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
import ar.com.agtech.syros.fecae.implementations.esphora.invoices.FA;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoConcepto;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoDocumento;

/**
 * @author german
 *
 */
public class TestFA {
	
	private static final Logger log = Logger.getLogger(TestFA.class);
	private static Cuit cuitFacturador;
	private static Cuit cuitCliente;
	private static FECAEGateway gateway;
	private static Dni dniCliente;
	private static TipoIva tipoIva;
	private static String importeStr;
	private static Importe importe;
	private static int sleepTime;

	@BeforeClass
	public static void setUp() throws Exception {
		cuitFacturador = new Cuit(30710370792L);
		cuitCliente = new Cuit(33679836299L);
		dniCliente = new Dni(67983629L);
		gateway = new EsphoraGateway();
		gateway.init();
		tipoIva = TipoIva.VEINTIUNO;
		importeStr = "1117.7043";
		//El objeto importe discrimina IVA automáticamente ya que le indicamos que se le pasa un valor NETO
		importe = new Importe(new Importe.Neto(new BigDecimal(importeStr)) , tipoIva);
		sleepTime = 60;
	}
	
	@Before
	public void printSeparator(){
		System.out.println("-----------------------------------------------------------------------");
	}
	
	@Test
	public void FASimple() {
		try {
			
			log.debug("Generando Factura A iva discriminado");
			
			FA fa = new FA(cuitFacturador, TipoDocumento.CUIT, cuitCliente, TipoConcepto.PRODUCTOS,1 , importe);
			
			FA faResp = gateway.authorize(fa);
			
			log.info("ESTADO: "+faResp.getEstado());
			log.info("RESULTADO: "+faResp.getResultado());
			log.info("CAE: "+faResp.getCae());
			log.info("Vto CAE: "+faResp.getVtoCae());
			List<EsphoraObservacion> errores = faResp.getObservaciones();
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
	
	@Test
	public void FAMasiva() {
		try {
			log.debug("Sleeping for "+sleepTime+" secs due to ESPHORA reconnection problem");
			int sleepMillis = sleepTime * 1000;
			Thread.sleep(sleepMillis);
			log.debug("Generando Lote Facturas A iva discriminado");
			
			List<FA> facturasA = new ArrayList<FA>();
			
			FA fa = new FA(TipoDocumento.CUIT, cuitCliente, TipoConcepto.PRODUCTOS, importe);
			facturasA.add(fa);
			FA fa2 = new FA(TipoDocumento.DNI, dniCliente, TipoConcepto.PRODUCTOS, importe);
			facturasA.add(fa2);
			FA fa3 = new FA(TipoDocumento.OTRO, null, TipoConcepto.PRODUCTOS, importe);
			facturasA.add(fa3);
			
			EsphoraResponse response = gateway.authorize(TipoComprobante.FACTURA_A, 1, cuitFacturador, facturasA, 0);
			
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
		} catch (InterruptedException e) {
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
