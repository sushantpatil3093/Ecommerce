package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.config.JwtProvider;
import com.demo.model.User;
import com.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	   @Autowired
       private  JwtProvider jwtProvider;
	   
	   
	   
	@Override
	public User findUserByJwtToken(String jwt) throws Exception {
		String email=jwtProvider.getEmailFromToken(jwt);
	
		return this.findUserByEmail(email);
	}
	@Override
	public User findUserByEmail(String email) throws Exception {
	
	User user=userRepository.findByEmail(email);
		if(user==null) {
			throw new Exception("user not found with email "+email);
		
		
		//return this.findUserByEmail(email);
	}

	return user;
	
	
	}
}

	

