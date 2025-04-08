package com.springboot.rest_api.exception;

public class InvalidContactException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String message;
	


	public InvalidContactException(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
