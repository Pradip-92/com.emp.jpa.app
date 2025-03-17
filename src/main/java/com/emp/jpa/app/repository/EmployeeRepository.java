package com.emp.jpa.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emp.jpa.app.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Optional<Employee> findByFirstName(String firstName);

	@Query(value = "SELECT * FROM employee_table", nativeQuery = true)
	public List<Employee> getAllEmployeeByNativeQuery();

	@Query(value = "SELECT * FROM employee_table WHERE city =:city", nativeQuery = true)
	public List<Employee> getAllEmployeeByCityNativeQuery(@Param("city") String city);

}
