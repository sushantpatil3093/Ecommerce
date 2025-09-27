package com.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Product;
import com.demo.model.User;
import com.demo.model.WishList;
import com.demo.service.impl.ProductService;
import com.demo.service.impl.UserService;
import com.demo.service.impl.WishListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishListController {

	private WishListService wishlistService;
	
	private UserService userService;
	
	private ProductService proudctService;
	
	@GetMapping()
	public ResponseEntity<WishList>getWishlistByUserId(
			@RequestHeader("Authorization")String jwt) throws Exception{
		
		User user =userService.findUserByJwtToken(jwt);
		
		WishList wishlist=wishlistService.getWishlistUserId(user);
		return ResponseEntity.ok(wishlist);
	}
	 @PostMapping("/add-product/{productId}")
	 public ResponseEntity<WishList>addProductToWishList(
			 @PathVariable Long productId,
			 @RequestHeader("Authorization")String jwt) throws Exception {
		 
		 Product product=proudctService.findProductById(productId);
		 User user=userService.findUserByJwtToken(jwt);
		 
		 WishList updateWishList=wishlistService.addProductWishlist(user, product);
		 
		 return ResponseEntity.ok(updateWishList);
		 
		 
		 
	 }
	 
	 
	
}
