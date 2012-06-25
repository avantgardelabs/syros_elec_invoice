/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.types;

import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_CONCEPTO_PRODUCTOS;
import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_CONCEPTO_PRODUCTOS_SERVICIOS;
import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_CONCEPTO_SERVICIOS;

/**
 * @author Jorge Morando
 *
 */
public enum TipoConcepto {
	PRODUCTOS(TIPO_CONCEPTO_PRODUCTOS),
	SERVICIOS(TIPO_CONCEPTO_SERVICIOS),
	PRODUCTOS_Y_SERVICIOS(TIPO_CONCEPTO_PRODUCTOS_SERVICIOS);
	
	private int id;
	
	TipoConcepto(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
}
