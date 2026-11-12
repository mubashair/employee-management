package com.portfolio.empmgt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.empmgt.DTO.EmployeeRequestDTO;
import com.portfolio.empmgt.DTO.EmployeeResponseDTO;
import com.portfolio.empmgt.entity.Employee;
import com.portfolio.empmgt.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	private final EmployeeService employeeService;
	
	//Constructor injection
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//POST /api/employees
	@PostMapping
	public ResponseEntity<EmployeeResponseDTO>  createEmployee(@Valid @RequestBody EmployeeRequestDTO dto) {
		EmployeeResponseDTO responseDTO = employeeService.createEmployee(dto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(responseDTO);
	}
	//GET /api/employees
	// GET all employees with pagination and sorting
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	// GET employee by ID
	//Get /api/employees/{id}
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
		return ResponseEntity.ok(
				employeeService.getEmployeeById(id));
	}
	//PUT /api/employees/{id}
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> updateEmployee(
			@PathVariable Long id, 
			@Valid @RequestBody EmployeeRequestDTO employee) {
		
		return ResponseEntity.ok(
				employeeService.updateEmployee(id, employee));
	}
	//Delete /api/employees/{id}
	 // DELETE employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }
}
