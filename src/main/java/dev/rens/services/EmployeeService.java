package dev.rens.services;

import java.util.List;

import dev.rens.models.Employee;
import dev.rens.repositories.EmployeeRepositoryImpl;

public class EmployeeService {

	public EmployeeRepositoryImpl erepo = new EmployeeRepositoryImpl();
	
	public Employee createEmployee(Employee e) {
		return erepo.createEmployee(e);
	}
	public Employee getEmployee(int id) {
		return erepo.getEmployee(id);
	}
	public Employee getLatestEmployee() {
		return erepo.getLatestEmployee();
	}
	public List<Employee> getAllEmployees() {
		return erepo.getAllEmployees();
	}
	public List<Employee> GetAllBencos() {
		return erepo.GetAllBencos();
	}
	
	public Employee updateEmployee(Employee update) {
		return erepo.updateEmployee(update);
	}
	public boolean deleteEmployee(int id) {
		return erepo.deleteEmployee(id);
	}
	
	public Employee updateEmployeeReimbursementRemaining(Employee update) {
		return erepo.updateEmployeeReimbursementReamaining(update);
	}
}
