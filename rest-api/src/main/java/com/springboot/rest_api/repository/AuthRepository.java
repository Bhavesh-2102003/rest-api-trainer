package com.springboot.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.UserInfo;

public interface AuthRepository extends JpaRepository<UserInfo, Integer>{
	
	UserInfo findByUsername(String Username);
}
