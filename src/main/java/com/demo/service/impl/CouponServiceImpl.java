package com.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import com.demo.model.Cart;
import com.demo.model.Coupon;
import com.demo.model.User;
import com.demo.repository.CartRepository;
import com.demo.repository.CouponRepository;
import com.demo.repository.UserRepository;
import com.stripe.param.ChargeUpdateParams.FraudDetails.UserReport;

public class CouponServiceImpl implements CouponService{

	@Autowired
	private CouponRepository couponReposiotry;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Override
	
	public Cart applyCoupen(String code, double orderValue, User user) throws Exception {
		// TODO Auto-generated method stub
		
		Coupon coupon=couponReposiotry.findByCode(code);
		Cart cart=cartRepository.findByUserId(user.getId());
		
		if(coupon==null) {
			throw new Exception("coupon not valid");
		}
		 if(user.getUsedCoupen().contains(coupon)) {
			 throw new Exception("coupon alredy used");
			 
		 }
		  if(orderValue<coupon.getMinimumOrderValue()) {
			  
			  throw new Exception("coupon less than minuimum order value"+coupon.getMinimumOrderValue());
		  }
		  // 25th aug
		  // 26th aug
			if(coupon.isActive()&& LocalDate.now().isBefore(coupon.getValidityEndDate())  ) {
				user.getUsedCoupen().add(coupon);
				userRepository.save(user);
				double discountedPrice=(cart.getTotalSellingPrice()
						*coupon.getDiscountPercentage())/100;
		
				cart.setTotalSellingPrice(cart.getTotalSellingPrice()-discountedPrice);;
				cart.setCouponCode(code);
				cartRepository.save(cart);
			
				return cart;
			}
				throw new Exception("coupon not valid");
	}

	@Override
	public Cart removeCoupen(String code, User user) throws Exception {
		// TODO Auto-generated method stub
	Coupon coupon=couponReposiotry.findByCode(code);
	
		if(coupon==null) {
			throw new Exception("coupon not found");
		}
		Cart cart=cartRepository.findByUserId(user.getId());
		
		double discountedPrice=(cart.getTotalSellingPrice()
				*coupon.getDiscountPercentage())/100;
		
		cart.setTotalSellingPrice(cart.getTotalSellingPrice()+discountedPrice);
		
		cart.setCouponCode(null);
	 
		return cartRepository.save(cart);
	}

	@Override
	public Coupon findCoupenById(Long Id) throws Exception {
		// TODO Auto-generated method stub
		return couponReposiotry.findById(Id).orElseThrow(()->
		new Exception("coupon not found"));
	}

	@Override
	
	@PreAuthorize("hasRole('ADMIN')")
	public Coupon createCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		return couponReposiotry.save(coupon);
	}

	@Override
	public List<Coupon> findAllCoupens() {
		// TODO Auto-generated method stub
		return couponReposiotry.findAll();
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteCoupon(Long id) throws Exception {
		// TODO Auto-generated method stub
		
		findCoupenById(id);
		couponReposiotry.deleteById(id);
	}

	
}
