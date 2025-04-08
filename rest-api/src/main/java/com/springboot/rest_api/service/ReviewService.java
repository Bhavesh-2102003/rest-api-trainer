package com.springboot.rest_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest_api.model.Review;
import com.springboot.rest_api.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;

	public Review addReview(Review review) {
		
		return reviewRepository.save(review);
	}

	public List<Review> getReviewByCustomer(int cId) {
		
		return reviewRepository.findByCustomerId(cId);
	}

public List<Review> getReviewByProduct(int pId) {
		
		return reviewRepository.findByProductId(pId);
	}

}
