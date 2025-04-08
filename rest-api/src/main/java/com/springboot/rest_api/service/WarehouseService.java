package com.springboot.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.model.Warehouse;
import com.springboot.rest_api.repository.WarehouseRepository;

@Service
public class WarehouseService {

	@Autowired
	WarehouseRepository warehouseRepository;
	
	public Warehouse addWarehouse(Warehouse warehouse) {
		return warehouseRepository.save(warehouse);
	}

	public List<Warehouse> getAllWarehouse() {

		return warehouseRepository.findAll();	}

	public Warehouse getById(int warId) {
		Optional<Warehouse> optional=warehouseRepository.findById(warId);
		return optional.get();
	}

}
