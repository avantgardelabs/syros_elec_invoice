/**
 * 
 */
package ar.com.agtech.syros.fecae.elements;

import ar.com.agtech.syros.fecae.exceptions.InvalidIDException;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraUnhandledException;

/**
 * @author Jorge Morando
 * 
 */
public final class Dni implements Identification {
	

	private Long dni;
	
	public Dni(Long dni) throws InvalidIDException{
		this.dni = dni;
		validar();
	}
	
	public void validar() throws InvalidIDException {
		try {
			if(dni==null)throw new InvalidIDException("Null DNI given");
			String dniStr = dni.toString().trim();
			if(dniStr.length()<6 || dniStr.length()>8) throw new IndexOutOfBoundsException("Invalid DNI length:"+dniStr.length()+", must contain 6 to 8 digits");
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidIDException("Invalid DNI",e);
		} catch (NullPointerException e) {
			throw new InvalidIDException("Invalid CUIT",e);
		} catch (Exception e) {
			EsphoraUnhandledException eue = new EsphoraUnhandledException("Unahndled Exception Catched",e);
			throw new InvalidIDException("Invalid CUIT",eue);
		}
	}
	
	@Override
	public String toString() {
		return dni.toString();
	}

	@Override
	public Long getId() {
		return dni;
	}
	
	@Override
	public void setId(Long dni) throws InvalidIDException {
		this.dni = dni;
		validar();
	}
}