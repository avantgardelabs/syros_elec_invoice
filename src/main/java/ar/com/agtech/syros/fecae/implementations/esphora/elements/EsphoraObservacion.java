package ar.com.agtech.syros.fecae.implementations.esphora.elements;

import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.Err;
import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.Obs;

public class EsphoraObservacion {
	
	private Obs originalObs;
	
	private String message;
	
	private int code;
	
	public EsphoraObservacion(Obs obs){
		this.originalObs = obs;
		this.message = obs.getMsg();
		this.code=obs.getCode();
	}
	
	public EsphoraObservacion(Err err){
		this.originalObs = null;
		this.message = err.getMsg();
		this.code=err.getCode();
	}

	/**
	 * @return the original Observation
	 */
	public Obs getOriginalObs() {
		return originalObs;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

}
