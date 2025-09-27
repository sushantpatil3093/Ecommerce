package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.response.ApiResponse;

@RestController
public class HomeController {

	@GetMapping
	public ApiResponse HomeControllerHandler() {
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("welcome to ecommerce vendor system");
		
		return apiResponse;
	}
}
