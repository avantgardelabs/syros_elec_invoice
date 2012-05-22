/**
 * 
 */
package ar.com.agtech.syros.esphora.conector.elements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.agtech.syros.esphora.conector.artifacts.Err;
import ar.com.agtech.syros.esphora.conector.artifacts.FECAECabResponse;
import ar.com.agtech.syros.esphora.conector.artifacts.FECAEDetResponse;
import ar.com.agtech.syros.esphora.conector.artifacts.FECAEResponse;
import ar.com.agtech.syros.esphora.conector.artifacts.Obs;
import ar.com.agtech.syros.esphora.utils.Util;

/**
 * @author Jorge Morando
 *
 */
public class FESimpleResponse {

	public static final int ESTADO_ERROR = 666;
	public static final int ESTADO_RECHAZADA = 0;
	public static final int ESTADO_APROBADA = 1;
	
	public static final String RESULTADO_RECHAZADA = "R";
	public static final String RESULTADO_APROBADA = "A";
	
	private int estado;
	private String cae;
	private Date vtoCae;
	private String[] errores;
	
	public FESimpleResponse(FECAEResponse rawResp){
		List<Err> errors = new ArrayList<Err>();
		if( rawResp.getErrors() != null ){
			 errors =  rawResp.getErrors().getErr();
		}
		
		FECAECabResponse cabecera = rawResp.getFeCabResp();
		
		if(cabecera.getResultado().equals(RESULTADO_RECHAZADA)){
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
				estado = ESTADO_RECHAZADA;
			}else{
				if(rawResp.getFeDetResp()!=null){
					FECAEDetResponse cuerpo = rawResp.getFeDetResp().getFECAEDetResponse().get(0);
					if(cuerpo.getObservaciones()!=null){
						List<Obs> observaciones = cuerpo.getObservaciones().getObs();
						errores = new String[observaciones.size()];
						for (int i = 0; i < observaciones.size(); i++) {
							try {
								errores[i] = new String(observaciones.get(i).getMsg().getBytes(),"iso-8859-1");
							} catch (Exception e) {
								//fallback to same encoding
								errores[i] = observaciones.get(i).getMsg();
							}
						}
						estado = ESTADO_RECHAZADA;
					}else
						estado = ESTADO_ERROR;
				}else{
					estado = ESTADO_ERROR;
				}
			}
		}else{
			if(rawResp.getFeDetResp()!=null){
				FECAEDetResponse cuerpo = rawResp.getFeDetResp().getFECAEDetResponse().get(0);
				this.cae = cuerpo.getCAE();
				this.vtoCae = Util.parseDate(cuerpo.getCAEFchVto());
				estado = ESTADO_APROBADA;
			}else
				estado = ESTADO_ERROR;
		}
		
		
	}
	
	public int getEstado(){
		return this.estado;
	}
	
	public String getCAE(){
		return this.cae;
	}
	
	public Date getVtoCAE(){
		return this.vtoCae;
	}
	
	public String[] getErrores(){
		return errores;
	}
}
