/**
 * 
 */
package ar.com.agtech.syros.esphora.conector.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.com.agtech.syros.esphora.conector.FECAEConector;
import ar.com.agtech.syros.esphora.conector.elements.FESimpleResponse;
import ar.com.agtech.syros.esphora.conector.elements.FEUltimoResponse;
import ar.com.agtech.syros.esphora.conector.elements.Importe;
import ar.com.agtech.syros.esphora.conector.elements.TipoComprobante;
import ar.com.agtech.syros.esphora.conector.elements.TipoConcepto;
import ar.com.agtech.syros.esphora.conector.elements.TipoDocumento;
import ar.com.agtech.syros.esphora.conector.elements.TipoIva;

/**
 * @author german
 *
 */
public class EsphoraConectorTest {
	
	private static final long cuitFacturador = 33679836299L;
	
	private static final Logger log = Logger.getLogger(EsphoraConectorTest.class);

	private FECAEConector connector;
	
	private TipoComprobante comprobante;
	
	private TipoIva tipoIva;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		connector = new EsphoraConector();
		connector.init();
		comprobante = TipoComprobante.FACTURA_B;
		tipoIva = TipoIva.VEINTIUNO;
	}

	/**
	 * Test method for {@link ar.com.agtech.syros.esphora.conector.impl.EsphoraConector#generarFESimple(java.lang.Long, ar.com.agtech.syros.esphora.conector.elements.TipoComprobante, java.lang.Integer, ar.com.agtech.syros.esphora.conector.elements.TipoConcepto, ar.com.agtech.syros.esphora.conector.elements.TipoDocumento, long, long, ar.com.agtech.syros.esphora.conector.elements.Importe)}.
	 */
	@Test
	public void testGenerarFESimple() {
		try {
			FEUltimoResponse comp = connector.getFeCompUltimoAutorizado(comprobante, 1, cuitFacturador);
			assertNotNull("La respuesta de el ultimo comprobante es nula", comp);
			String[] errors =  comp.getErrores();
			assertNull("Hubo errores en la llamada al servicio getFeCompUltimoAutorizado",errors);
			log.info("El ultimo comprobante genrado fue el numero: "+comp.getCbteNro());
			
			Importe importe = new Importe(Importe.calcularBruto(new BigDecimal("100"), tipoIva), tipoIva,false);
			
			FESimpleResponse response = connector.generarFESimple(
					cuitFacturador, 
					comprobante, 
					1,
					TipoConcepto.PRODUCTOS, 
					TipoDocumento.CUIT,
					30710370792L, 
					comp.getCbteNro()+1,
					importe);
			
			assertNotNull("La respuesta de generarFESimple es nula", response);
			String[] errores = response.getErrores();
			assertNull("Hubo errores en la llamada al servicio getFeCompUltimoAutorizado",errores);
			
			log.info("ESTADO: "+response.getEstado());
			log.info("CAE: "+response.getCAE());
			
			
			
		} catch (Exception e) {
			log.error(e);
			fail(e.getMessage());
		}

	}
	
	@Test
	public void testGenerarFESimpleA() {
		try {
			comprobante = TipoComprobante.FACTURA_A;
			FEUltimoResponse comp = connector.getFeCompUltimoAutorizado(comprobante, 1, cuitFacturador);
			assertNotNull("La respuesta de el ultimo comprobante es nula", comp);
			String[] errors =  comp.getErrores();
			assertNull("Hubo errores en la llamada al servicio getFeCompUltimoAutorizado",errors);
			log.info("El ultimo comprobante genrado fue el numero: "+comp.getCbteNro());
			
			Importe importe = new Importe(new BigDecimal("100"), tipoIva,true);
			
			FESimpleResponse response = connector.generarFESimple(
					cuitFacturador, 
					comprobante, 
					1,
					TipoConcepto.PRODUCTOS, 
					TipoDocumento.CUIT,
					30710370792L, 
					comp.getCbteNro()+1,
					importe);
			
			assertNotNull("La respuesta de generarFESimple es nula", response);
			String[] errores = response.getErrores();
			assertNull("Hubo errores en la llamada al servicio getFeCompUltimoAutorizado",errores);
			
			log.info("ESTADO: "+response.getEstado());
			log.info("CAE: "+response.getCAE());
			
			
			
		} catch (Exception e) {
			log.error(e);
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
