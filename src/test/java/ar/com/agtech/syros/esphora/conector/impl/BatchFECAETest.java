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

import ar.com.agtech.syros.fecae.elements.Importe;
import ar.com.agtech.syros.fecae.elements.types.TipoImporte;
import ar.com.agtech.syros.fecae.elements.types.TipoIva;
import ar.com.agtech.syros.fecae.implementations.EsphoraGateway;
import ar.com.agtech.syros.fecae.implementations.FECAEGateway;
import ar.com.agtech.syros.fecae.implementations.behaviors.EsphoraResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.EsphoraError;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraException;
import ar.com.agtech.syros.fecae.implementations.esphora.invoices.FA;
import ar.com.agtech.syros.fecae.implementations.esphora.invoices.FB;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoConcepto;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoDocumento;

/**
 * @author german
 *
 */
public class BatchFECAETest {
	
	private static final long cuitFacturador = 30710370792L;
	private static final long cuitCliente = 30710370799L;
	
	private static final Logger log = Logger.getLogger(BatchFECAETest.class);

	private FECAEGateway gateway;
	
	private TipoIva tipoIva;
	
	private String importeStr;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		gateway = new EsphoraGateway();
		gateway.init();
		tipoIva = TipoIva.VEINTIUNO;
		importeStr = "7117.7043";
	}

	@Test
	public void testGenerarFEA() {
		try {
			log.debug("Generando Factura A iva discriminado");
			
			List<FA> facturasA = new ArrayList<FA>();
			
			Importe importe = new Importe(new BigDecimal(importeStr), tipoIva, TipoImporte.NETO);

			FA fa = new FA(cuitFacturador, 
					TipoDocumento.CUIT, 
					cuitCliente, 
					TipoConcepto.PRODUCTOS, 
					1, 0, 
					importe);
			facturasA.add(fa);
			
			FA fa2 = new FA(cuitFacturador, 
					TipoDocumento.CUIT, 
					cuitCliente, 
					TipoConcepto.PRODUCTOS, 
					1, 0, 
					importe);
			facturasA.add(fa2);
			
			EsphoraResponse response = gateway.authorize(TipoComprobante.FACTURA_A, 1, cuitFacturador, facturasA);
			
			assertNotNull("La respuesta de generarFA es nula", response);
			assertNull("Se obtovieron errores en la llamada al servicio",response.getGlobalErrors());
			logResponse(response);
		} catch (EsphoraException e) {
			log.error("ERROR",e);
			fail(e.getMessage());
		} 
		
	}
	
	/**
	 * Test method for {@link ar.com.agtech.syros.esphora.conector.impl.EsphoraConector#generarFESimple(java.lang.Long, ar.com.agtech.syros.esphora.conector.elements.TipoComprobante, java.lang.Integer, ar.com.agtech.syros.esphora.conector.elements.TipoConcepto, ar.com.agtech.syros.esphora.conector.elements.TipoDocumento, long, long, ar.com.agtech.syros.esphora.conector.elements.Importe)}.
	 */
	@Test
	public void testGenerarFB() {
		try {
			log.debug("Generando Factura B iva discriminado");
			
			List<FB> facturasB = new ArrayList<FB>();
			
			Importe importe = new Importe(Importe.calcularBruto(new BigDecimal(importeStr), tipoIva), tipoIva, TipoImporte.BRUTO);

			FB fa = new FB(cuitFacturador, 
					TipoDocumento.CUIT, 
					cuitCliente, 
					TipoConcepto.PRODUCTOS, 
					1, 0, 
					importe);
			facturasB.add(fa);
			
			FB fa2 = new FB(cuitFacturador, 
					TipoDocumento.CUIT, 
					cuitCliente, 
					TipoConcepto.PRODUCTOS, 
					1, 0, 
					importe);
			facturasB.add(fa2);
			
			EsphoraResponse response = gateway.authorize(TipoComprobante.FACTURA_B, 1, cuitFacturador, facturasB);
			
			assertNotNull("La respuesta de generarFB es nula", response);
			assertNull("Se obtovieron errores en la llamada al servicio getFeCompUltimoAutorizado",response.getGlobalErrors());
			logResponse(response);
		} catch (EsphoraException e) {
			log.error("ERROR",e);
			fail(e.getMessage());
		} 

	}
	
	private void logResponse(EsphoraResponse response){
		log.error("ESTADO: "+response.getEstado());
		log.error("RESULTADO: "+response.getResultado());
		log.error("TOTAL APROVADAS: "+response.getTotalApproved());
		log.error("TOTAL RECHAZADAS: "+response.getTotalRejected());
		List<EsphoraError> errores = response.getGlobalErrors();
		if(errores!=null){
			for (EsphoraError error : errores) {
				log.error(error.getMessage());
			}
		}
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	

}
