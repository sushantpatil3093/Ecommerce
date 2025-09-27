package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Review;

@Repository
public interface ReviewRespository  extends JpaRepository<Review, Long>{

	List<Review>findByProductId(Long productId);
	
	
}
