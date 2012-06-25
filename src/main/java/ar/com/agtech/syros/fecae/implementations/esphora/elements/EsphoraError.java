package ar.com.agtech.syros.fecae.implementations.esphora.elements;

import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.Err;

public class EsphoraError {
	
	private Err originalError;
	
	private String message;
	
	private int code;
	
	public EsphoraError(Err error){
		this.originalError = error;
		this.message = error.getMsg();
		this.code=error.getCode();
	}

	/**
	 * @return the original Error
	 */
	public Err getOriginalError() {
		return originalError;
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
