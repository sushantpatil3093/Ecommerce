package com.demo.model;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class BusinessDetails {

	   
	private String businessName;
	private String businessEmail;
	private String businessMobile;
	private String logo;
	private String banner;
	
	
	public BusinessDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BusinessDetails(String businessName, String businessEmail, String businessMobile, String logo,
			String banner) {
		super();
		this.businessName = businessName;
		this.businessEmail = businessEmail;
		this.businessMobile = businessMobile;
		this.logo = logo;
		this.banner = banner;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessEmail() {
		return businessEmail;
	}
	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}
	public String getBusinessMobile() {
		return businessMobile;
	}
	public void setBusinessMobile(String businessMobile) {
		this.businessMobile = businessMobile;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	@Override
	public String toString() {
		return "BusinessDetails [businessName=" + businessName + ", businessEmail=" + businessEmail
				+ ", businessMobile=" + businessMobile + ", logo=" + logo + ", banner=" + banner + "]";
	}
	
}
