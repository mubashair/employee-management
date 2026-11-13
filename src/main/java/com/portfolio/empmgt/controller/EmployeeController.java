package com.portfolio.empmgt.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.empmgt.DTO.EmployeeRequestDTO;
import com.portfolio.empmgt.DTO.EmployeeResponseDTO;
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
	public ResponseEntity<Page<EmployeeResponseDTO>> getAllEmployees(
			@RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "employeeId")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String sortDir){

        // Validate pagination parameters
        if (page < 0) {
            throw new IllegalArgumentException(
                    "Page number cannot be negative"
            );
        }

        if (size < 1 || size > 100) {
            throw new IllegalArgumentException(
                    "Page size must be between 1 and 100"
            );
        }
       // Allow only known entity properties for sorting
        if (!java.util.Set.of(
                "employeeId",
                "employeeName",
                "email",
                "department",
                "salary"
        ).contains(sortBy)) {

            throw new IllegalArgumentException(
                    "Invalid sort field: " + sortBy
            );
        }
        // Validate sort direction
        if (!sortDir.equalsIgnoreCase("asc")
                && !sortDir.equalsIgnoreCase("desc")) {

            throw new IllegalArgumentException(
                    "Sort direction must be asc or desc"
            );
        }

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<EmployeeResponseDTO> employees =
                employeeService.getAllEmployees(pageable);

        return ResponseEntity.ok(employees);
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
