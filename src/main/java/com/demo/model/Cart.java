package com.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private User user;
	
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<CartItem>cartItems=new HashSet<>();
	
	private double totalSellingPrice;
	
	private int totalItem;
	
	private int totalMrpPrice;
	
	private int discount;
	
	private String couponCode;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(Long id, User user, Set<CartItem> cartItems, double totalSellingPrice, int totalItem, int totalMrpPrice,
			int discount, String couponCode) {
		super();
		this.id = id;
		this.user = user;
		this.cartItems = cartItems;
		this.totalSellingPrice = totalSellingPrice;
		this.totalItem = totalItem;
		this.totalMrpPrice = totalMrpPrice;
		this.discount = discount;
		this.couponCode = couponCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotalSellingPrice() {
		return totalSellingPrice;
	}

	public void setTotalSellingPrice(double totalSellingPrice) {
		this.totalSellingPrice = totalSellingPrice;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public int getTotalMrpPrice() {
		return totalMrpPrice;
	}

	public void setTotalMrpPrice(int totalMrpPrice) {
		this.totalMrpPrice = totalMrpPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", cartItems=" + cartItems + ", totalSellingPrice="
				+ totalSellingPrice + ", totalItem=" + totalItem + ", totalMrpPrice=" + totalMrpPrice + ", discount="
				+ discount + ", couponCode=" + couponCode + "]";
	}

	
}
