package com.springboot.xp.exception;

public class BaseException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6204592164138271684L;
	
	public static final String NO_LOGIN = "base_0001";
	
	public static final String NOACCOUNT_OR_PWDERROR = "base_0002";
	
	public BaseException(String errorCode) {
		super(errorCode);
	}
	
	public String getErrorCode() {
		return super.getMessage();
	}

}
