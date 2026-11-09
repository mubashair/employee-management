package com.portfolio.empmgt.dto;

public class EmployeeResponseDTO {
	private Long employeeId;
	private String employeeName;
	private String email;
	private String department;
	private double salary;
	
	public EmployeeResponseDTO() {
		
	}
	
	public EmployeeResponseDTO(Long employeeId, String employeeName, String email, String department, double salary) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.email = email;
		this.department = department;
		this.salary = salary;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
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
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
}
