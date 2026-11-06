package com.portfolio.empmgt.exception;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String message) {
		super(message);
		
	}
	/*Purpose: Used when an employee does not exist.

   For example, requesting employee ID 1000 when that employee is absent from the database.

   Instead of returning a confusing error, your application can return HTTP 404 Not Found.*/
	
}
