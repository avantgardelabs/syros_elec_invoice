/**
 * 
 */
package ar.com.agtech.syros.fecae.elements;

import ar.com.agtech.syros.fecae.elements.types.TipoPersoneria;
import ar.com.agtech.syros.fecae.exceptions.InvalidCUITException;

/**
 * @author Jorge Morando
 * 
 */
public final class Cuit {
	

	private Long dni;

	private Long sexo;

	private Long verificador;
	
	private Long cuit;
	
	public Cuit(Long cuit)throws InvalidCUITException{
		validar(cuit);
	}
	
	private void validar(Long cuit) throws InvalidCUITException {
		try {
			if(cuit.toString().length()!=11) throw new IndexOutOfBoundsException("CUIT must 11 digits");
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
			this.cuit = cuit;
			
			if(!verificador.equals(digito)) throw new InvalidCUITException("Invalid CUIT -> Check digit invalid.");
			
		} catch (Exception e) {
			throw new InvalidCUITException("Invalid CUIT",e);
		}
	}
	
	@Override
	public String toString() {
		return cuit.toString();
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

	public static void main(String[] args) {
		Cuit cuit;
		try {
			cuit = new Cuit(20301524332L);
			System.out.println(cuit);
			System.out.println(cuit.getDni());
			System.out.println((TipoPersoneria.FISICA_FEMENINO.getTipo() == cuit.getSexo()?"FEMENINO":"MASCULINO"));
			System.out.println(cuit.getVerificador());
		} catch (InvalidCUITException e) {
			e.printStackTrace();
		}
	} 
}