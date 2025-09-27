package com.demo.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.demo.model.Product;
import com.demo.model.Review;
import com.demo.model.User;
import com.demo.repository.ReviewRespository;
import com.demo.request.CreateReviewRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewServcie {

	private ReviewRespository reviewRespository;
	
	
	@Override
	public Review createReview(CreateReviewRequest req, User user, Product product) {
		// TODO Auto-generated method stub
		
		Review review=new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReviewText(req.getReviewText());
		review.setRating(req.getReviewRating());
		review.setProductImages(req.getProductImages());
		
		product.getReviews().add(review);
		return reviewRespository.save(review);
		
		
	}

	@Override
	public List<Review> getReviewByProductId(Long productId) {
		// TODO Auto-generated method stub
		return reviewRespository.findByProductId(productId);
	}

	@Override
	public Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception {
		// TODO Auto-generated method stub
	 Review review=getReviewById(reviewId);
	 
	 if(review.getUser().getId().equals(userId)) {
		 review.setReviewText(reviewText);
		 review.setRating(rating);
		 return reviewRespository.save(review);
	 }
	 throw new Exception("you can t update this review");
	
	}

	@Override
	public void deleteReview(Long reviewId, Long userId) throws Exception {
		// TODO Auto-generated method stub
		Review review=getReviewById(reviewId);
		if(review.getUser().getId().equals(userId)) {
		}
		reviewRespository.delete(review);
		}

	
	public Review getReviewById(Long reviewId) throws Exception {
		// TODO Auto-generated method stub
		return reviewRespository.findById(reviewId).orElseThrow(()->
		
		new Exception("review not found"));
	}

	
}
