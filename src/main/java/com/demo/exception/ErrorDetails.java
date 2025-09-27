package com.demo.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ErrorDetails {

	private String error;
	private String details;
	private LocalDateTime timestamp;
	public ErrorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorDetails(LocalDateTime timestamp, String details, String error) {
		super();
		this.error = error;
		this.details = details;
		this.timestamp = timestamp;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "ErrorDetails [error=" + error + ", details=" + details + ", timestamp=" + timestamp + "]";
	}
	
	
	
	
}
