package com.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {

	 @ExceptionHandler(ProductException.class)
	    public ResponseEntity<ErrorDetails> handleProductException(ProductException ex) {
	        ErrorDetails error = new ErrorDetails(
	                LocalDateTime.now(),
	                ex.getMessage(),
	                "Product Error"
	        );
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(SellerException.class)
	    public ResponseEntity<ErrorDetails> handleSellerException(SellerException ex) {
	        ErrorDetails error = new ErrorDetails(
	                LocalDateTime.now(),
	                ex.getMessage(),
	                "Seller Error"
	        );
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(Exception.class) // fallback for other exceptions
	    public ResponseEntity<ErrorDetails> handleGeneralException(Exception ex) {
	        ErrorDetails error = new ErrorDetails(
	                LocalDateTime.now(),
	                ex.getMessage(),
	                "Internal Server Error"
	        );
	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}