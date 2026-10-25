
package com.portfolio.empmgt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.empmgt.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
