package com.springboot.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.model.Manager;
import com.springboot.rest_api.repository.ManagerRepository;

@Service
public class ManagerService {

	@Autowired
	ManagerRepository managerRepository;
	
	public Manager addManager(Manager manager) {
		return managerRepository.save(manager);
	}
	
	public List<Manager> getAllManager() {
		
		return managerRepository.findAll();
	}
	
	
	public Manager getById(int manId) {
		
		Optional<Manager> optional=managerRepository.findById(manId);
		return optional.get();
		
	}

}
