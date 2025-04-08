package com.springboot.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.model.Vendor;
import com.springboot.rest_api.repository.VendorRepository;

@Service
public class VendorService {

	@Autowired
	private VendorRepository vendorRepository;
	
	public Vendor addVendor(Vendor vendor) {
		
		return vendorRepository.save(vendor);
	}

	public List<Vendor> getAllVendor() {
		
		return vendorRepository.findAll();
	}

	public Vendor getById(int venId) {
		
		Optional<Vendor> optional=vendorRepository.findById(venId);
		
		return optional.get();
	}
	
	

}
