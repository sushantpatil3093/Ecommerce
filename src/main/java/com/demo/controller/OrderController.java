package com.demo.controller;

import java.util.List;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.demo.domain.OrderStatus;
import com.demo.domain.PaymentMethod;
import com.demo.model.Address;
import com.demo.model.Cart;
import com.demo.model.Order;
import com.demo.model.OrderItem;
import com.demo.model.PaymentOrder;
import com.demo.model.Seller;
import com.demo.model.SellerReport;
import com.demo.model.User;
import com.demo.repository.PaymentOrderRepository;
import com.demo.response.PaymentLinkResponse;
import com.demo.service.impl.CartService;
import com.demo.service.impl.OrderService;
import com.demo.service.impl.PaymentService;
import com.demo.service.impl.SellerReportService;
import com.demo.service.impl.SellerService;
import com.demo.service.impl.UserService;
import com.razorpay.PaymentLink;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

	//private static final int ResponseEntity = 0;
	//private static final Long OrderItem = null;
	private OrderService orderService;
	private UserService userService;
	private CartService cartService;
	private SellerService sellerService;
	private SellerReportService sellerReportService;
	
	private PaymentService paymentService;
	
	private PaymentOrderRepository PaymentOrderRepository;
	
	
	@PostMapping()
	public ResponseEntity<PaymentLinkResponse>createOrderHandler(
			@RequestBody Address spippingAddress,
			 @RequestParam PaymentMethod paymentMethod,
			 @RequestHeader("Authorization")String jwt) throws Exception{
		 
		User user=userService.findUserByJwtToken(jwt);
		Cart cart=cartService.findUserCart(user);
		
		Set<Order>orders=orderService.createOrders(user,spippingAddress,cart);
		
		PaymentOrder paymentOrder =paymentService.createOrder(user, orders);
	
	     PaymentLinkResponse res=new PaymentLinkResponse();
	   //  return new ResponseEntity<>(res,HttpStatus.OK);
	
	     if(paymentMethod.equals(paymentMethod.RAZORPAY)) {
	     PaymentLink payment=paymentService.createRazorpayPaymentLink(user,paymentOrder.getAmount(), 
	    		 paymentOrder.getId());
	     String paymentUrl=payment.get("short_url");
	     String paymentUrlId=payment.get("id");
	     
	     res.setPayment_url(paymentUrl);
	     paymentOrder.setPaymentLinkId(paymentUrl);
	     
	     PaymentOrderRepository.save(paymentOrder);
	     }
	     else {
	    	 String paymentUrl=paymentService.createStripePaymentLink(user, paymentOrder.getAmount(), 
	    			 paymentOrder.getId());
	    	 res.setPayment_url(paymentUrl);
	     }
 return new ResponseEntity<>(res,HttpStatus.OK);
	    		 
	    		 
	}
	@GetMapping("/user")
	public ResponseEntity<List<Order>>usersOrderHistoryHandler(
			@RequestHeader("Authorization")String jwt)throws Exception{
		
		User user=userService.findUserByJwtToken(jwt);
		List<Order>orders=orderService.usersOrderHistory(user.getId());
		return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
		
	}
	   
	
	@GetMapping("/{orderId}")
	public ResponseEntity<Order>getOrderById(@PathVariable Long orderId,@RequestHeader("Authorization")
	String jwt)throws Exception{
		
		//System.out.println("....controller");
		User user=userService.findUserByJwtToken(jwt);
	    Order orders=orderService.findOrderById(orderId);
	    return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
	}
	
	 @GetMapping("/item/{orderItemId}")
	 public ResponseEntity<OrderItem>getOrderItemById(
			 @PathVariable Long orderItemId,@RequestHeader("Authorization")String jwt)throws Exception{
		 System.out.println("....controller");
		 User user=userService.findUserByJwtToken(jwt);
		 OrderItem orderItem=orderService.getOrderItemById(orderItemId);
		 return new ResponseEntity<>(orderItem,HttpStatus.ACCEPTED);
	 }
	
	  @PutMapping("/{orderId}/cancel")
	  public ResponseEntity<Order>cancelOrder(
			  @PathVariable Long orderId,
			  @RequestHeader("Authorization")String jwt)throws Exception{
		  User user=userService.findUserByJwtToken(jwt);
		  Order order=orderService.cancelOrder(orderId, user);
		  
		  Seller seller=sellerService.getSellerById(order.getSellerId());
		  SellerReport report=sellerReportService.getSelleReport(seller);
		  report.setCancledOrders(report.getCancledOrders()+1);
		  report.setTotalRefunds(report.getTotalRefunds()+order.getTotalSellingPrice());
		  sellerReportService.updateSellerReport(report);
		  
		  
		  return ResponseEntity.ok(order);
	  }
	  
}







