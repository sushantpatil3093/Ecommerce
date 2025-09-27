package com.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.AccountStatus;
import com.demo.model.Seller;
import com.demo.service.impl.SellerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class AdminController {

	private SellerService sellerService;
	
	@PatchMapping("/seller/{id}/status/{status}")
	public ResponseEntity<Seller>updateSellerStatus(
			@PathVariable Long id,
			@PathVariable AccountStatus status) throws Exception{
		
		Seller updateSeller=sellerService.updateSellerAccountStatus(id, status);
		return ResponseEntity.ok(updateSeller);
	}
	
}
