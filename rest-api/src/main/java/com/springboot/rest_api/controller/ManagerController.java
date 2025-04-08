package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.model.Manager;
import com.springboot.rest_api.service.ManagerService;

@RestController
public class ManagerController {
	
	@Autowired
	ManagerService managerService;
	
	@PostMapping("/api/manager/add")
	public Manager addManager(@RequestBody Manager manager)
	{
		return managerService.addManager(manager);
	}
	
	@GetMapping("/api/manager/getAll")
	public List<Manager> getAllManager()
	{
		return managerService.getAllManager();
	}
	
	@GetMapping("/api/manager/get/{manId}")
	public Manager getById(@PathVariable int manId)
	{
		return managerService.getById(manId);
	}
}
