package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Deal;
import com.demo.model.HomeCategory;
import com.demo.repository.DealRepository;
import com.demo.repository.HomeCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DealServcieImpl implements DealService {

	@Autowired
	private DealRepository dealRepository;
	
	@Autowired
	private HomeCategoryRepository homeCategoryRepository;
	
	@Override
	public List<Deal> getDeal() {
		// TODO Auto-generated method stub
		return dealRepository.findAll();
	}

	@Override
	public Deal createDeal(Deal deal) {
		// TODO Auto-generated method stub
	
		HomeCategory category=homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);
		
		
	Deal newDeal=dealRepository.save(deal);
	newDeal.setCategory(category);
	newDeal.setDiscount(deal.getDiscount());
	
	
		return dealRepository.save(newDeal);
	}

	@Override
	public Deal updateDeal(Deal deal,Long id) throws Exception {
		// TODO Auto-generated method stub
		Deal existingDeal=dealRepository.findById(id).orElse(null);
		HomeCategory category=homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);
		
		if(existingDeal!=null) {
			
			if(deal.getDiscount()!=null) {
				existingDeal.setDiscount(deal.getDiscount());
		}
		 if(category!=null) {
			 existingDeal.setCategory(category);
			 
		 }
		  return dealRepository.save(existingDeal);
	}
		 throw new Exception("deal not found");
	}

	public void deleteDeal(Long id) throws Exception {
		// TODO Auto-generated method stub
		Deal deal=dealRepository.findById(id).orElseThrow(()->
		new Exception("deal not found"));
	}

}
