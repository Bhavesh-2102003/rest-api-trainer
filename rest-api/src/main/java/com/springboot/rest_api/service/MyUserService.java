package com.springboot.rest_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.repository.AuthRepository;

@Service
public class MyUserService implements UserDetailsService {
	
	@Autowired
	AuthRepository authRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return authRepository.findByUsername(username);
		
	}

	
}
