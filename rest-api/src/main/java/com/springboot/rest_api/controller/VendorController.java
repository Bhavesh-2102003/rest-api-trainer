package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.model.Vendor;
import com.springboot.rest_api.service.VendorService;

@RestController
public class VendorController {
	
	@Autowired
	private VendorService vendorService;
	
	@PostMapping("/api/vendor/add")
	public Vendor addVendor(@RequestBody Vendor vendor)
	{
		return vendorService.addVendor(vendor);
	}
	
	@GetMapping("/api/vendor/getAll")
	public List<Vendor> getAllVendor()
	{
		return vendorService.getAllVendor();
	}
}
