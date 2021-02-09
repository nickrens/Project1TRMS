package dev.rens.test;

import java.util.ArrayList;
import java.util.List;

import dev.rens.models.DepartmentHead;
import dev.rens.models.Employee;
import dev.rens.services.DepartmentHeadService;
import dev.rens.services.EmployeeService;

public class DAOTests {

	public static void main(String[] args) {
		EmployeeService eserv = new EmployeeService();
		DepartmentHeadService dhserv = new DepartmentHeadService();
		Employee employee;
		
		employee = eserv.getEmployee(8);
		System.out.println(employee);
		
		employee.setEmail("nrens@test.net");
		eserv.updateEmployee(employee);
		
		employee = eserv.getEmployee(8);
		System.out.println(employee);
		
		List<Employee> list = new ArrayList<Employee>();
		
		list = eserv.GetAllBencos();
		System.out.println("---------------------------------------------------");
		for(Employee person: list) {
			System.out.println(person);
		}
		
		list = eserv.getAllEmployees();
		System.out.println("---------------------------------------------------");
		for(Employee person: list) {
			System.out.println(person);
		}
		
		System.out.println("-------------------------------------------------------------------------------");
		String department = "";
		List<DepartmentHead> dhlist = dhserv.getAllDepartmentHeads();
		for(DepartmentHead i: dhlist) {
			System.out.println(i);
			department = i.getDepartment();
		}
		
		DepartmentHead dh = dhserv.findDepartmentHead(department);
		System.out.println(dh);
	}
}
