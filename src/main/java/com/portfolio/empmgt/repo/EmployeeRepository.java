
package com.portfolio.empmgt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.empmgt.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	boolean existsByEmail(String email);//Check whether an email already exists
	//Check for a duplicate email belonging to another employee
	boolean existsByEmailAndEmployeeIdNot(String email, Long employeeId);
	
	/*The last method is particularly useful when updating an employee. 
	
	An employee should be allowed to keep their own email address, but they must not use another employee's email.

	boolean existsByEmail(String email);
	boolean existsByEmailAndEmployeIdNot(String email, Long employeeId);
	
/*Explanation
Because this interface extends JpaRepository<Employee, Long>, Spring Data JPA provides methods such as:
Method	Purpose
save()	Insert or update an employee
findById()	Find an employee by ID
findAll()	Retrieve all employees
deleteById()	Delete an employee
existsByEmail()	Check whether an email already exists
existsByEmailAndEmployeeIdNot()	Check for a duplicate email belonging to another employee


The last method is particularly useful when updating an employee. An employee should be allowed to keep their own email address, but they must not use another employee's email.

JpaRepository also supports pagination and sorting without requiring you to write SQL queries manually.*/


}
