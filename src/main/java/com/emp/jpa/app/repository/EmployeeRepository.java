package com.emp.jpa.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.jpa.app.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Optional<Employee> findByFirstName(String firstName);

}
