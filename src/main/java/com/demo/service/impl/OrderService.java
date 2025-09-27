package com.demo.service.impl;

import java.util.Set;

import java.util.List;
import com.demo.model.Address;
import com.demo.model.Cart;
import com.demo.model.Order;
import com.demo.model.OrderItem;
import com.demo.domain.*;
import com.demo.model.User;


public interface OrderService {

	Set<Order>createOrders(User user,Address shippingAddress,Cart cart);
  Order findOrderById(long id) throws Exception;
  List<Order>usersOrderHistory(Long userId) throws Exception;
  List<Order>sellersOrder(Long sellerId) throws Exception;
  Order updateOrderSatus(Long orderId,OrderStatus orderStatus) throws Exception;
  Order cancelOrder(Long orderId,User user) throws Exception;
 
  OrderItem getOrderItemById(Long id) throws Exception;





}
