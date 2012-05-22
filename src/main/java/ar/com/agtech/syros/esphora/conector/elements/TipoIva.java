/**
 * 
 */
package ar.com.agtech.syros.esphora.conector.elements;

import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_IVA_CERO;
import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_IVA_DIEZ_COMA_CINCO;
import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_IVA_VEINTIUNO;
import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_IVA_VEINTISIETE;

/**
 * @author Jorge Morando
 *
 */
public enum TipoIva {
	CERO(TIPO_IVA_CERO),
	DIEZ_COMA_CINCO(TIPO_IVA_DIEZ_COMA_CINCO),
	VEINTIUNO(TIPO_IVA_VEINTIUNO),
	VINTISIETE(TIPO_IVA_VEINTISIETE);
	
	private int id;
	
	TipoIva(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
}
