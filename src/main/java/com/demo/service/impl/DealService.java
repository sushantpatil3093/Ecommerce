package com.demo.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.Deal;

public interface DealService  {
	
	List<Deal>getDeal();
	Deal createDeal(Deal deal);
	Deal updateDeal(Deal deal,Long id) throws Exception;
	void deleteDeal(Long id) throws Exception;
	
	

}
