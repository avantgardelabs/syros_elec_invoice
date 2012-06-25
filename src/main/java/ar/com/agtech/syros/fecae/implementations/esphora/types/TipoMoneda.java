/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.types;

import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_MONEDA_PESOS_ARGENTINOS;

/**
 * @author Jorge Morando
 *
 */
public enum TipoMoneda {
	PESOS_ARGENTINOS(TIPO_MONEDA_PESOS_ARGENTINOS);
	
	private String id;
	
	TipoMoneda(String id){
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}
}
