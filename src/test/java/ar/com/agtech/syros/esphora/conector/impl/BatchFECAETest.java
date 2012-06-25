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
import ar.com.agtech.syros.fecae.implementations.EsphoraGateway;
import ar.com.agtech.syros.fecae.implementations.FECAEGateway;
import ar.com.agtech.syros.fecae.implementations.behaviors.EsphoraResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.EsphoraError;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraException;
import ar.com.agtech.syros.fecae.implementations.esphora.invoices.FA;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoConcepto;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoDocumento;

/**
 * @author german
 *
 */
public class BatchFECAETest {
	
	private static Cuit cuitFacturador;
	private static Cuit cuitCliente;
	
	private static final Logger log = Logger.getLogger(BatchFECAETest.class);

	private static FECAEGateway gateway;
	
	private static TipoIva tipoIva;
	
	private static String importeStr;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	@SuppressWarnings("static-access")
	public void setUp() throws Exception {
		cuitFacturador = new Cuit(30710370792L);
		cuitCliente = new Cuit(33679836299L);
		gateway = new EsphoraGateway();
		gateway.init();
		tipoIva = TipoIva.VEINTIUNO;
		importeStr = "7117.7043";
		log.info("Sleeping for 5 sec");
		Thread.currentThread().sleep(1000);
		log.info("Sleeping for 4 sec");
		Thread.currentThread().sleep(1000);
		log.info("Sleeping for 3 sec");
		Thread.currentThread().sleep(1000);
		log.info("Sleeping for 2 sec");
		Thread.currentThread().sleep(1000);
		log.info("Sleeping for 1 sec");
		Thread.currentThread().sleep(1000);
		log.info("starting test");
	}
	

	@Test
	public void testGenerarFEA() {
		try {
			log.debug("Generando Factura A iva discriminado");
			
			List<FA> facturasA = new ArrayList<FA>();
			
			Importe importe = new Importe(new BigDecimal(importeStr), tipoIva, TipoImporte.NETO);
			
			FA fa = new FA(TipoDocumento.CUIT, cuitCliente, TipoConcepto.PRODUCTOS, importe);
			facturasA.add(fa);
			FA fa2 = new FA(TipoDocumento.CUIT, cuitCliente, TipoConcepto.PRODUCTOS, importe);
			facturasA.add(fa2);
			FA fa3 = new FA();
			
			
			EsphoraResponse response = gateway.authorize(TipoComprobante.FACTURA_A, 1, cuitFacturador, facturasA);
			
			log.info("ESTADO: "+response.getEstado());
			log.info("RESULTADO: "+response.getResultado());
			log.info("TOTAL APROVADAS: "+response.getTotalApproved());
			log.info("TOTAL RECHAZADAS: "+response.getTotalRejected());
			List<EsphoraError> errores = response.getGlobalErrors();
			if(errores!=null){
				for (EsphoraError error : errores) {
					log.error(error.getMessage());
				}
			}
			
			assertNotNull("La respuesta de es nula", response);
			assertNull("Se obtovieron errores en la llamada al servicio",response.getGlobalErrors());
		} catch (EsphoraException e) {
			log.error("ERROR",e);
			fail(e.getMessage());
		} 
		
	}
	
	/**
	 * Test method for {@link ar.com.agtech.syros.esphora.conector.impl.EsphoraConector#generarFESimple(java.lang.Long, ar.com.agtech.syros.esphora.conector.elements.TipoComprobante, java.lang.Integer, ar.com.agtech.syros.esphora.conector.elements.TipoConcepto, ar.com.agtech.syros.esphora.conector.elements.TipoDocumento, long, long, ar.com.agtech.syros.esphora.conector.elements.Importe)}.
	 */
//	@Test
//	public void testGenerarFB() {
//		try {
//			log.debug("Generando Factura B iva discriminado");
//			
//			List<FB> facturasB = new ArrayList<FB>();
//			
//			Importe importe = new Importe(Importe.calcularBruto(new BigDecimal(importeStr), tipoIva), tipoIva, TipoImporte.BRUTO);
//
//			FB fa = new FB(cuitFacturador, 
//					TipoDocumento.CUIT, 
//					cuitCliente, 
//					TipoConcepto.PRODUCTOS, 
//					1, 0, 
//					importe);
//			facturasB.add(fa);
//			
//			FB fa2 = new FB(cuitFacturador, 
//					TipoDocumento.CUIT, 
//					cuitCliente, 
//					TipoConcepto.PRODUCTOS, 
//					1, 0, 
//					importe);
//			facturasB.add(fa2);
//			
//			EsphoraResponse response = gateway.authorize(TipoComprobante.FACTURA_B, 1, cuitFacturador, facturasB);
//			
//			assertNotNull("La respuesta de es nula", response);
//			assertNull("Se obtovieron errores en la llamada al servicio",response.getGlobalErrors());
//			logResponse(response);
//		} catch (EsphoraException e) {
//			log.error("ERROR",e);
//			fail(e.getMessage());
//		} 
//
//	}
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	

}
