package com.springboot.rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.model.UserInfo;
import com.springboot.rest_api.service.AuthService;

@RestController
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/api/auth/signup")
	public UserInfo signUp(@RequestBody UserInfo user)
	{
		return authService.signUp(user);
	}
	
	
}
