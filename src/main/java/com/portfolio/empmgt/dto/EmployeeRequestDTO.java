package com.portfolio.empmgt.dto;

import jakarta.persistence.Column;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class EmployeeRequestDTO {
	
	@NotBlank(message = "Employee name is required")
	private String employeeName;
	
	@Email(message = "Enter a valid email address")
	@NotBlank(message = "Email is required")
	@Column(unique = true, nullable = false)
	private String email;
	
	private String department;
	
	@NotNull(message = "Salary is required")
	@PositiveOrZero(message = "Salary cannot be negative")
	private Double salary;

	//Getters and Setters
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
	
	
}
