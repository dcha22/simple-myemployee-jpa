package com.sample.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.jpa.dto.EmployeeDTO;
import com.sample.jpa.model.Employee;

//@SpringBootTest
public class TestEmployeeDTO {
	ModelMapper mapper;
	
	@BeforeEach
	public void setup() {
		this.mapper = new ModelMapper();
	}
	
	@Test
	public void whenMapEmployeeWithExactMatch_ThenConvertToDTO() {
		Employee emp = new Employee(1, "Basant Singh");
		EmployeeDTO empDTO = this.mapper.map(emp, EmployeeDTO.class);
		
		Assertions.assertEquals(emp.getId(), empDTO.getId());
		Assertions.assertEquals(emp.getName(), empDTO.getName());
	}
}
