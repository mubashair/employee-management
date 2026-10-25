package com.portfolio.empmgt.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.empmgt.entity.Employee;
import com.portfolio.empmgt.repo.EmployeeRepository;

@Service
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	
	//Constructor Injection
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	//Create Employee
	public Employee createEmployee(Employee employee) {
		employee.setEmployeeId(null);
		Employee savedEmployee = employeeRepository.save(employee);
		return savedEmployee;
	}
	//Retrieve all employees
	public List<Employee> getAllEmployees() {
		List<Employee> findAll = employeeRepository.findAll();
		return findAll;
	}
	//Retrieve one employee
	public Employee getEmployeeById(Long id) {
		
		return employeeRepository.findById(id)
				.orElseThrow(()->new ResponseStatusException(
						HttpStatus.NOT_FOUND, 
						"Employee not found with ID: " + id));
	}
	//Update an employee
	public Employee updateEmployee(Long id, Employee updateEmployee) {
		Employee existingEmployee = getEmployeeById(id);
		
		existingEmployee.setEmployeeName(
				updateEmployee.getEmployeeName());
		existingEmployee.setDepartment(
				updateEmployee.getDepartment());
		existingEmployee.setEmail(
				updateEmployee.getEmail());
		existingEmployee.setSalary(
				updateEmployee.getSalary());
		return employeeRepository.save(existingEmployee);
	}
	//Delete an employee
	public void deleteEmployee(Long id) {
		Employee employee = getEmployeeById(id);
		employeeRepository.delete(employee);
	}
}
