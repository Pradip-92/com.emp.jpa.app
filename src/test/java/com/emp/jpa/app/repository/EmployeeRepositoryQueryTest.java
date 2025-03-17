package com.emp.jpa.app.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.emp.jpa.app.model.Employee;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryQueryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	@Order(1)
	@DisplayName("Fetch all employees test")
	public void getAllEmployeeNativeQueryTest() {
		// to get employee list we need to save some employee
		Employee emp1 = new Employee("Ganesh", "Patil", "ganesh.patil@gmail.com", "9098998890", "Development", "Pune");
		Employee emp2 = new Employee("Durga", "Durga", "durga.durga@gmail.com", "9098998891", "Management", "Pune");
		Employee emp3 = new Employee("Radha", "Radhe", "radha.radhe@gmail.com", "9098998892", "Sales", "Mumbai");
		List<Employee> empList = new ArrayList<>();
		empList.add(emp1);
		empList.add(emp2);
		empList.add(emp3);
		List<Employee> savedEmpList = employeeRepository.saveAll(empList);

		// Hwew we need to fetch all employee from employee table
		List<Employee> fetchedEmpList = employeeRepository.getAllEmployeeByNativeQuery();

		// finally we will test it with assertion methods
		assertThat(fetchedEmpList).isNotNull();
		assertThat(fetchedEmpList).isNotEmpty();
		assertThat(fetchedEmpList.size()).isEqualTo(savedEmpList.size());

	}

	@Test
	@Order(2)
	@DisplayName("Fetch all employees by city test")
	public void getAllEmployeeByCityNativeQueryTest() {
		// to get employee list we need to save some employee
		Employee emp1 = new Employee("Ganesh", "Patil", "ganesh.patil@gmail.com", "9098998890", "Development", "Pune");
		Employee emp2 = new Employee("Durga", "Durga", "durga.durga@gmail.com", "9098998891", "Management", "Pune");
		Employee emp3 = new Employee("Radha", "Radhe", "radha.radhe@gmail.com", "9098998892", "Sales", "Mumbai");
		List<Employee> empList = new ArrayList<>();
		empList.add(emp1);
		empList.add(emp2);
		empList.add(emp3);
		List<Employee> savedEmpList = employeeRepository.saveAll(empList);

		// Hwew we need to fetch all employee from employee table
		List<Employee> fetchedEmpList = employeeRepository.getAllEmployeeByCityNativeQuery("Pune");

		// finally we will test it with assertion methods
		assertThat(fetchedEmpList).isNotNull();
		assertThat(fetchedEmpList).isNotEmpty();
		assertThat(fetchedEmpList.size()).isEqualTo(2);

	}

}
