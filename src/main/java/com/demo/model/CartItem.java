package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JsonIgnore
	private Cart cart;

	@ManyToOne
    private Product product;
   
   private String size;
   
   private int quantity=1;
   
   private Integer mrpPrice =0;
   
   private Integer sellingPrice =0;
   
   private Long userId;

public CartItem() {
	super();
	// TODO Auto-generated constructor stub
}

public CartItem(Long id, Cart cart, Product product, String size, int quantity, Integer mrpPrice, Integer sellingPrice,
		Long userId) {
	super();
	this.id = id;
	this.cart = cart;
	this.product = product;
	this.size = size;
	this.quantity = quantity;
	this.mrpPrice = mrpPrice;
	this.sellingPrice = sellingPrice;
	this.userId = userId;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Cart getCart() {
	return cart;
}

public void setCart(Cart cart) {
	this.cart = cart;
}

public Product getProduct() {
	return product;
}

public void setProduct(Product product) {
	this.product = product;
}

public String getSize() {
	return size;
}

public void setSize(String size) {
	this.size = size;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public Integer getMrpPrice() {
	return mrpPrice;
}

public void setMrpPrice(Integer mrpPrice) {
	this.mrpPrice = mrpPrice;
}

public Integer getSellingPrice() {
	return sellingPrice;
}

public void setSellingPrice(Integer sellingPrice) {
	this.sellingPrice = sellingPrice;
}

public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}



}
