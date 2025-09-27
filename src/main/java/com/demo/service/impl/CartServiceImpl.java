package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Cart;
import com.demo.model.CartItem;
import com.demo.model.Product;
import com.demo.model.User;
import com.demo.repository.CartItemRepository;
import com.demo.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartReposioty;
	
	@Autowired
	private CartItemRepository cartItemRepository;


	public CartItem addCartItem(User user, Product product, String size, int quantity) {
		
		Cart cart=findUserCart(user);
		
		CartItem isPresent=cartItemRepository.findByCartAndProductAndSize(cart, product, size);
		
		if(isPresent==null) {
			CartItem cartItem=new CartItem();
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			cartItem.setUserId(user.getId());
			
			cartItem.setSize(size);
			
			int totalPrice=quantity * product.getSellingPrice();
			cartItem.setSellingPrice(totalPrice);
		//	cartItem.setMrpPrice(quantity*product.getSellingPrice());
			
			cart.getCartItems().add(cartItem);
			cartItem.setCart(cart);
			
			return cartItemRepository.save(cartItem);
			
		}
		
		return isPresent;
	}

	public Cart findUserCart(User user) {
		
		Cart cart=cartReposioty.findByUserId(user.getId());
		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		
		for(CartItem cartItem: cart.getCartItems()) {
			totalPrice+=cartItem.getMrpPrice();
			totalDiscountedPrice+=cartItem.getSellingPrice();
			totalItem+=cartItem.getQuantity();
		}
		cart.setTotalMrpPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setDiscount(calculateDiscountPercetage(totalDiscountedPrice, totalPrice));
        cart.setTotalItem(totalItem);
        
		return cart;
	}
	
	private int calculateDiscountPercetage(int mrpPrice,int sellingPrice) { 
		
	 if(mrpPrice<=0) {
		// throw new IllegalArgumentException("Actual price must be greater than 0");
		 return 0;
	 }
	  double discount=mrpPrice-sellingPrice;
	  double disountPercetage=(discount/mrpPrice)*100;
	  
		return (int)disountPercetage;
	}}
	

