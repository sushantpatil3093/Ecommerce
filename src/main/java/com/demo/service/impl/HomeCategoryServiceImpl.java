package com.demo.service.impl;

import java.util.List;

import com.demo.model.Category;
import com.demo.model.HomeCategory;
import com.demo.repository.HomeCategoryRepository;

public class HomeCategoryServiceImpl implements HomeCategoryService {

	private HomeCategoryRepository homeCategoryRepository;
	
	
	@Override
	public HomeCategory createHomeCategory(HomeCategory homeCategory) {
		// TODO Auto-generated method stub
		return homeCategoryRepository.save(homeCategory);
	}

	@Override
	public List<HomeCategory> createCategoryies(List<HomeCategory> homeyCategories) {
		// TODO Auto-generated method stub
		
		if(homeCategoryRepository.findAll().isEmpty()) {
			return homeCategoryRepository.saveAll(homeyCategories);
		}
		return homeCategoryRepository.findAll();
	}

	@Override
	public HomeCategory updateHomeCategory(HomeCategory category, Long id) throws Exception {
		// TODO Auto-generated method stub
	
		HomeCategory existingCategory=homeCategoryRepository.findById(id)
				.orElseThrow(()-> new Exception("category not found"));
		
		 if(category.getImage()!=null) {
			 existingCategory.setImage(category.getImage());
			 
		 }
		  if(category.getCategoryId()!=null) {
			  existingCategory.setCategoryId(category.getCategoryId());
			  
		  }
		  return homeCategoryRepository.save(existingCategory);
	}

	@Override
	public List<HomeCategory> getAllHomeCategoryies() {
		// TODO Auto-generated method stub
		return homeCategoryRepository.findAll();
	}

}
