package com.demo.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SignupRequest {

	private String email;
	private String fullName;
	private String otp;
	public SignupRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SignupRequest(String email, String fullName, String otp) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.otp = otp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "SignupRequest [email=" + email + ", fullName=" + fullName + ", otp=" + otp + "]";
	}
	
	
	
}
