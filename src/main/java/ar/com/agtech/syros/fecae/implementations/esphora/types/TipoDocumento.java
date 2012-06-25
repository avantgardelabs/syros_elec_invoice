/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.types;

import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_DOC_CUIL;
import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_DOC_CUIT;
import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_DOC_DNI;

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
