package com.portfolio.empmgt.exception;

public class DuplicateEmailException extends RuntimeException {

	public DuplicateEmailException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	/*Purpose: Prevents duplicate email addresses.

    For example, if ali@example.com already belongs to an employee, 
    trying to register another employee with that email should return HTTP 409 Conflict.

    The database's unique constraint remains important 
     because application-level checks alone cannot prevent every concurrent duplicate request.*/
	

}
