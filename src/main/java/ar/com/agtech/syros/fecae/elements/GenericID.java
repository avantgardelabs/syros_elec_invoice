/**
 * 
 */
package ar.com.agtech.syros.fecae.elements;


/**
 * @author Jorge Morando
 * 
 */
public final class GenericID implements Identification {
	

	private Long number;
	
	public GenericID(){
		this.number = 0L;
	}
	
	@Override
	public void validar(){
		if(number==null)
			number = new Long(0);
		else{
			number = new Long(0);
		}
	}
	
	@Override
	public String toString() {
		return number.toString();
	}

	@Override
	public Long getId() {
		return number;
	}
	
	@Override
	public void setId(Long number) {
		this.number = number;
	}
}