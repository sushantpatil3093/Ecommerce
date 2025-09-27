package com.demo.domain;

import java.util.Set;

import com.demo.model.Address;
import com.demo.model.Cart;
import com.demo.model.Order;
import com.demo.model.User;

public enum OrderStatus {

	PENDING,
	PLACED,
	CONFIRMED,
	SHIPPED,
	DELIVERED,
	CANCELLED;


	public Set<Order> createOrder(User user, Address spippingAddress, Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}
}
