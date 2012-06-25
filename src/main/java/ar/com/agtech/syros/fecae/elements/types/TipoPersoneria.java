/**
 * 
 */
package ar.com.agtech.syros.fecae.elements.types;


/**
 * @author Jorge Morando
 *
 */
public enum TipoPersoneria {
	
	FISICA_MASCULINO(20L),
	FISICA_FEMENINO(27L),
	JURIDICA_SOCIEDAD(30L);

	private long tipo;
	
	TipoPersoneria(long tipo){
		this.tipo = tipo;
	}
	
	public long getTipo(){
		return tipo;
	}
}
