package com.demo.request;

import org.springframework.stereotype.Service;

import com.demo.domain.USER_ROLE;
import com.demo.model.User;

import lombok.Data;


@Service
public class LoginOtpRequest {

	private String email;
	private String otp;
	private USER_ROLE role;
	public LoginOtpRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginOtpRequest(String email, String otp, USER_ROLE role) {
		super();
		this.email = email;
		this.otp = otp;
		this.role = role;
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
	public USER_ROLE getRole() {
		return role;
	}
	public void setRole(USER_ROLE role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "LoginOtpRequest [email=" + email + ", otp=" + otp + ", role=" + role + "]";
	}
	
	
}
