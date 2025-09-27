package com.demo.request;

import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class AddItemRequest {

	private String size;
	private int quantity;
	private Long productId;
	public AddItemRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddItemRequest(String size, int quantity, Long productId) {
		super();
		this.size = size;
		this.quantity = quantity;
		this.productId = productId;
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
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	
	
	 
}
