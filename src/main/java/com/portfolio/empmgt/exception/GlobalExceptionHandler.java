package com.portfolio.empmgt.exception;
//Represents an HTTP response containing status code and response body.
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	//Handle Employee not found exception
	 // Runs when ResourceNotFoundException is thrown.
    // Example: Employee ID 100 does not exist.
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request){
		return null;
	}
}
