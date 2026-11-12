  package com.portfolio.empmgt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.empmgt.DTO.EmployeeRequestDTO;
import com.portfolio.empmgt.DTO.EmployeeResponseDTO;
import com.portfolio.empmgt.entity.Employee;
import com.portfolio.empmgt.exception.DuplicateEmailException;
import com.portfolio.empmgt.exception.ResourceNotFoundException;
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
	private EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	/**
     * Maps an Employee database entity into a clean, read-only Response DTO.
     * This keeps sensitive database structures hidden from public API consumers.
     */
	private EmployeeResponseDTO convertToDTO(Employee employee) {
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
	// Helper: Convert Request DTO into a new Database Entity
	private Employee convertToEntity(EmployeeRequestDTO dto) {
		Employee employee = new Employee();
		
		employee.setEmployeeName(dto.getEmployeeName());
		employee.setEmail(dto.getEmail());
		employee.setDepartment(dto.getDepartment());
		employee.setSalary(dto.getSalary());
		return employee;
	}
	/**
     * Registers a new employee in the system.
     * Validates that the requested email address is globally unique.
     * 
     * @param dto Data payload containing new employee details
     * @return Transformed response object containing database-generated IDs
     * @throws DuplicateEmailException if the email is already registered
     */
	
	//Create Employee
	public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
		
		//Enforce the unique email business rule
		if (employeeRepository.existsByEmail(dto.getEmail())) {
			throw new DuplicateEmailException(
					"An employee with this email already exists:" +  dto.getEmail());
		}//end if
		
		//Convert the request dto to entity
		Employee employee = convertToEntity(dto);
		//Save the entity to the database
		Employee savedEmployee = employeeRepository.save(employee);
		//Convert the saved employee entity back to response dto and return it
		return convertToDTO(savedEmployee);
	}
	//Retrieve all employees
	// READ: Get all employees in paginated chunks
	@Transactional(readOnly = true)
	public Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable) {
		return employeeRepository.findAll(pageable)
				.map(this::convertToDTO);// Converts each Employee entity in the page to an EmployeeResponseDTO
	}
	// READ: Get employee by ID (readOnly transaction optimizes performance)
	//Retrieve one employee
	@Transactional(readOnly=true)
	public EmployeeResponseDTO getEmployeeById(Long id) {
		// Find employee by ID or throw a 404-style custom exception if missing
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()->new ResponseStatusException(
						HttpStatus.NOT_FOUND, 
						"Employee not found with ID: " + id));
		return convertToDTO(employee);
	}
	//Update an existing details
	public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto) {
		//Check if the employee exists first
		Employee employee = employeeRepository.findById(id).orElseThrow(()->
		                   new ResourceNotFoundException(
		                		   "Employee not found with ID:"+id));
		
		// Check if another employee (with a different ID) is already using this email
		boolean existsByEmailAndEmployeeIdNot = employeeRepository.existsByEmailAndEmployeeIdNot(dto.getEmail(), id);
		if(existsByEmailAndEmployeeIdNot) {
			throw new DuplicateEmailException(
					"Another employee already using this email: "+dto.getEmail());
		}
		employee.setEmployeeName(
				dto.getEmployeeName());
		employee.setDepartment(
				dto.getDepartment());
		employee.setEmail(
				dto.getEmail());
		employee.setSalary(
				dto.getSalary());
		Employee updatedEmployee = employeeRepository.save(employee);
		return convertToDTO(updatedEmployee);
	}
	//Delete an employee by ID
	public void deleteEmployee(Long id) {
		//Find the employee first throw exception if not found
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(
						"Employee not found with ID:"+id));
		employeeRepository.delete(employee);
	}
}
