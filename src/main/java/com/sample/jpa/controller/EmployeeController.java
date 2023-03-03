package com.sample.jpa.controller;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.jpa.EmployeeJpaRepo;
import com.sample.jpa.dto.EmployeeDTO;
import com.sample.jpa.exception.ResourceNotFoundException;
import com.sample.jpa.model.Employee;
import com.sample.jpa.model.EmptyJsonResponse;
import com.sample.jpa.model.OkJsonResponse;

//@RequestMapping("/techTraining")
@RestController
public class EmployeeController {

	@Autowired
	EmployeeJpaRepo employeeRepo;

	ModelMapper mapper = new ModelMapper();

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> empLst = new ArrayList<>();
		employeeRepo.findAll().forEach(empLst:: add);
		
		if(!empLst.isEmpty()) {
			return new ResponseEntity<>(empLst, HttpStatus.OK);
		} else {
			//return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
			// This will return empty json in the response
			return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
		}
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
		Optional<Employee> _employee =  Optional.of(employeeRepo.findById(id)
				.orElseThrow(
						() -> new ResourceNotFoundException("Employee with ID: "+ id +" does not exist")));
		return new ResponseEntity<>(_employee.get(), HttpStatus.OK);
	}

	@PostMapping("/employee")
	public ResponseEntity<OkJsonResponse> addEmployee2(@RequestBody EmployeeDTO empDTO) {
		EmployeeDTO _empDTO = new EmployeeDTO(empDTO.getName());
		
		employeeRepo.save(convertToEntity(_empDTO));
		
		String status = "OK";
		return new ResponseEntity<>(new OkJsonResponse(status), HttpStatus.OK);
	}
	
	// e.g. http://localhost:8080/employee/1
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<OkJsonResponse> deleteEmployee(@PathVariable long id) {
		Employee _employee = employeeRepo.findById(id).get();
		employeeRepo.delete(_employee);
		employeeRepo.flush();
		String status = "OK";
		return new ResponseEntity<>(new OkJsonResponse(status), HttpStatus.OK);
	}

	// Same method as delete above...pass Id as a request parameter
	// e.g. http://localhost:8080/employee?id=1
	@DeleteMapping("/employee2")
	public void deleteEmployee2(@RequestParam long id) {
		Employee _employee = employeeRepo.findById(id).get();
		employeeRepo.delete(_employee);
		//employeRepo.flush();
	}

	// Delete All Employees
	@DeleteMapping("/employees")
	public void deleteAllEmployees() {
		employeeRepo.deleteAll();
		employeeRepo.flush();
	}

	private EmployeeDTO convertToDTO(Employee emp) {
		EmployeeDTO empDTO = mapper.map(emp, EmployeeDTO.class);
		return empDTO;
	}

	private Employee convertToEntity(EmployeeDTO empDTO) {
		Employee emp = mapper.map(empDTO, Employee.class);
		return emp;
	}

	private void mapFields(Employee emp, EmployeeDTO empDTO) {
		TypeMap<EmployeeDTO, Employee> propertyMapper = mapper.createTypeMap(EmployeeDTO.class, Employee.class);
		propertyMapper.addMapping(EmployeeDTO::getLocalTimeDateFromCreationDate, Employee::setCreationDate);
		System.out.println(emp.toString());
	}
}
