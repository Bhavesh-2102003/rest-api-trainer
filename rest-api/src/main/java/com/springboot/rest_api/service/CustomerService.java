package com.springboot.rest_api.service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.exception.InvalidContactException;
import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer addCustomer(Customer customer) {
		
		return customerRepository.save(customer);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();		
	}

	public Customer getSingleCustomer(int id) throws InvalidIDException{
		
	
		Optional<Customer> optional = customerRepository.findById(id);
	    
	    if (optional.isEmpty()) { 
	        throw new InvalidIDException("User ID is invalid");
	    }
	    
	    return optional.get();
	}

	public void hardDelete(Customer customer) {
		customerRepository.delete(customer);
		
	}

	public void softDelete(Customer customer) {
		customer.setActive(true);
		customerRepository.save(customer);
		
	}
	
	public List<Customer> findByContact(String contact) throws InvalidContactException {
		if(contact.length() < 10)
			throw new InvalidContactException("contact number invalid must be 10 digits..");
		return customerRepository.findByContact(contact);
	}
	
//	public List<Customer> findByActive(boolean active)  {
//		
//		return customerRepository.findByActive(active);
//	}
}
