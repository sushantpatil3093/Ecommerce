package com.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Cart;
import com.demo.model.CartItem;
import com.demo.model.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

//	CartItem findByCartAndProductAndSize(Cart cart,Product product,String size);
	CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
//	CartItemRepository.findByCartAndProductAndSize(Cart, Product, "M");
	///  Optional<CartItem> findByCartAndProductAndSize(Cart cart, Product product, String size);
}
