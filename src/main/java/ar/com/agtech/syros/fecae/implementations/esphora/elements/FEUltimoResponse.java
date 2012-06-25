/**
 * 
 */
package ar.com.agtech.syros.fecae.implementations.esphora.elements;

import java.util.ArrayList;
import java.util.List;

import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.Err;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FERecuperaLastCbteResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraInternalException;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraRemoteException;

public class FEUltimoResponse {

	private int cbteNro;
	private int cbteTipo;
	private int ptoVta;
	private String[] errores;
	
	public int getCbteNro() {
		return cbteNro;
	}


	public int getCbteTipo() {
		return cbteTipo;
	}


	public int getPtoVta() {
		return ptoVta;
	}


	public FEUltimoResponse(FERecuperaLastCbteResponse rawResp) throws EsphoraInternalException{
		List<Err> errors = new ArrayList<Err>();
		this.cbteNro = rawResp.getCbteNro();
		this.cbteTipo = rawResp.getCbteTipo();
		this.ptoVta = rawResp.getPtoVta();
		
		if(cbteNro == 0 && rawResp.getErrors() == null){
			EsphoraRemoteException ere = new EsphoraRemoteException("No se obtuvieron ni errores ni el comprobante desde el servicio de esphora");
			throw new EsphoraInternalException("Respuesta incorrecta de Esphora",ere);
		}
		
		if( rawResp.getErrors() != null ){
			 errors =  rawResp.getErrors().getErr();
			 if(errors.size()>0){
				errores = new String[errors.size()];
				for (int i = 0; i < errors.size(); i++) {
					try {
						errores[i] = new String(errors.get(i).getMsg().getBytes(),"iso-8859-1");
					} catch (Exception e) {
						//fallback to same encoding
						errores[i] = errors.get(i).getMsg();
					}
				}					
			}
		}		
	}
	

	public String[] getErrores(){
		return errores;
	}
}
