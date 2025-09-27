package com.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Seller;
import com.demo.model.Transaction;
import com.demo.repository.TransactionRepository;
import com.demo.service.impl.SellerService;
import com.demo.service.impl.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/transactions")
public class TransactionController {

	private TransactionService trasnactionService;
    
	private SellerService sellerService;

	public ResponseEntity<List<Transaction>>getTransactionBySeller(
			@RequestHeader("Authorization")String jwt) throws Exception{
		
		Seller seller=sellerService.getSellerProfile(jwt);
		
		List<Transaction>transaction=trasnactionService.getTransactionBySellerId(seller);
		return ResponseEntity.ok(transaction);
	}
  
	@GetMapping
	public ResponseEntity<List<Transaction>>getAllTransaction(){
		List<Transaction>transactions=trasnactionService.getAllTransaction();
		return ResponseEntity.ok(transactions);
	}

}

