package com.emp.jpa.app.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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
//@TestMethodOrder(MethodOrderer.DisplayName.class)
//@TestMethodOrder(MethodOrderer.Alphanumeric.class)
//@TestMethodOrder(MethodOrderer.MethodName.class)
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	private Employee emp;

	@BeforeEach
	private void getEmployee() {
		emp = new Employee();
		emp.setFirstName("Ganesh");
		emp.setLastName("Dev");
		emp.setEmailId("ganesh.gev@gmail.com");
		emp.setMobileNo("9090909900");
		emp.setDept("Management");
		emp.setCity("Pune");
	}

	/**
	 * Here we will provide employee object to and check for the test if given
	 * employee id is same as fetched employee id then test gets pass or else gets
	 * failed
	 */
	@Test
	@Order(1)
	@DisplayName("Save Employee Test")
	public void givenEmployeeObj_whenSaveEmpObj_thenReturnSavedEmpObj() {
		Employee emp = new Employee();
		emp.setFirstName("Ganesh");
		emp.setLastName("Dev");
		emp.setEmailId("ganesh.gev@gmail.com");
		emp.setMobileNo("9090909900");
		emp.setDept("Management");
		emp.setCity("Pune");
		Employee savedEmp = employeeRepository.save(emp);

		assertThat(savedEmp).isNotNull();
	}

	/**
	 * Here we will provide employee id and check for the test if given employee id
	 * is same as fetched employee id then test gets pass or else gets failed
	 */
	@Test
	@Order(2)
	@DisplayName("Fetch Employee By Id Test")
	public void givenEmployeeId_whenFetchEmpObj_thenReturnFetchedEmpObj() {

		Employee savedEmp = employeeRepository.save(emp);

		Optional<Employee> empOptional = employeeRepository.findById(savedEmp.getId());

		assertThat(empOptional.isPresent());
	}

	/**
	 * Here we will provide employee first name and check for the test if given
	 * employee name is same as fetched employee name then test gets pass or else
	 * gets failed
	 */
	@Test
	@Order(3)
	@DisplayName("Fetch Employee By First Name Test")
	public void givenEmployeeName_whenFetchEmpObj_thenReturnFetchEmpObj() {

		Employee savedEmp = employeeRepository.save(emp);
		Optional<Employee> empOptional = employeeRepository.findByFirstName(savedEmp.getFirstName());

		assertThat(empOptional.isPresent());
		assertThat(empOptional.get().getFirstName()).isEqualTo("Ajay");
	}

	/**
	 * Here we will provide wrong employee id and we will test for empty optional
	 * value, Test will get pass only when we will get empty employee optional
	 * object and then we will check
	 * assertThat(empOptional.isEmpty()).isEqualTo(true); i.e empOptional.isEmpty()
	 * returns true then it is pass else test gets failed.
	 * 
	 */
	@Test
	@Order(4)
	@DisplayName("Fetch Employee By Id Test For Null Object")
	public void givenEmployeeId_whenFetchEmpObj_thenReturnFetchNullEmpObj() {

		Employee savedEmp = employeeRepository.save(emp);
		long id = 101;
		Optional<Employee> empOptional = employeeRepository.findById(id);

		assertThat(empOptional.isEmpty()).isEqualTo(true);
	}

	@Test
	@Order(5)
	@DisplayName("Update Employee By Id Test")
	public void givenEmployeeObj_WhenUpdateEmployeeObj_ThenReturnUpdatedEmployeeObj() {
		// Here we will save employee first
		Employee savedEmp = employeeRepository.save(emp);
		// After employee gets saved successfully we will update it's city from pune to
		// mumbai
		savedEmp.setCity("Mumbai");
		// Now after city gets updated again we will save employee so it will get update
		// with new information
		Employee updatedEmp = employeeRepository.save(savedEmp);

		assertThat(updatedEmp).isNotNull();
		assertThat(updatedEmp.getCity()).isEqualTo("Mumbai");
	}

	@Test
	@Order(6)
	@DisplayName("Delete Employee By Id Test")
	public void givenEmployeeObj_WhenDeleteEmployeeObj_ThenReturnDeletedEmployeeObj() {
		// Here we will save employee first
		Employee savedEmp = employeeRepository.save(emp);
		// After employee gets saved successfully we will delete the same employee using
		// id
		employeeRepository.deleteById(savedEmp.getId());

		// After successfully delete we will try to fetch it by id and check
		Optional<Employee> empOptional = employeeRepository.findById(savedEmp.getId());
		assertThat(empOptional.isEmpty()).isEqualTo(true);
	}

}
