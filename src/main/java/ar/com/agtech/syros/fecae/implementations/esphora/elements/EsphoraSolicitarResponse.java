package ar.com.agtech.syros.fecae.implementations.esphora.elements;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ar.com.agtech.syros.fecae.implementations.behaviors.EsphoraResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.ArrayOfFECAEDetResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.Err;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FECAECabResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FECAEDetResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FECAEResponse;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.Obs;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraInternalException;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraRemoteException;
import ar.com.agtech.syros.fecae.implementations.esphora.exceptions.EsphoraUnhandledException;
import ar.com.agtech.syros.fecae.implementations.esphora.invoices.ComprobanteFiscal;
import ar.com.agtech.syros.fecae.implementations.esphora.utils.Util;

/**
 * @author Jorge Morando
 * @param <C>
 * @param <C>
 *
 */
public class EsphoraSolicitarResponse<C extends ComprobanteFiscal> implements EsphoraResponse{

	private static Logger log = Logger.getLogger(EsphoraSolicitarResponse.class);
	
	public static final int ESTADO_NOK = 0;
	public static final int ESTADO_OK = 1;
	public static final int ESTADO_ERROR = 666;
	
	public static final String RESULTADO_RECHAZADA = "R";
	public static final String RESULTADO_APROBADA = "A";
	public static final String RESULTADO_PARCIAL = "P";
	
	private List<C> comprobantesAceptados;
	private List<C> comprobantesRechazados;
	
	private List<EsphoraError> globalErrors;
	
	private int estado;
	private String resultado;
	
	
	public EsphoraSolicitarResponse(FECAEResponse rawResp, List<C> lote) throws EsphoraInternalException{
		log.debug("Se construye objeto de respuesta");
		if(rawResp.getErrors()!=null){
			translateErrors(rawResp.getErrors().getErr());
		}
		
		FECAECabResponse cabecera = rawResp.getFeCabResp();
		ArrayOfFECAEDetResponse cuerpo = rawResp.getFeDetResp();
		try {
			
			if(cabecera != null && cuerpo != null){
				if(RESULTADO_APROBADA.equals(cabecera.getResultado())){//vineron todos aprobados
					comprobantesAceptados = new ArrayList<C>();
					int index = 0;
					FECAEDetResponse cfResp;
					for (C cf : lote) {
						cfResp = cuerpo.getFECAEDetResponse().get(index);
						comprobantesAceptados.add(processCFAccepted(cfResp, cf));
						index++;
					}
					estado = ESTADO_OK;
					resultado = RESULTADO_APROBADA;
				}else if(RESULTADO_RECHAZADA.equals(cabecera.getResultado())){
					comprobantesRechazados = new ArrayList<C>();
					int index = 0;
					FECAEDetResponse cfResp;
					for (C cf : lote) {
						cfResp = cuerpo.getFECAEDetResponse().get(index);
						comprobantesRechazados.add(processCFRejected(cfResp, cf));
						index++;
					}
					estado = ESTADO_OK;
					resultado = RESULTADO_RECHAZADA;
				}else{// vino un resultado parcial
					comprobantesAceptados = new ArrayList<C>();
					comprobantesRechazados = new ArrayList<C>();
					int index = 0;
					List<FECAEDetResponse> loteResp = cuerpo.getFECAEDetResponse();
					FECAEDetResponse cfResp;
					for (C cf : lote) {
						cfResp = loteResp.get(index);
						if(cfResp.getResultado() == RESULTADO_APROBADA){
							comprobantesAceptados.add(processCFAccepted(cfResp, cf));
						}else{
							comprobantesRechazados.add(processCFRejected(cfResp, cf));
						}
						index++;
					}
					 lote.subList(index, lote.size()-1);
					estado = ESTADO_OK;
					resultado = RESULTADO_PARCIAL;
				}
			}else{
				if(cabecera == null){
					throw new EsphoraInternalException("Null Header Received from esphora", generateStack());
				}else{
					throw new EsphoraInternalException("Null Body Received from esphora", generateStack());
				}
			}
		} catch (Exception e) {
			throw new EsphoraUnhandledException("Unhandled FECAE Implementation",e);
		}
	}
	
	private EsphoraRemoteException generateStack(){
		EsphoraRemoteException stack = null;
		for (EsphoraError error : globalErrors) {
			if(stack == null)
				stack = new EsphoraRemoteException(error.getMessage());
			else
				stack = new EsphoraRemoteException(error.getMessage(),stack);
		}
		return stack;
	}
					
	private void translateErrors(List<Err> errors){
		globalErrors = new ArrayList<EsphoraError>();
		for (Err error : errors) {
			globalErrors.add(new EsphoraError(error));
		}
	}
	
	private C processCFAccepted(FECAEDetResponse cfResp, C cf){
		if(cfResp != null){
			cf.setCae(cfResp.getCAE());
			cf.setVtoCae(Util.parseDate(cfResp.getCAEFchVto()));
			cf.setEstado(cfResp.getResultado());
			cf = processCFObs(cfResp, cf);
		}
		return cf;
	}
	
	private C processCFRejected(FECAEDetResponse cfResp, C cf){
		if(cfResp != null){
			cf = processCFObs(cfResp, cf);
		}
		return cf;
	}
	
	private C processCFObs(FECAEDetResponse cfResp, C cf){
		if(cfResp.getObservaciones()!=null && cfResp.getObservaciones().getObs()!=null){
			for (Obs o : cfResp.getObservaciones().getObs()) {
				cf.addObservacion(o);
			}
		}
		return cf;
	}
	
	@Override
	public int getEstado(){
		return this.estado;
	}
	
	@Override
	public String getResultado(){
		return this.resultado;
	}
	
	@Override
	public List<EsphoraError> getGlobalErrors(){
		if(globalErrors==null){
			globalErrors = new ArrayList<EsphoraError>();
		}
		return globalErrors;
	}

	@Override
	public boolean hasGlobalErrors() {
		return globalErrors==null || globalErrors.isEmpty();
	}

	@Override
	public int getTotalApproved() {
		if(comprobantesAceptados==null){
			return 0;
		}else{
			return comprobantesAceptados.size();
		}
	}


	@Override
	public int getTotalRejected() {
		if(comprobantesRechazados==null){
			return 0;
		}else{
			return comprobantesRechazados.size();
		}
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<C> getAllApproved() {
		return comprobantesAceptados;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<C> getAllRejected() {
		return comprobantesRechazados;
	}
}
