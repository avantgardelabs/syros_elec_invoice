/**
 * 
 */
package ar.com.agtech.syros.esphora.conector.elements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ar.com.agtech.syros.esphora.conector.artifacts.ArrayOfFECAEDetResponse;
import ar.com.agtech.syros.esphora.conector.artifacts.Err;
import ar.com.agtech.syros.esphora.conector.artifacts.FECAECabResponse;
import ar.com.agtech.syros.esphora.conector.artifacts.FECAEDetResponse;
import ar.com.agtech.syros.esphora.conector.artifacts.FECAEResponse;
import ar.com.agtech.syros.esphora.conector.artifacts.Obs;
import ar.com.agtech.syros.esphora.conector.exceptions.EsphoraRemoteException;
import ar.com.agtech.syros.esphora.utils.Util;

/**
 * @author Jorge Morando
 *
 */
public class FESimpleResponse {

	private static Logger log = Logger.getLogger(FESimpleResponse.class);
	
	public static final int ESTADO_ERROR = 666;
	public static final int ESTADO_RECHAZADA = 0;
	public static final int ESTADO_APROBADA = 1;
	
	public static final String RESULTADO_RECHAZADA = "R";
	public static final String RESULTADO_APROBADA = "A";
	
	private int estado;
	private String cae;
	private Date vtoCae;
	private String[] errores;
	
	public FESimpleResponse(FECAEResponse rawResp) throws EsphoraRemoteException {
		log.debug("Se construye objeto de respuesta");
		List<Err> errors = new ArrayList<Err>();
		if( rawResp.getErrors() != null ){
			 errors =  rawResp.getErrors().getErr();
		}
		
		FECAECabResponse cabecera = rawResp.getFeCabResp();
		ArrayOfFECAEDetResponse cuerpo = rawResp.getFeDetResp();
		
		if(cabecera !=null){//hay cabecera
			if(RESULTADO_APROBADA.equals(cabecera.getResultado())){//vino aprobada
				if(cuerpo!=null){
					FECAEDetResponse primerRespuesta = cuerpo.getFECAEDetResponse().get(0);
					this.cae = primerRespuesta.getCAE();
					this.vtoCae = Util.parseDate(primerRespuesta.getCAEFchVto());
					estado = ESTADO_APROBADA;
				}else{
					estado = ESTADO_ERROR;
					for (int i = 0; i < errors.size(); i++) {
						try {
							errores[i] = new String(errors.get(i).getMsg().getBytes(),"iso-8859-1");
						} catch (Exception e) {
							//fallback to same encoding
							errores[i] = errors.get(i).getMsg();
						}
					}
				}
			}else{// vino rechazada
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
					if(cuerpo !=null){
						FECAEDetResponse primerRespuesta = cuerpo.getFECAEDetResponse().get(0);
						if(primerRespuesta!=null && primerRespuesta.getObservaciones()!=null){
							List<Obs> observaciones = primerRespuesta.getObservaciones().getObs();
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
						throw new EsphoraRemoteException("No se incluye cuerpo en respuesta de Esphora");
					}
				}
			}
		}else{//no vino con cabecera
			estado = ESTADO_ERROR;
			throw new EsphoraRemoteException("No se incluye cabecera en respuesta de Esphora");
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
