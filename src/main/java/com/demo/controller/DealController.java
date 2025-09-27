package com.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Deal;
import com.demo.response.ApiResponse;
import com.demo.service.impl.DealService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/deals")
public class DealController {

	private DealService dealService;
	
	@PostMapping()
	public ResponseEntity<Deal>createDeals(
			@RequestBody Deal deals){
		
		Deal createDeals=dealService.createDeal(deals);
		return new ResponseEntity<>(createDeals,HttpStatus.ACCEPTED);
	}
	 @PatchMapping("/{id}")
	 public ResponseEntity<Deal>updateDeal(
			 @PathVariable Long id,
			 @RequestBody Deal deal) throws Exception{
		 
		 Deal updateDeal=dealService.updateDeal(deal, id);
		 return ResponseEntity.ok(updateDeal);
	 }
	   
	   public ResponseEntity<ApiResponse>deleteDeals(
			   @PathVariable Long id) throws Exception{
		   
		   dealService.deleteDeal(id);
		   
		   ApiResponse apiResponse=new ApiResponse();
		   apiResponse.setMessage("Deal delete");
		
		   
		   return new ResponseEntity<>(apiResponse,HttpStatus.ACCEPTED);
		   
	   }
	   
	 
	 
	}
