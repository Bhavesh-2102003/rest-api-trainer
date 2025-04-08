package com.springboot.rest_api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.model.CustomerProduct;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.service.CustomerProductService;
import com.springboot.rest_api.service.CustomerService;
import com.springboot.rest_api.service.ProductService;


@RestController
public class CustomerProductController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CustomerProductService customerProductService;

	@PostMapping("/api/customerproduct/purchase/{cId}/{pId}")
	public CustomerProduct purchaseProduct(@PathVariable int cId,
								@PathVariable int pId,
								@RequestBody CustomerProduct customerProduct)
	{
		Customer customer=customerService.getSingleCustomer(cId);
		
		Product product=productService.getByProductId(pId);
		
		if(customerProduct.getDateOfPurchase()==null)
		{
			customerProduct.setDateOfPurchase(LocalDate.now());
		}
		
		customerProduct.setProduct(product);
		customerProduct.setCustomer(customer);
		
		
		return customerProductService.purchaseProduct(customerProduct);
	}
	
	@GetMapping("/api/customerproduct/product/{pId}")
	public List<Customer> getCustomerByProduct(@PathVariable int pId)
	{
		return customerProductService.getCustomerByProduct(pId);
	}
	
	@GetMapping("/api/customerproduct/customer/{cId}")
	public List<Product> getProductByCustomer(@PathVariable int cId)
	{
		return customerProductService.getProductByCustomer(cId);
	}
}
