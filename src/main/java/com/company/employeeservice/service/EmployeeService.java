package com.company.employeeservice.service;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.company.employeeservice.repository.EmployeeRepository;
import com.company.employeeservice.entity.Employee;
import com.company.employeeservice.client.DepartmentClient;
import com.company.employeeservice.dto.EmployeeResponseDTO;
import com.company.employeeservice.dto.DepartmentDTO;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;
	private final DepartmentClient departmentClient;

	public EmployeeService(EmployeeRepository employeeRepository, DepartmentClient departmentClient) {
		this.employeeRepository = employeeRepository;
		this.departmentClient = departmentClient;
	}

	private EmployeeResponseDTO convertToDTO(Employee employee) {
		EmployeeResponseDTO dto = new EmployeeResponseDTO();

		dto.setId(employee.getId());
		dto.setName(employee.getName());
		dto.setEmail(employee.getEmail());
		dto.setDepartmentId(employee.getDepartmentId());
		if (employee.getDepartmentId() != null) {
		    DepartmentDTO department = departmentClient.getDepartmentById(
		        Long.parseLong(employee.getDepartmentId()));

		    dto.setDepartmentName(department.getName());
		    dto.setDepartmentLocation(department.getLocation());
		}
		return dto;
	}

	public EmployeeResponseDTO addEmployee(Employee employee) {
	    Employee savedEmployee = employeeRepository.save(employee);
	    return convertToDTO(savedEmployee);
	}

	public List<EmployeeResponseDTO> getAllEmployees() {
		return employeeRepository.findAll().stream().map(this::convertToDTO).toList();
	}

	public EmployeeResponseDTO getEmployeeById(Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id));
		return convertToDTO(employee);
	}

	public EmployeeResponseDTO updateEmployee(Long id, Employee updateEmployee) {
	    Employee existingEmployee = employeeRepository.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(
	                    HttpStatus.NOT_FOUND,
	                    "Employee not found with id: " + id));

	    existingEmployee.setName(updateEmployee.getName());
	    existingEmployee.setEmail(updateEmployee.getEmail());
	    existingEmployee.setDepartmentId(updateEmployee.getDepartmentId());

	    Employee savedEmployee = employeeRepository.save(existingEmployee);
	    return convertToDTO(savedEmployee);
	}

	public void deleteEmployee(Long id) {
		getEmployeeById(id);
		employeeRepository.deleteById(id);
	}

}
