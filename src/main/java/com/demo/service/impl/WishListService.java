package com.demo.service.impl;

import com.demo.model.Product;
import com.demo.model.User;
import com.demo.model.WishList;

public interface WishListService {

	WishList createWishlist(User user);
	WishList getWishlistUserId(User user);
	WishList addProductWishlist(User user,Product product);
	
}
