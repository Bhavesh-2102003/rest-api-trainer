package com.springboot.rest_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.model.CustomerProduct;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.repository.CustomerProductRepository;
import com.springboot.rest_api.repository.CustomerRepository;

@Service
public class CustomerProductService {

	@Autowired
    CustomerRepository customerRepository;

	@Autowired
	CustomerProductRepository customerProductRepository;


	
	public CustomerProduct purchaseProduct(CustomerProduct customerProduct) {
		
		return customerProductRepository.save(customerProduct);

	}
	
	public List<Product> getProductByCustomer(int cId)
	{
		
		List<CustomerProduct> list= customerProductRepository.findByProductId(cId);
		return list.stream()
				.map(cp->cp.getProduct())
				.toList();	
	}

	public List<Customer> getCustomerByProduct(int pId) {
		
		List<CustomerProduct> list= customerProductRepository.findByProductId(pId);
		return list.stream()
				.map(cp->cp.getCustomer())
				.toList();	
		}

}
