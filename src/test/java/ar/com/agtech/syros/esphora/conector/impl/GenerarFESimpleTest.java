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

import ar.com.agtech.syros.fecae.elements.Importe;
import ar.com.agtech.syros.fecae.elements.types.TipoImporte;
import ar.com.agtech.syros.fecae.elements.types.TipoIva;
import ar.com.agtech.syros.fecae.implementations.EsphoraConector;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.FESimpleResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.FEUltimoResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraException;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoConcepto;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoDocumento;

/**
 * @author german
 *
 */
public class GenerarFESimpleTest {
	
	private static final long cuitFacturador = 30710370792L;
	private static final long cuitCliente = 30710370799L;
	
	private static final Logger log = Logger.getLogger(GenerarFESimpleTest.class);

	private EsphoraConector connector;
	
	private TipoIva tipoIva;
	
	private String importeStr;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		connector = new EsphoraConector();
		connector.init();
		tipoIva = TipoIva.VEINTIUNO;
		importeStr = "7117.7043";
	}

	@Test
	public void testGenerarFESimpleA() {
		try {
			log.debug("Generando Factura A iva discriminado");
			TipoComprobante comprobante = TipoComprobante.FACTURA_A;
			FEUltimoResponse comp = connector.getFeCompUltimoAutorizado(comprobante, 1, cuitFacturador);
			assertNotNull("La respuesta de el ultimo comprobante es nula", comp);
			String[] errors =  comp.getErrores();
			assertNull("Hubo errores en la llamada al servicio getFeCompUltimoAutorizado",errors);
			log.info("El ultimo comprobante genrado fue el numero: "+comp.getCbteNro());
			
			Importe importe = new Importe(new BigDecimal(importeStr), tipoIva, TipoImporte.NETO);
			
			FESimpleResponse response = connector.generarFESimple(
					cuitFacturador, 
					comprobante, 
					1,
					TipoConcepto.PRODUCTOS, 
					TipoDocumento.CUIT,
					cuitCliente, 
					comp.getCbteNro()+1,
					importe);
			
			assertNotNull("La respuesta de generarFESimple es nula", response);
			log.info("ESTADO: "+response.getEstado());
			log.info("CAE: "+response.getCAE());
			String[] errores = response.getErrores();
			assertNull("Se obtovieron errores en la llamada al servicio getFeCompUltimoAutorizado",errores);
			if(errores!=null){
				for (int i = 0; i < errores.length; i++) {
					log.debug(errores[i]);
				}
			}
			
		} catch (EsphoraException e) {
			log.error("ERROR",e);
			fail(e.getMessage());
		} 
		
	}
	
	/**
	 * Test method for {@link ar.com.agtech.syros.esphora.conector.impl.EsphoraConector#generarFESimple(java.lang.Long, ar.com.agtech.syros.esphora.conector.elements.TipoComprobante, java.lang.Integer, ar.com.agtech.syros.esphora.conector.elements.TipoConcepto, ar.com.agtech.syros.esphora.conector.elements.TipoDocumento, long, long, ar.com.agtech.syros.esphora.conector.elements.Importe)}.
	 */
	@Test
	public void testGenerarFESimpleB() {
		try {
			log.debug("Generando Factura B iva NO discriminado");
			TipoComprobante comprobante = TipoComprobante.FACTURA_B; 
			FEUltimoResponse comp = connector.getFeCompUltimoAutorizado(comprobante, 1, cuitFacturador);
			assertNotNull("La respuesta de el ultimo comprobante es nula", comp);
			String[] errors =  comp.getErrores();
			assertNull("Hubo errores en la llamada al servicio getFeCompUltimoAutorizado",errors);
			log.info("El ultimo comprobante genrado fue el numero: "+comp.getCbteNro());
			
			Importe importe = new Importe(Importe.calcularBruto(new BigDecimal(importeStr), tipoIva), tipoIva, TipoImporte.BRUTO);
			
			FESimpleResponse response = connector.generarFESimple(
					cuitFacturador, 
					comprobante, 
					1,
					TipoConcepto.PRODUCTOS, 
					TipoDocumento.CUIT,
					cuitCliente, 
					comp.getCbteNro()+1,
					importe);
			
			assertNotNull("La respuesta de generarFESimple es nula", response);
			log.info("ESTADO: "+response.getEstado());
			log.info("CAE: "+response.getCAE());
			String[] errores = response.getErrores();
			assertNull("Se obtovieron errores en la llamada al servicio getFeCompUltimoAutorizado",errores);
			if(errores!=null){
				for (int i = 0; i < errores.length; i++) {
					log.debug(errores[i]);
				}
			}
		} catch (EsphoraException e) {
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
