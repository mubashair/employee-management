package com.portfolio.empmgt.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiErrorResponse {
	private LocalDateTime timeStamp; // Date and time when the error occurred.
	private int status;//Http status code 404, 200, 500
	
	//Short description of Http error such as "Bad Request" or "Not Found"
	private String error;
	//Detailed explanation of error
	//Example employee not foud with id 500
	private String message;
	//API endpoint where error occurred
	//Example api/employees/100
	private String path;
	//Stores Validation errors for individual fields
	// Example:
    // "employeeName" -> "Employee name is required"
    // "email"        -> "Email must be valid"
	private Map<String, String> validationErrors;
	// Default constructor.
    // Allows an empty ApiErrorResponse object to be created.
	public ApiErrorResponse() {
		
	}
	// Parameterized constructor.
    // Creates an error response with all fields initialized.
	public ApiErrorResponse(LocalDateTime timeStamp, int status, String error, String message, String path,
			Map<String, String> validationErrors) {
		this.timeStamp = timeStamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.validationErrors = validationErrors;
	}
	//Return the timestamp
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	//Update the timestamp
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Map<String, String> getValidationErrors() {
		return validationErrors;
	}
	public void setValidationErrors(Map<String, String> validationErrors) {
		this.validationErrors = validationErrors;
	}
	
	
	
}
