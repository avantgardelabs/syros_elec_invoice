/**
 * 
 */
package ar.com.agtech.syros.esphora.conector.elements;

import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_COMPROBANTE_FACTURA_A;
import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_COMPROBANTE_FACTURA_B;
import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_COMPROBANTE_NOTA_CREDITO_A;
import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_COMPROBANTE_NOTA_CREDITO_B;
import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_COMPROBANTE_NOTA_DEBITO_A;
import static ar.com.agtech.syros.esphora.conector.elements.Constantes.TIPO_COMPROBANTE_NOTA_DEBITO_B;
/**
 * @author Jorge Morando
 *
 */
public enum TipoComprobante {
	
	FACTURA_A(TIPO_COMPROBANTE_FACTURA_A),
	FACTURA_B(TIPO_COMPROBANTE_FACTURA_B),
	NOTA_CREDITO_A(TIPO_COMPROBANTE_NOTA_CREDITO_A),
	NOTA_CREDITO_B(TIPO_COMPROBANTE_NOTA_CREDITO_B),
	NOTA_DEBITO_A(TIPO_COMPROBANTE_NOTA_DEBITO_A),
	NOTA_DEBITO_B(TIPO_COMPROBANTE_NOTA_DEBITO_B);
	
	private int id;
	
	TipoComprobante(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
}
