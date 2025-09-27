package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.USER_ROLE;
import com.demo.model.User;
import com.demo.model.VerificationCode;
import com.demo.repository.UserRepository;
import com.demo.request.LoginOtpRequest;
import com.demo.request.LoginRequest;
import com.demo.response.ApiResponse;
import com.demo.response.AuthResponse;
import com.demo.response.SignupRequest;
import com.demo.service.impl.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {


	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/signup") 
	public ResponseEntity<AuthResponse>createUserHandler(@RequestBody SignupRequest req) throws Exception{
		
		String jwt=authService.createUser(req);
		
		AuthResponse res=new AuthResponse();
		res.setJwt(jwt);
		res.setMessage("register success");
		res.setRole(USER_ROLE.ROLE_CUSTOMER);
		
		return ResponseEntity.ok(res);
	}
	@PostMapping("/sent/login-signup-otp") 
	public ResponseEntity<ApiResponse>sentOtpHandler(@RequestBody LoginOtpRequest  req) throws Exception{
		
		authService.sentLoginOtp(req.getEmail(),req.getRole());
		
	ApiResponse res=new ApiResponse();
	
	res.setMessage("otp sent successfully");
		
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/signing") 
	public ResponseEntity<AuthResponse>loginHandler(@RequestBody LoginRequest req) throws Exception{
		
	  AuthResponse authResponse=authService.signing(req);
	  
   
		
		return ResponseEntity.ok(authResponse);
}
}