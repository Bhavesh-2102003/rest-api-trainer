package com.springboot.rest_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.dto.messageResponseDTO;
import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Category;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.model.Vendor;
import com.springboot.rest_api.model.Warehouse;
import com.springboot.rest_api.service.CategoryService;
import com.springboot.rest_api.service.ProductService;
import com.springboot.rest_api.service.VendorService;
import com.springboot.rest_api.service.WarehouseService;

@RestController
public class ProductController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	VendorService vendorService;
	
	
	@Autowired	
	ProductService productService;
	
	@Autowired
	WarehouseService warehouseService;
	
	@Autowired
	messageResponseDTO dto;
	
	Product product=null;
	
	@PostMapping("/api/product/add/{catId}/{venId}/{warId}")
	public Product addProduct(@PathVariable int catId,
										@PathVariable int venId,
										@PathVariable int warId,
										@RequestBody Product product)
	{
		Category category=categoryService.getById(catId);
		Vendor vendor=vendorService.getById(venId);
		Warehouse warehouse=warehouseService.getById(warId);
		
		product.setCategory(category);
		product.setVendor(vendor);
		product.setWarehouse(warehouse);
		
		product=productService.addProduct(product);
		
		return product;
	}
	
	@GetMapping("/api/product/category/{catId}")
	public List<Product> getProductByCategory(@PathVariable int catId,
												  @RequestParam int page,
												  @RequestParam int size) throws InvalidIDException
	{
		Pageable pageable=PageRequest.of(page, size);
		
		
			List<Product> list=productService.getProductByCategory(catId,pageable);
			return list;
		
		
		
	}
	
	@GetMapping("/api/product/vendor/{venId}")
	public ResponseEntity<?> getProductByVendor(@PathVariable int venId,
												  @RequestParam int page,
												  @RequestParam int size)
	{
		Pageable pageable=PageRequest.of(page, size);
		
		try
		{
			List<Product> list=productService.getProductByVendor(venId,pageable);
			return ResponseEntity.ok(list);
		}
		catch(InvalidIDException e)
		{
			dto.setMessage(e.getMessage());
			dto.setStatusCode(400);
			return ResponseEntity.status(400).body(dto);
		}
	}
	
	@GetMapping("/api/product/getAll")
	public List<Product> getAllProduct()
	{
		return productService.getAllProduct();
	}
}
