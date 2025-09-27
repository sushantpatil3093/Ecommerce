package com.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.demo.model.Product;
import com.demo.model.Review;
import com.demo.model.User;
import com.demo.repository.ReviewRespository;
import com.demo.request.CreateReviewRequest;
import com.demo.response.ApiResponse;
import com.demo.service.impl.ProductService;
import com.demo.service.impl.ReviewServcie;
import com.demo.service.impl.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

	//private ReviewRespository reviewRespository;
	
	//private ReviewRespository reviewRespository;
	
	private ReviewServcie reviewServcie;
	
	private UserService userService;
	
	private ProductService productService;
	
	@GetMapping("/products/{productId}/reviews")
	public ResponseEntity<List<Review>>getReviewByProductId(
			@PathVariable Long productId) throws Exception{
	
		List<Review>reviews=reviewServcie.getReviewByProductId(productId);
		return ResponseEntity.ok(reviews);
	}
	
	 @PostMapping("/products/{productId}/reviews")
	 public  ResponseEntity<Review>writeReview(
			 @RequestBody CreateReviewRequest req,
			@PathVariable Long productId,
			@RequestHeader("Authorization")String jwt) throws Exception {
	 
	 User user=userService.findUserByJwtToken(jwt);
	 
	 Product product=productService.findProductById(productId);
	 
	 Review review=reviewServcie.createReview(req, user, product);
	 
	 return ResponseEntity.ok(review);
}
       @PatchMapping("/review/{reviewId}")
       public ResponseEntity<Review>updateReview(
    		   @RequestBody CreateReviewRequest req,
    		   @PathVariable Long reviewId,
    		   @RequestHeader("Authorization")String jwt) throws Exception{
    	   
    	   User user=userService.findUserByJwtToken(jwt);
    	   
    	   Review review=reviewServcie.updateReview(reviewId, 
    			   req.getReviewText(), req.getReviewRating(),
    			   user.getId());
    	   
  	 return ResponseEntity.ok(review);
       
       }
       @DeleteMapping("/review/{reviewId}")
       public ResponseEntity<ApiResponse>deleteReview(
    		   @PathVariable Long reviewId,@RequestHeader("Authorization")String jwt) throws Exception{
    	   
    	   User user=userService.findUserByJwtToken(jwt);
    	   
    	   reviewServcie.deleteReview(reviewId,user.getId());
    	   ApiResponse res=new ApiResponse();
    	   res.setMessage("riview deleted successfuly");
    	   
    	  
    	   
    	   return ResponseEntity.ok(res);
       }
}
