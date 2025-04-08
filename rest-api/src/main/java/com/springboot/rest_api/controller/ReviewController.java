package com.springboot.rest_api.controller;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest_api.model.Customer;
import com.springboot.rest_api.model.Product;
import com.springboot.rest_api.model.Review;
import com.springboot.rest_api.service.CustomerService;
import com.springboot.rest_api.service.ProductService;
import com.springboot.rest_api.service.ReviewService;

@RestController
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/api/review/add/{pId}/{cId}")
	public Review addReview(@PathVariable int cId,
							@PathVariable int pId,
							@RequestBody Review review)
	{
		Customer customer=customerService.getSingleCustomer(cId);
		Product product=productService.getByProductId(pId);
		
		review.setCustomer(customer);
		review.setProduct(product);
		
		return reviewService.addReview(review);
	}
	
	@GetMapping("/api/review/customer/{cId}")
	public List<Review> getReviewByCustomer(@PathVariable int cId)
	{
		return reviewService.getReviewByCustomer(cId);
	}
	
	@GetMapping("/api/review/product/{pId}")
	public List<Review> getReviewByProduct(@PathVariable int pId)
	{
		return reviewService.getReviewByProduct(pId);
	}
}
