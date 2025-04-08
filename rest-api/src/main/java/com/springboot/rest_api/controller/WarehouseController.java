package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.model.Manager;
import com.springboot.rest_api.model.Warehouse;
import com.springboot.rest_api.service.ManagerService;
import com.springboot.rest_api.service.WarehouseService;

@RestController
public class WarehouseController {
	
	@Autowired
	WarehouseService warehouseService;
	
	
	
	@Autowired
	ManagerService managerService;
	
	@PostMapping("/api/warehouse/add/{manId}")
	public Warehouse addWarehouse(@PathVariable int manId,
								  @RequestBody Warehouse warehouse)
	{
		Manager manager=managerService.getById(manId);
		warehouse.setManager(manager);
		return warehouseService.addWarehouse(warehouse);
	}
	
	@GetMapping("/api/warehouse/getAll")
	public List<Warehouse> getAllWarehouse()
	{
		return warehouseService.getAllWarehouse();
	}
	
	@GetMapping("/api/warehouse/get/{warId}")
	public Warehouse getById(@PathVariable int warId)
	{
		return warehouseService.getById(warId);
	}
}
