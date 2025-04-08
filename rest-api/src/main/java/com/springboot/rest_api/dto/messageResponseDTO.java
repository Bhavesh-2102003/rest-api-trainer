package com.springboot.rest_api.dto;

import org.springframework.stereotype.Component;

@Component
public class messageResponseDTO {
	
	private int statusCode;
	private String message;
	
		
	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public messageResponseDTO() {
		super();
	}


	public messageResponseDTO(int satatusCode, String message) {
		super();
		this.statusCode = satatusCode;
		this.message = message;
	}
	
	
}
