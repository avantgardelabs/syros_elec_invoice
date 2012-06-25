/**
 * 
 */
package ar.com.agtech.syros.fecae.elements.types;


/**
 * @author Jorge Morando
 *
 */
public enum TipoImporte {
	
	
	NETO(true),
	BRUTO(false);
	
	private boolean flag;
	
	TipoImporte(boolean flag){
		this.flag = flag;
	}
	
	public boolean isNeto(){
		return flag;
	}
}
