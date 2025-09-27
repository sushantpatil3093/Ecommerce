package com.demo.service.impl;

import com.demo.model.Seller;
import com.demo.model.SellerReport;

public interface SellerReportService {

	SellerReport getSelleReport(Seller seller);
	SellerReport updateSellerReport(SellerReport sellerReport);
	
}
