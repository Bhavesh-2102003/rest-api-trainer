package com.springboot.rest_api.config;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.exception.InvalidUserNameException;

@RestControllerAdvice
public class GlobalExceptionHandlerConfig {
	
	@ExceptionHandler(InvalidIDException.class)
	public ErrorResponse InvalidIDExceptionHandler(InvalidIDException e)
	{
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400), e.getMessage());
	}
	
	@ExceptionHandler(InvalidUserNameException.class)
	public ErrorResponse InvalidUserNameExceptionHandler(InvalidUserNameException e)
	{
		return ErrorResponse.create(e, HttpStatusCode.valueOf(400), e.getMessage());
	}
}
