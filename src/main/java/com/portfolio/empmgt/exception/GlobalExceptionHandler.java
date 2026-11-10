package com.portfolio.empmgt.exception;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
//Represents an HTTP response containing status code and response body.
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.portfolio.empmgt.DTO.ApiErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	//Handle Employee not found exception
	 // Runs when ResourceNotFoundException is thrown.
    // Example: Employee ID 100 does not exist.
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request){
		
		ApiErrorResponse error = new ApiErrorResponse(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(),
				"Not Found",
				ex.getMessage(),
				request.getRequestURI(),
				null
				);
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(error);
	}
	//Handle Duplicate Email
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<ApiErrorResponse> handleDuplicateEmail(ResourceNotFoundException ex, HttpServletRequest request){
		
		ApiErrorResponse error = new ApiErrorResponse(
				LocalDateTime.now(),
				HttpStatus.CONFLICT.value(),
				"Conflict",
				ex.getMessage(),
				request.getRequestURI(),
				null
				);
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(error);
	}
}
