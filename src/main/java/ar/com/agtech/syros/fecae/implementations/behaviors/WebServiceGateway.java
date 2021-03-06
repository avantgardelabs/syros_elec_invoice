package ar.com.agtech.syros.fecae.implementations.behaviors;

import java.io.Serializable;

import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraInternalException;

public interface WebServiceGateway extends Serializable {
	
	/**
	 * Establece la conexi&oacute;n con el ws de Esphora
	 * @throws EsphoraInternalException
	 */
	void init() throws EsphoraInternalException;
	
	/**
	 * Establece la conexi&oacute;n con el ws de Esphora teniendo en cuenta el wsdl aportado
	 * @param wsdlLocation el url del wsdl que al que se quiere apuntar
	 * @throws EsphoraInternalException
	 */
	void init(String wsdlLocation) throws EsphoraInternalException;
}
