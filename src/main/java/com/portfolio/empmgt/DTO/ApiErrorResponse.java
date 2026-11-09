package com.portfolio.empmgt.DTO;

import java.time.LocalDateTime;
import java.util.Map;
/**
 * Data Transfer Object (DTO) representing a standardized API error response.
 * Used to send structured error details back to the client when exceptions occur.
 */
public class ApiErrorResponse {
	 // --- Fields (Payload Data) ---

    // The exact date and time when the error occurred
	private LocalDateTime timeStamp;
	// The HTTP status code (e.g., 400, 404, 500)
	private int status;
	// The short reason phrase or HTTP error name (e.g., "Bad Request", "Not Found")
	private String error;
	// A user-friendly message explaining why the error occurred
	private String message;
	// The specific API endpoint or URL path where the error happened (e.g., "/api/employees/1")
	private String path;
	// Stores field-specific validation errors (e.g., "email" -> "Email is invalid")
    // This is particularly useful for handling HTTP 400 Bad Request form validation failures.
	private Map<String, String> validationErrors;
	// --- Constructors ---

    /**
     * Default no-argument constructor.
     * Required for JSON serialization/deserialization frameworks like Jackson.
     */
	public ApiErrorResponse() {
		
	}
	 /**
     * All-arguments constructor.
     * Allows quick instantiation of the error response payload with all fields populated.
     */

	public ApiErrorResponse(LocalDateTime timeStamp, int status, String error, String message, String path,
			Map<String, String> validationErrors) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.validationErrors = validationErrors;
	}
    // --- Getters and Setters ---
    // Standard boilerplate methods required to read from and write to the private fields.
	 public LocalDateTime getTimeStamp() {
		 return timeStamp;
	 }
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
