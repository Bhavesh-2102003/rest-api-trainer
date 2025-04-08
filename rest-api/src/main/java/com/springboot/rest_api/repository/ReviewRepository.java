package com.springboot.rest_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

	List<Review> findByCustomerId(int cId);

	List<Review> findByProductId(int pId);

}
