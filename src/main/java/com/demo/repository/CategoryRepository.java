package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByCategoryId(String categoryId);
	
}
