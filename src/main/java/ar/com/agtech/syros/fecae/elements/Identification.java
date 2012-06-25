/**
 * 
 */
package ar.com.agtech.syros.fecae.elements;

import ar.com.agtech.syros.fecae.exceptions.InvalidIDException;

/**
 * @author Jorge Morando
 * 
 */
public interface Identification  {
	
	public void validar() throws InvalidIDException;

	public Long getId();

	public void setId(Long id) throws InvalidIDException;
	
	public String toString();
	
}