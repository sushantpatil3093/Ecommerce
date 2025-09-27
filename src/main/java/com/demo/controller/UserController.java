package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.USER_ROLE;
import com.demo.model.User;
import com.demo.response.AuthResponse;
import com.demo.response.SignupRequest;
import com.demo.service.impl.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users/profile")
	public ResponseEntity<User>createUserHandler(@RequestHeader("Authorization")String jwt)throws Exception{
		
		User user =userService.findUserByJwtToken(jwt);

	
		return ResponseEntity.ok(user);
}
}