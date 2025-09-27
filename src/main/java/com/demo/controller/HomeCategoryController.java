package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Home;
import com.demo.model.HomeCategory;
import com.demo.service.impl.HomeCategoryService;
import com.demo.service.impl.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class HomeCategoryController {

	@Autowired
	private HomeCategoryService homeCategoryService;
	
	@Autowired
	private HomeService homeService;
	
	@PostMapping("/home/categories")
	public ResponseEntity<Home>createHomeCategories(
			@RequestBody List<HomeCategory>homeCategories){
		
		List<HomeCategory>categories =homeCategoryService.createCategoryies(homeCategories);
		Home home =homeService.createHomePageData(categories);
		
		return new ResponseEntity<>(home,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/home-category")
	public ResponseEntity<List<HomeCategory>>getHomeCategory(){
		
		List<HomeCategory>categories=homeCategoryService.getAllHomeCategoryies();
		return ResponseEntity.ok(categories);
		
	}
	@PatchMapping("/home-category/{id}")
	public ResponseEntity<HomeCategory>updateHomeCategory(
			@PathVariable Long id,
			@RequestBody HomeCategory homeCategory) throws Exception{
		HomeCategory updateCategory=homeCategoryService.updateHomeCategory(homeCategory, id);
		return ResponseEntity.ok(updateCategory);
	}
	
	
	
	
	
	
}
