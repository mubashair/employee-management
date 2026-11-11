  package com.portfolio.empmgt.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.empmgt.DTO.EmployeeResponseDTO;
import com.portfolio.empmgt.entity.Employee;
import com.portfolio.empmgt.repo.EmployeeRepository;

/**
 * Service class that handles the core business logic for Employee management.
 * It serves as an intermediary layer between the REST Controller and the Database Repository.
 */
@Service
//Manages database transactions automatically for methods in this class
@Transactional
public class EmployeeService {
	private final EmployeeRepository employeeRepository;
	
	//Constructor Injection
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	/**
     * Maps an Employee database entity into a clean, read-only Response DTO.
     * This keeps sensitive database structures hidden from public API consumers.
     */
	public EmployeeResponseDTO convertToDTO(Employee employee) {
		return new EmployeeResponseDTO(
				employee.getEmployeeId(),
				employee.getEmployeeName(),
				employee.getEmail(),
				employee.getDepartment(),
				employee.getSalary()
				);
	}
	/**
     * Maps an incoming Request DTO into a fresh Employee database entity.
     * This prepares user input data for persistent storage in the database.
     */
	public Employee convertToEntity(EmployeeResponseDTO dto) {
		Employee employee = new Employee();
		
		return employee;
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
