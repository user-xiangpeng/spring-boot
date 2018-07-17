package com.springboot.xp.bean;

import java.io.Serializable;

public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8208299221665878073L;
	
	private boolean success = true;
	private String errorCode;
	private String errorMessage;
	private Object data;
	
	public Result() {
		super();
	}
	
	public Result(boolean success, String errorCode, String errorMessage, Object data) {
		super();
		this.success = success;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.data = data;
	}
		
	public static Result success(){
		return new Result();
	}
	
	public static Result success(Object data){
		Result result = new Result();
		result.setData(data);
		return result;
	}
	
	public static Result error(String errorCode){
		Result result = new Result();
		result.setErrorCode(errorCode);
		return result;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	

}
