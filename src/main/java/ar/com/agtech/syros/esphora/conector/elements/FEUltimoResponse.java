/**
 * 
 */
package ar.com.agtech.syros.esphora.conector.elements;

import java.util.ArrayList;
import java.util.List;

import ar.com.agtech.syros.esphora.conector.artifacts.Err;
import ar.com.agtech.syros.esphora.conector.artifacts.FERecuperaLastCbteResponse;

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


	public FEUltimoResponse(FERecuperaLastCbteResponse rawResp){
		this.cbteNro = rawResp.getCbteNro();
		this.cbteTipo = rawResp.getCbteTipo();
		this.ptoVta = rawResp.getPtoVta();
		List<Err> errors = new ArrayList<Err>();
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
