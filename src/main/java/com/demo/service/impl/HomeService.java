package com.demo.service.impl;

import java.util.List;

import com.demo.model.Home;
import com.demo.model.HomeCategory;

public interface HomeService {

	public Home createHomePageData(List<HomeCategory>allCategories);
	
}
