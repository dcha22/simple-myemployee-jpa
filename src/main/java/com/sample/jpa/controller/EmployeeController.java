package com.sample.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.sample.jpa.model.Employee;

//@RequestMapping("/techTraining")
@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeJpaRepo employeeRepo;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return  employeeRepo.findAll();
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable long id) {
		Optional<Employee> _employee =  employeeRepo.findById(id);
		return _employee.get();
	}
	
	@PostMapping("/employee")
	public void addEmployee(@RequestBody Employee employee) {
		employeeRepo.save(employee);
	}
	
	// e.g. http://localhost:8080/employee/1
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable long id) {
		Employee _employee = employeeRepo.findById(id).get();
		employeeRepo.delete(_employee);
		employeeRepo.flush();
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
}
