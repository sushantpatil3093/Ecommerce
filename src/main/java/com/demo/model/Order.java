package com.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.demo.domain.OrderStatus;
import com.demo.domain.PaymentOrderStatus;
import com.demo.domain.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders")
public class Order{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String orderId;
	
	@ManyToOne
	private User user;
	
	private Long sellerId;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<OrderItem>orderItems=new ArrayList<>();
	
	@ManyToOne
	private Address shippingAddress;
	
	@Embedded
	private PaymentDetails paymentDetails=new PaymentDetails();
	
	private double totalMrpPrice;
	
	private Integer totalSellingPrice;
	
	private Integer discount;
	
	private OrderStatus orderStatus;
	
	private int totalItem;
	
	private PaymentStatus paymentStatus=PaymentStatus.PENDING;
	
	private LocalDateTime orderDate=LocalDateTime.now();
	
	private LocalDateTime deliverDate=orderDate.plusDays(7);

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Long id, String orderId, User user, Long sellerId, List<OrderItem> orderItems, Address shippingAddress,
			PaymentDetails paymentDetails, double totalMrpPrice, Integer totalSellingPrice, Integer discount,
			OrderStatus orderStatus, int totalItem, PaymentStatus paymentStatus, LocalDateTime orderDate,
			LocalDateTime deliverDate) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.user = user;
		this.sellerId = sellerId;
		this.orderItems = orderItems;
		this.shippingAddress = shippingAddress;
		this.paymentDetails = paymentDetails;
		this.totalMrpPrice = totalMrpPrice;
		this.totalSellingPrice = totalSellingPrice;
		this.discount = discount;
		this.orderStatus = orderStatus;
		this.totalItem = totalItem;
		this.paymentStatus = paymentStatus;
		this.orderDate = orderDate;
		this.deliverDate = deliverDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public double getTotalMrpPrice() {
		return totalMrpPrice;
	}

	public void setTotalMrpPrice(double totalMrpPrice) {
		this.totalMrpPrice = totalMrpPrice;
	}

	public Integer getTotalSellingPrice() {
		return totalSellingPrice;
	}

	public void setTotalSellingPrice(Integer totalSellingPrice) {
		this.totalSellingPrice = totalSellingPrice;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(LocalDateTime deliverDate) {
		this.deliverDate = deliverDate;
	}

	
	
	
	
	
	
}
