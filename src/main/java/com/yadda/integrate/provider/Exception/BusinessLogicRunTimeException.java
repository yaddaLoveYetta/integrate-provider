package com.yadda.integrate.provider.Exception;

public class BusinessLogicRunTimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessLogicRunTimeException() {
		super("业务逻辑导致的操作不能继续");
	}

	public BusinessLogicRunTimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessLogicRunTimeException(String message) {
		super(message);
	}

	public BusinessLogicRunTimeException(Throwable cause) {
		super(cause);
	}

}
