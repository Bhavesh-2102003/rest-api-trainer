package com.springboot.rest_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.exception.InvalidUserNameException;
import com.springboot.rest_api.model.UserInfo;
import com.springboot.rest_api.repository.AuthRepository;

@Service
public class AuthService {
	
	@Autowired
	AuthRepository authRepository;
	
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	public UserInfo signUp(UserInfo user) throws InvalidUserNameException
	{
		UserInfo user1=authRepository.findByUsername(user.getUsername());
		if(user1!=null)
		{
			throw new InvalidUserNameException("User Already Exists");
		}
			
		
		if(user.getRole()==null)
		{
			user.setRole("Default_User");
		}
		
		String password=bcrypt.encode(user.getPassword());
		
		user.setPassword(password);
		
		return authRepository.save(user);
	}
}
