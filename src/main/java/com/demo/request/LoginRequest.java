package com.demo.request;

import org.springframework.stereotype.Service;

import lombok.Data;



public class LoginRequest {

	private String email;
	private String otp;
	public LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginRequest(String email, String otp) {
		super();
		this.email = email;
		this.otp = otp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "LoginRequest [email=" + email + ", otp=" + otp + "]";
	}
	
	
	
	
}
