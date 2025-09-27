package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.CartItem;
import com.demo.model.User;
import com.demo.repository.CartItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;
	
	
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception {
		
		CartItem item=findCartItemById(id);
		
		User cartItemUser=item.getCart().getUser();
		
		if(cartItemUser.getId().equals(userId)) {
		item.setQuantity(cartItem.getQuantity());
		
		item.setMrpPrice(item.getQuantity()*item.getProduct().getMrpPrice());
		item.setSellingPrice(item.getQuantity()*item.getProduct().getSellingPrice());
		return cartItemRepository.save(item);
		
	}
	   throw new Exception("you can`t update this cartitem");
		
}


	public void removeCartItem(Long userId,Long cartItemId) throws Exception {
		// TODO Auto-generated method stub
		
        CartItem item=findCartItemById(cartItemId);
		
		User cartItemUser=item.getCart().getUser();
		
		if(cartItemUser.getId().equals(userId)) {
			cartItemRepository.delete(item);
		}
		else throw new Exception("you can`t delete this item");
	}


	public CartItem findCartItemById(Long id) throws Exception {
		// TODO Auto-generated method stub
	
		return cartItemRepository.findById(id).orElseThrow(()->
		new Exception("cart item not found with id"+id));
		}


	
	}
