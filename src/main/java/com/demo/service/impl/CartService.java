package com.demo.service.impl;

import com.demo.model.Cart;
import com.demo.model.CartItem;
import com.demo.model.Product;
import com.demo.model.User;

public interface CartService {

public CartItem addCartItem(
		User user,
		Product product,
		String size,
		int quantity);

    public Cart findUserCart(User user);
    
	
}
	

