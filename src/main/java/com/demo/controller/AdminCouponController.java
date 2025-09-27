package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Cart;
import com.demo.model.Coupon;
import com.demo.model.User;
import com.demo.repository.CouponRepository;
import com.demo.repository.UserRepository;
import com.demo.service.impl.CartService;
import com.demo.service.impl.CouponService;
import com.demo.service.impl.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminCouponController {

	@Autowired
	private CouponService couponService;
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	private  CartService cartService;
	

	@PostMapping("/api")
	public ResponseEntity<Cart>applyCoupon(
			@RequestParam String apply,
			@RequestParam String code,
			@RequestParam double orderValue,
			@RequestHeader("Authorization")String jwt) throws Exception{
		
		User user=userService.findUserByJwtToken(jwt);
		
		Cart cart;
		
		if(apply.equals("true")) {
			cart=couponService.applyCoupen(code, orderValue, user);
		}
		else {
			cart=couponService.removeCoupen(code, user);
		}
		return ResponseEntity.ok(cart);
			
	}
	     @DeleteMapping("/admin/delete/{id}")
		public ResponseEntity<?>deleteCoupon(@PathVariable Long id) throws Exception{
	    	 couponService.deleteCoupon(id);
	    	 return ResponseEntity.ok("coupon deleted successfuly");
	     }
	      @GetMapping("/admin/all")
	      public ResponseEntity<List<Coupon>>getAllCoupon(){
	    	  List<Coupon>coupons=couponService.findAllCoupens();
	    	  return ResponseEntity.ok(coupons);
					
					
		
	}
}

