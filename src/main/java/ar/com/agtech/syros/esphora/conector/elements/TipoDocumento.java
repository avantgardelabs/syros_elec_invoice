/**
 * 
 */
package ar.com.agtech.syros.esphora.conector.elements;

import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_DOC_DNI;
import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_DOC_CUIL;
import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_DOC_CUIT;

/**
 * @author Jorge Morando
 *
 */
public enum TipoDocumento {
	CUIT(TIPO_DOC_CUIT),
	CUIL(TIPO_DOC_CUIL),
	DNI(TIPO_DOC_DNI);
	
	private int id;
	
	private TipoDocumento(int id) {
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
}
