package ar.com.agtech.syros.fecae.implementations;

import ar.com.agtech.syros.fecae.implementations.behaviors.Authorize;
import ar.com.agtech.syros.fecae.implementations.behaviors.WebServiceGateway;

/**
 * Gateway WS para la pasarela de facturaci&oacute;n ESPHORA. <br/>
 * Instancia una interfaz de comunicaci&oacute;n via web service SOAP <br/>
 * para la autorizaci&oacute;n de comprobantes fiscales.
 * 
 * @author Jorge Morando
 */
public interface FECAEGateway extends Authorize, WebServiceGateway {
	
}
