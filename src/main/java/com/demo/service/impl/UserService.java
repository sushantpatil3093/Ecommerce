package com.demo.service.impl;

import com.demo.model.User;

public interface UserService {

	User findUserByJwtToken(String jwt) throws Exception;
	User findUserByEmail(String email) throws Exception;
	
	
}
