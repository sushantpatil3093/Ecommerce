package com.demo.service.impl;

import java.util.List;

import com.demo.model.Order;
import com.demo.model.Seller;
import com.demo.model.Transaction;

public interface TransactionService {

	Transaction createTransaction(Order order);
	
	List<Transaction>getTransactionBySellerId(Seller seller);
	List<Transaction>getAllTransaction();
}
