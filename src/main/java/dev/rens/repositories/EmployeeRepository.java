package dev.rens.repositories;

import java.util.List;

import dev.rens.models.Employee;

public interface EmployeeRepository {

	Employee createEmployee(Employee e);
	Employee getEmployee(int id);
	List<Employee> getAllEmployees();
	List<Employee> GetAllBencos();
	
	Employee updateEmployee(Employee update);
	boolean deleteEmployee(int id);
}
