package com.demo.service.impl;

import java.util.List;

import com.demo.model.Order;
import com.demo.model.Seller;
import com.demo.model.Transaction;
import com.demo.repository.SellerRepository;
import com.demo.repository.TransactionRepository;

public class TransactionServiceImpl implements TransactionService {

	private TransactionRepository transactionRepository;
	
	private SellerRepository sellerRepository;
	
	@Override
	public Transaction createTransaction(Order order) {
		// TODO Auto-generated method stub
	Seller seller=sellerRepository.findById(order.getSellerId()).get();
	
	Transaction transaction=new Transaction();
	transaction.setSeller(seller);
	transaction.setCustomer(order.getUser());
	transaction.setOrder(order);
		
		return transactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> getTransactionBySellerId(Seller seller) {
		// TODO Auto-generated method stub
		return transactionRepository.findBySellerId(seller.getId());
	}

	@Override
	public List<Transaction> getAllTransaction() {
		// TODO Auto-generated method stub
		return transactionRepository.findAll();
	}

}
