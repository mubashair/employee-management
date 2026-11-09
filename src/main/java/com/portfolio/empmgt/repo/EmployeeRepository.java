
package com.portfolio.empmgt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.empmgt.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	boolean existsByEmail(String email);//Check whether an email already exists
	//Check for a duplicate email belonging to another employee
	boolean existsByEmailAndEmployeeIdNot(String email, Long employeeId);
	
	/*The last method is particularly useful when updating an employee. 
	
	An employee should be allowed to keep their own email address, but they must not use another employee's email.

    JpaRepository also supports pagination and sorting without requiring you to write SQL queries manually.*/
}
