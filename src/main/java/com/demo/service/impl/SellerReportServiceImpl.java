package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Seller;
import com.demo.model.SellerReport;
import com.demo.repository.SellerReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService{

	
	@Autowired
	private SellerReportRepository sellerReportRepository;
	
	@Override
	public SellerReport getSelleReport(Seller seller) {
		// TODO Auto-generated method stub
		SellerReport sr=sellerReportRepository.findBySellerId(seller.getId());
		
		if(sr==null) {
			SellerReport newReport=new SellerReport();
			newReport.setSeller(seller);
			return sellerReportRepository.save(newReport);
		}
		return sr;
	}

	@Override
	public SellerReport updateSellerReport(SellerReport sellerReport) {
		// TODO Auto-generated method stub
		return sellerReportRepository.save(sellerReport);
	}


}
