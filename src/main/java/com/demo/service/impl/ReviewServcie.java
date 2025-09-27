package com.demo.service.impl;

import java.util.List;

import com.demo.model.Product;
import com.demo.model.Review;
import com.demo.model.User;
import com.demo.request.CreateReviewRequest;

public interface ReviewServcie {

	Review createReview(CreateReviewRequest req,User user,
			Product product) throws Exception;
	
	List<Review>getReviewByProductId(Long productId) throws Exception;
	
	Review updateReview(Long reviewId,String reviewText,double rating,Long userId) throws Exception;
	
	void deleteReview(Long reviewId,Long userId) throws Exception;
     
	Review getReviewById(Long reviewId) throws Exception;
}
