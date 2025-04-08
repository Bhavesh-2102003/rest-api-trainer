package com.springboot.rest_api.controller;

import java.util.List;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.dto.messageResponseDTO;
import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private messageResponseDTO messageDTO;
	
	@GetMapping("/api/public/hello")
	public String sayHello()
	{
		return "Hello from Spring boot";
	}
	
	@GetMapping("/api/private/hello")
	public String sayHelloPrivate()
	{
		return "Hello from Spring boot in private";
	}
	
	@PostMapping("/api/customer/add")
	public Customer addCustomer(@RequestBody Customer customer)
	{
		return  customerService.addCustomer(customer);
	}
	
	@GetMapping("/api/customer/getAll")
	public List<Customer> getAllEmployees()
	{
		 	return customerService.getAllCustomers();
	}
	
	@GetMapping("/api/customer/one/{id}")
	public ResponseEntity<?> getSingleCustomer(@PathVariable int id)
	{
		try
		{
			
			Customer customer=customerService.getSingleCustomer(id);
			return ResponseEntity.ok(customer);
		}
		catch(InvalidIDException e)
		{
			messageDTO.setMessage(e.getMessage());
			messageDTO.setStatusCode(400);
			return ResponseEntity.status(400).body(e.getMessage());
		}

		
	}
	
	@DeleteMapping("/api/customer/delete/{id}")
	public ResponseEntity<?> hardDeleteCustomer(@PathVariable int id)
	{
		try {
			Customer customer=customerService.getSingleCustomer(id);
			customerService.hardDelete(customer);
			messageDTO.setMessage("Customer hard  deleted from DB!");
			messageDTO.setStatusCode(200);
			return ResponseEntity.ok(messageDTO);
			
		} catch (InvalidIDException e) {
			messageDTO.setMessage(e.getMessage());
			messageDTO.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDTO); 
		}
	}
	
	@DeleteMapping("/api/customer/softDelete/{id}")
	public ResponseEntity<?> softDeleteCustomer(@PathVariable int id)
	{
		try {
			Customer customer=customerService.getSingleCustomer(id);
			customerService.softDelete(customer);
			messageDTO.setMessage("Customer soft deleted from DB!");
			messageDTO.setStatusCode(200);
			return ResponseEntity.ok(messageDTO);
			
		} catch (InvalidIDException e) {
			
			messageDTO.setMessage(e.getMessage());
			messageDTO.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDTO); 
		}
	}
	
	
	@PutMapping("api/customer/update/{id}")
	public ResponseEntity<?> updateCustomer(@PathVariable int id,@RequestBody Customer newValue)
	{
		try
		{
			Customer customer = customerService.getSingleCustomer(id);
			if(newValue.getName()!=null)
			{
				customer.setName(newValue.getName());
			}
			if(newValue.getContact()!=null)
			{
				customer.setContact(newValue.getContact());
			}
			customer=customerService.addCustomer(customer);
			return ResponseEntity.ok(customer);
		}
		catch(InvalidIDException e)
		{
			messageDTO.setMessage(e.getMessage());
			messageDTO.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDTO);
		}
		
	}
	
	
	@GetMapping("/api/customer/contact")
	public ResponseEntity<?> findByContact(@RequestParam String contact)
	{
		try {
			List<Customer> list = customerService.findByContact(contact);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			messageDTO.setMessage(e.getMessage());
			messageDTO.setStatusCode(400);
			return ResponseEntity.status(400).body(messageDTO);
		}
	}
	
//	@GetMapping("/api/customer/active")
//	public List<Customer> findByActive(@RequestParam boolean active)
//	{
//		return customerService.findByActive(active);
//	}
}
