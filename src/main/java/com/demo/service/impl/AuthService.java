package com.demo.service.impl;

import com.demo.domain.USER_ROLE;
import com.demo.request.LoginRequest;
import com.demo.response.AuthResponse;
import com.demo.response.SignupRequest;

public interface AuthService {

	void sentLoginOtp(String email,USER_ROLE role) throws Exception;
	
	String createUser(SignupRequest req) throws Exception;
	
	AuthResponse signing(LoginRequest req) throws Exception;
	
}
