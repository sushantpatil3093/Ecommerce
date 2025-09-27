package com.demo.service.impl;

import java.util.List;

import com.demo.model.Cart;
import com.demo.model.Coupon;
import com.demo.model.User;

public interface CouponService {

	Cart applyCoupen(String code,double orderValue,User user) throws Exception;
	
	Cart removeCoupen(String code,User user) throws Exception;
	Coupon findCoupenById(Long Id) throws Exception;
	Coupon createCoupon(Coupon coupon);
	List<Coupon>findAllCoupens();
	void deleteCoupon(Long id) throws Exception;
	
}
