package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {

	WishList findByUserId(Long userId);
	
}
