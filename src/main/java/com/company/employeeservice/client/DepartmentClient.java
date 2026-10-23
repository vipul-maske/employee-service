package com.company.employeeservice.client;

import org.springframework.web.client.RestClient;
import com.company.employeeservice.dto.DepartmentDTO;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.stereotype.Component;

@Component
public class DepartmentClient {
	
	private final String departmentServiceUrl = 
			"http://localhost:8081/api/departments";
	
	private final RestClient restClient = RestClient.create();
	
	public DepartmentDTO getDepartmentById(Long id) {
		return restClient.get()
				.uri(departmentServiceUrl + "/" + id)
				.retrieve().body(DepartmentDTO.class);
	}

}
