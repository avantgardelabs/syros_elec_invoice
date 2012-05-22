/**
 * 
 */
package ar.com.agtech.syros.esphora.conector.elements;

import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_MONEDA_PESOS_ARGENTINOS;

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
