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
public final class Cuit implements Identification {

	private Long dni;

	private Long sexo;

	private Long verificador;
	
	private Long cuit;
	
	public Cuit(Long cuit) throws InvalidIDException{
		this.cuit = cuit;
		validar();
	}
	
	public void validar() throws InvalidIDException {
		try {
			if(cuit==null) throw new NullPointerException("Null CUIT given");
			if(cuit.toString().length()!=11) throw new IndexOutOfBoundsException("Invalid CUIT length:"+cuit.toString().length()+"must 11 digits");
			sexo = cuit / (long) Math.pow(10, 9);
			dni = cuit / 10 - sexo * (long) Math.pow(10, 8);
			verificador = cuit % 10;
			
			String serie = "2345672345";
			String numero = String.valueOf(sexo) + String.valueOf(dni);
			long suma = 0;
			for (int i = 0; i < 10; i++) {
				suma += (numero.charAt(i)- '0') * (serie.charAt(9 - i) - '0');
			}
			Long digito = 11 - suma % 11;
			digito = digito.equals(11) ? 0 : digito;
			digito = digito.equals(10) ? 9 : digito;
			
			if(!verificador.equals(digito)) throw new InvalidIDException("Invalid CUIT Check digit");
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidIDException("Invalid CUIT",e);
		} catch (NullPointerException e) {
			throw new InvalidIDException("Invalid CUIT",e);
		} catch (Exception e) {
			EsphoraUnhandledException eue = new EsphoraUnhandledException("Unahndled Exception Catched",e);
			throw new InvalidIDException("Invalid CUIT",eue);
		}
	}
	
	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public Long getSexo() {
		return sexo;
	}

	public void setSexo(Long sexo) {
		this.sexo = sexo;
	}

	public Long getVerificador() {
		return verificador;
	}

	public void setVerificador(Long verificador) {
		this.verificador = verificador;
	}
	
	@Override
	public String toString() {
		return cuit.toString();
	}
	
	@Override
	public void setId(Long cuit) throws InvalidIDException {
		this.cuit = cuit;
		validar();
	}
	
	@Override
	public Long getId() {
		return this.cuit;
	}

}