package com.demo.service.impl;

import java.util.List;

import com.demo.model.HomeCategory;

public interface HomeCategoryService {

	HomeCategory createHomeCategory(HomeCategory homeCategory);
	List<HomeCategory>createCategoryies(List<HomeCategory>homeCategoryCategories);
	HomeCategory updateHomeCategory(HomeCategory category,Long id) throws Exception;
	List<HomeCategory>getAllHomeCategoryies();
	
}
