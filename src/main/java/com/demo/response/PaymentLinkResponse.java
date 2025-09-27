package com.demo.response;

import lombok.Data;

@Data
public class PaymentLinkResponse {

	private String payment_url;
	private String payment_id;
	public PaymentLinkResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentLinkResponse(String payment_url, String payment_id) {
		super();
		this.payment_url = payment_url;
		this.payment_id = payment_id;
	}
	public String getPayment_url() {
		return payment_url;
	}
	public void setPayment_url(String payment_url) {
		this.payment_url = payment_url;
	}
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	
	
}
