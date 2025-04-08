package com.springboot.rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.config.JwtUtil;
import com.springboot.rest_api.model.UserInfo;
import com.springboot.rest_api.service.AuthService;

@RestController
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/api/auth/signup")
	public UserInfo signUp(@RequestBody UserInfo user)
	{
		return authService.signUp(user);
	}
	
	
	@PostMapping("api/auth/token/generate")
	public String generateToken(@RequestBody UserInfo user)
	{
		Authentication auth=new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		authenticationManager.authenticate(auth);
		
		String token=jwtUtil.generateToken(user.getUsername());
		return token;
		
	}
	
	
}
