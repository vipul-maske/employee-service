package com.company.employeeservice.controller;

import java.util.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.company.employeeservice.service.EmployeeService;
import com.company.employeeservice.entity.Employee;
import com.company.employeeservice.dto.EmployeeResponseDTO;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping
	public EmployeeResponseDTO addEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@GetMapping
	public List<EmployeeResponseDTO> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/{id}")
	public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) {
		return employeeService.getEmployeeById(id);
	}

	@PutMapping("/{id}")
	public EmployeeResponseDTO updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		return employeeService.updateEmployee(id, employee);
	}

	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return "Employee delete successfully";
	}

}
