package com.demo.service.impl;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.OrderStatus;
import com.demo.domain.PaymentStatus;
import com.demo.model.Address;
import com.demo.model.Cart;
import com.demo.model.CartItem;
import com.demo.model.Order;
import com.demo.model.OrderItem;
import com.demo.domain.*;
import com.demo.model.User;
import com.demo.repository.AddressRepository;
import com.demo.repository.OrderItemRepository;
import com.demo.repository.OrderRepository;

import jakarta.mail.FetchProfile.Item;
import jakarta.mail.internet.AddressException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceimpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderItemRepository orderItemReposiotry;
	
	@Override
	public Set<Order> createOrders(User user, Address shippingAddress, Cart cart) {
		
		if(!user.getAddress().contains(shippingAddress)) {
			user.getAddress().add(shippingAddress);
		}	
		
		 Address address=addressRepository.save(shippingAddress);
		 
		Map<Long, List<CartItem>>itemsBySeller=cart.getCartItems().stream().collect(Collectors.groupingBy(item ->item.getProduct().getSeller().getId()));
		
		Set<Order>order=new HashSet<>();
		
		for(Map.Entry<Long, List<CartItem>>entry:itemsBySeller.entrySet()) {
			Long sellerId=entry.getKey();
			List<CartItem>items=entry.getValue();
			
			int totalOrderPrice=items.stream().mapToInt(
					CartItem::getSellingPrice).sum();
		
			int totalItem=items.stream().mapToInt(CartItem::getQuantity).sum();
			
			Order creatOrder=new Order();
			creatOrder.setUser(user);
			creatOrder.setSellerId(sellerId);
			creatOrder.setTotalMrpPrice(totalItem);
			creatOrder.setTotalSellingPrice(totalOrderPrice);
			creatOrder.setTotalItem(totalItem);
			creatOrder.setShippingAddress(address);
			creatOrder.setOrderStatus(OrderStatus.PENDING);
			creatOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);
		
		 Order saveOrder=orderRepository.save(creatOrder);
		 order.add(saveOrder);
		
		List<OrderItem>orderItemts=new ArrayList<>();
		
		for(CartItem item:items) {
			OrderItem orderItem=new OrderItem();
			orderItem.setOrder(saveOrder);
			orderItem.setMrpprice(item.getMrpPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setSellingPrice(item.getSellingPrice());
		     
			saveOrder.getOrderItems().add(orderItem);
		      
		   OrderItem saveOrderItem=orderItemReposiotry.save(orderItem);
		   
		   orderItemts.add(saveOrderItem);
		}
		
		}
		return order;
	}

	@Override
	public Order findOrderById(long id) throws Exception {
		
		return orderRepository.findById(id).orElseThrow(()->
		new Exception("order not found..."));
	}


	public List<Order> usersOrderHistory(Long userId) throws Exception {
		
		return  orderRepository.findByUserId(userId);
		
	}

	@Override
	public List<Order> sellersOrder(Long sellerId) {
	
		return orderRepository.findBySellerId(sellerId);
	}

	@Override
	public Order updateOrderSatus(Long orderId, OrderStatus orderStatus) throws Exception {
		Order order=findOrderById(orderId);
		order.setOrderStatus(orderStatus);
		return orderRepository.save(order);
	}

	@Override
	public Order cancelOrder(Long orderId, User user) throws Exception {
		
		Order order=findOrderById(orderId);
		if(!user.getId().equals(order.getUser().getId())) {
	 throw new Exception("you don t have access to this order");
	}
	 order.setOrderStatus(OrderStatus.CANCELLED);
	 return orderRepository.save(order);
	
	}
	

	@Override
	public OrderItem getOrderItemById(Long id) throws Exception {
		return orderItemReposiotry.findById(id).orElseThrow(()->
		new Exception("order item not exist"));
	}

	}

