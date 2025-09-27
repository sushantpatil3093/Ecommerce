package com.demo.service.impl;

import org.springframework.stereotype.Service;

import com.demo.model.Product;
import com.demo.model.User;
import com.demo.model.WishList;
import com.demo.repository.WishListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishListImpl implements WishListService {

	private WishListRepository wishListRepository;
	
	
	
	@Override
	public WishList createWishlist(User user) {
		// TODO Auto-generated method stub
		WishList wishList=new WishList();
		
		wishList.setUser(user);
		return wishListRepository.save(wishList);
	}

	@Override
	public WishList getWishlistUserId(User user) {
		// TODO Auto-generated method stub
		
		WishList wishList=wishListRepository.findByUserId(user.getId());
		if(wishList==null) {
		wishList=createWishlist(user);
		}
		return wishList;
	}

	@Override
	public WishList addProductWishlist(User user, Product product) {
		// TODO Auto-generated method stub
		
		WishList wishList=getWishlistUserId(user);
		if(wishList.getProduct().contains(product)) {
			wishList.getProduct().remove(product);
		}
		else wishList.getProduct().add(product);
		
		
		return wishListRepository.save(wishList);
	}

}
