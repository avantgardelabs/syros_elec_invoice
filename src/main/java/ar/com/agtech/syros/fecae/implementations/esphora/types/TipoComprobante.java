/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.types;

import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_COMPROBANTE_FACTURA_A;
import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_COMPROBANTE_FACTURA_B;
import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_COMPROBANTE_FACTURA_C;
import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_COMPROBANTE_NOTA_CREDITO_A;
import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_COMPROBANTE_NOTA_CREDITO_B;
import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_COMPROBANTE_NOTA_CREDITO_C;
import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_COMPROBANTE_NOTA_DEBITO_A;
import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_COMPROBANTE_NOTA_DEBITO_B;
import static ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants.TIPO_COMPROBANTE_NOTA_DEBITO_C;
/**
 * @author Jorge Morando
 *
 */
public enum TipoComprobante {
	
	FACTURA_A(TIPO_COMPROBANTE_FACTURA_A),
	FACTURA_B(TIPO_COMPROBANTE_FACTURA_B),
	FACTURA_C(TIPO_COMPROBANTE_FACTURA_C),
	NOTA_CREDITO_A(TIPO_COMPROBANTE_NOTA_CREDITO_A),
	NOTA_CREDITO_B(TIPO_COMPROBANTE_NOTA_CREDITO_B),
	NOTA_CREDITO_C(TIPO_COMPROBANTE_NOTA_CREDITO_C),
	NOTA_DEBITO_A(TIPO_COMPROBANTE_NOTA_DEBITO_A),
	NOTA_DEBITO_B(TIPO_COMPROBANTE_NOTA_DEBITO_B),
	NOTA_DEBITO_C(TIPO_COMPROBANTE_NOTA_DEBITO_C);
	
	private int id;
	
	TipoComprobante(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
}
