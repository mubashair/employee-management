package com.portfolio.empmgt.exception;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
//Represents an HTTP response containing status code and response body.
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	//Handle DTO validation Errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidationErrors(
			MethodArgumentNotValidException ex, HttpServletRequest request){
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult()
		      .getFieldErrors()
		      .forEach(fielError->
		               errors.putIfAbsent(
		            		   fielError.getField(),  
		            		   fielError.getDefaultMessage()
		            		   )
		               );
		ApiErrorResponse error = new ApiErrorResponse(
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value(),
				"Validation Failed",
				"Please correct the invalid request fields",
				request.getRequestURI(),
				errors
				);
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(error);
	}
	//Handle database constraints violations
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiErrorResponse> handleDatabseError(
			DataIntegrityViolationException ex,
			HttpServletRequest request){
		
		ApiErrorResponse error = new ApiErrorResponse(
				LocalDateTime.now(),
				HttpStatus.CONFLICT.value(),
				"Conflict",
				"The request violates a database constraint."
				           + "Check whether the email already exists.",
				request.getRequestURI(),
				null
				);
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(error);
	}
	//Handle unexpected exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleGeneralError(
			Exception ex, HttpServletRequest request){
		ApiErrorResponse error = new ApiErrorResponse(
				LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal server error",
				"An unexpected error occured",
				request.getRequestURI(),
				null
				);
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(error);
		
		
	}
}
