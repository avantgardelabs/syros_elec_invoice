/**
 * 
 */
package ar.com.agtech.syros.esphora.conector.elements;

import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_COMPROBANTE_FACTURA_A;
import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_COMPROBANTE_FACTURA_B;
/**
 * @author Jorge Morando
 *
 */
public enum TipoComprobante {
	
	FACTURA_A(TIPO_COMPROBANTE_FACTURA_A),
	FACTURA_B(TIPO_COMPROBANTE_FACTURA_B);
	
	private int id;
	
	TipoComprobante(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
}
