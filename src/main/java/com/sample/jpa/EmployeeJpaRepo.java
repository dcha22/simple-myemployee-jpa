package com.sample.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.jpa.model.Employee;

public interface EmployeeJpaRepo extends JpaRepository<Employee, Long> {

}
