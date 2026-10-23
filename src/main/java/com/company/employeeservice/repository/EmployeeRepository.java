package com.company.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.employeeservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
