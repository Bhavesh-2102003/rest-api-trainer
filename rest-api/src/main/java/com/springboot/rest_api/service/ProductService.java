package com.springboot.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.exception.InvalidIDException;
import com.springboot.rest_api.model.Category;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.model.Vendor;
import com.springboot.rest_api.repository.CategoryRepository;
import com.springboot.rest_api.repository.ProductRepository;
import com.springboot.rest_api.repository.VendorRepository;

@Service
public class ProductService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	VendorRepository vendorRepository;

	@Autowired
	ProductRepository productRepository;
	public Product addProduct(Product product) {
		
		return productRepository.save(product);
	}
	public List<Product> getProductByCategory(int catId, Pageable pageable) throws InvalidIDException{
		
		Optional<Category> optional=categoryRepository.findById(catId);
		if(optional.isEmpty())
			throw new InvalidIDException("Category ID is invalid");
		
		return productRepository.findByCategoryId(catId,pageable);
	}
	
	public List<Product> getProductByVendor(int venId, Pageable pageable) {
		
		Optional<Vendor> optional=vendorRepository.findById(venId);
		if(optional.isEmpty())
			throw new InvalidIDException("Category ID is invalid");
		
		return productRepository.findProductByVendor(optional.get(),pageable);
	}
	public  List<Product> getAllProduct() {
		
		return productRepository.findAll();
	}
	
	
	public Product getByProductId(int pId)
	{
		Optional<Product> optional=productRepository.findById(pId);
		return optional.get();
	}

}
