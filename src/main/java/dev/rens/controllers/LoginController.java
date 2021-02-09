package dev.rens.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import dev.rens.models.Employee;
import dev.rens.services.EmployeeService;

public class LoginController {
	
	public static Gson gson = new Gson();
	public static EmployeeService eserv = new EmployeeService();

	public static void login(HttpServletRequest request, HttpServletResponse response) throws JsonIOException, JsonSyntaxException, IOException {
		Employee employee = gson.fromJson(request.getReader(), Employee.class);
		
		List<Employee> list = new ArrayList<Employee>();
		
		list = eserv.getAllEmployees();
		
		for(Employee e: list) {
			System.out.println(e);
			if(e.getEmail().equals(employee.getEmail()) && e.getPassword().equals(employee.getPassword())) {
//				response.getWriter().append("Employee Found");
				response.getWriter().append("loged in");
				HttpSession sess = request.getSession();
				sess.setMaxInactiveInterval(0);
				sess.setAttribute("id", e.getId());
				
				return;
			}
		}
		
		response.getWriter().append("Email or password not found please try again");
	}
}
