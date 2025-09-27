package com.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Order;
import com.demo.model.PaymentOrder;
import com.demo.model.Seller;
import com.demo.model.SellerReport;
import com.demo.model.User;
import com.demo.response.ApiResponse;
import com.demo.response.PaymentLinkResponse;
import com.demo.service.impl.PaymentService;
import com.demo.service.impl.SellerReportService;
import com.demo.service.impl.SellerService;
import com.demo.service.impl.TransactionService;
import com.demo.service.impl.UserService;
import com.razorpay.RazorpayException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	private PaymentService paymentService;
	
	private UserService userService;
	
	private SellerService sellerService;
	
	private SellerReportService sellerReportService;
	
	private TransactionService transactionService; 
	
	public ResponseEntity<ApiResponse>paymentSuccessHandler(
			@PathVariable String paymentId,
			@RequestParam String paymentLinkId,
			@RequestHeader("Authorization")String jwt) throws Exception{
		
		    User user=userService.findUserByJwtToken(jwt);
		    
		    PaymentLinkResponse paymentResponse;
		    
		    PaymentOrder paymentOrder=paymentService
		    		.getPaymentOrderByPaymentId(paymentLinkId);
		    
		    boolean paymentSuccess=paymentService.ProceedPaymentOrder(paymentOrder, paymentId, paymentLinkId);
		    
		    
		    if(paymentSuccess) {
		    	for(Order order:paymentOrder.getOrders()) {
		    		Seller seller=sellerService.getSellerById(order.getSellerId());
		    		SellerReport report=sellerReportService.getSelleReport(seller);
		    		report.setTotalEarnings(report.getTotalEarnings()+order.getTotalSellingPrice());
		    		report.setTotalSales(report.getTotalSales()+order.getOrderItems().size());
		    		sellerReportService.updateSellerReport(report);
		    		
		    	}
		    }
		    
		    ApiResponse res=new ApiResponse();
		    res.setMessage("payment success");
		    
		    return new ResponseEntity<>(res,HttpStatus.CREATED);
		    
		    
		    
	}
}
