package com.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.demo.domain.HomeCategorySection;
import com.demo.model.Deal;
import com.demo.model.Home;
import com.demo.model.HomeCategory;
import com.demo.repository.DealRepository;

public class HomeServiceImpl implements HomeService {

	
	private DealRepository dealReposiory;
	
	@Override
	public Home createHomePageData(List<HomeCategory> allCategories) {
		// TODO Auto-generated method stub
	
		List<HomeCategory>gridCategories=allCategories.stream()
				.filter(category -> 
				category.getSection()==HomeCategorySection.GRID)
				.collect(Collectors.toList());
		
		List<HomeCategory>shopByCategories=allCategories.stream()
				.filter(category ->category.getSection()==
				HomeCategorySection.SHOP_BY_CATEGORIES)
				.collect(Collectors.toList());
		
		List<HomeCategory>electricCategories=allCategories.stream()
				.filter(category ->
				 category.getSection()==HomeCategorySection.ELECTRIC_CATEGORIES)
				.collect(Collectors.toList());
		
		List<HomeCategory>deleCategories=allCategories.stream()
				.filter(category -> category.getSection()==HomeCategorySection.DEALS)
				.toList();
		List<Deal>createDeal=new ArrayList<>();
						
		if(dealReposiory.findAll().isEmpty()) {
			List<Deal>deals=allCategories.stream()
				.filter(category -> category.getSection()==
				HomeCategorySection.DEALS)
				.map(category ->new Deal(null,10,category))
				.collect(Collectors.toList());
			
			createDeal= dealReposiory.saveAll(deals);
		
		}else createDeal =dealReposiory.findAll();
		
		Home home=new Home();
		home.setGrid(gridCategories);
		home.setShopByCategories(shopByCategories);
		home.setDeals(createDeal);
		home.setDealCategories(allCategories);
		
		return home;
	}

}
