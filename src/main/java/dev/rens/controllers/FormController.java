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

import dev.rens.models.Assessment;
import dev.rens.models.DepartmentHead;
import dev.rens.models.Employee;
import dev.rens.models.Event;
import dev.rens.models.Form;
import dev.rens.services.AssessmentService;
import dev.rens.services.DepartmentHeadService;
import dev.rens.services.EmployeeService;
import dev.rens.services.EventService;
import dev.rens.services.FormService;

public class FormController {

	public static Gson gson = new Gson();
	public static EventService evserv = new EventService();
	public static EmployeeService eserv = new EmployeeService();
	public static FormService fserv = new FormService();
	public static AssessmentService aserv = new AssessmentService();
	public static DepartmentHeadService dserv = new DepartmentHeadService();
	
	public static void submitForm(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException {
		Event event = gson.fromJson(request.getReader(), Event.class);
		HttpSession sess = request.getSession();
		int employee_id = (int) sess.getAttribute("id");
		String passing_grade = request.getParameter("passing_grade");
		
		Form form = new Form();
//		form.setEmployee_id(Integer.parseInt(request.getParameter("id")));
		form.setEmployee_id((int) sess.getAttribute("id"));
		form.setForm_status("In Review");
		form.setReimbursment_status("Pending");
		
		
		Employee e = eserv.getEmployee(employee_id);
		Employee supervisor = eserv.getEmployee(e.getSupervisor_id());
		if(supervisor.isDepartment_head()) {
			List<DepartmentHead> dhList = new ArrayList<DepartmentHead>();
			dhList = dserv.getAllDepartmentHeads();
			for(DepartmentHead d: dhList) {
				if(d.getDepartment().equals(e.getDepartment())) {
					form.setDepartment_head_approval(true);
				}
			}
		}
		
		
		
		fserv.createForm(form);
		form = fserv.getLatestForm();
		
		System.out.println("\n\n------------------------------------------------------------------");
		System.out.println(form);
		
		event.setId(form.getEvent_id());
		
		
		evserv.updateEvent(event);
		
		double calculated_amount = fserv.calculateReimbursementAmmount(event.getCost(), event.getEvent_type());
		double amount_available = fserv.calculateFundsLeft(employee_id);
		
		if(calculated_amount <= amount_available) {
			form.setReimbursment_amount(calculated_amount);
		}else {
			if(amount_available >= 0) {
				form.setReimbursment_amount(amount_available);
			}else {
				form.setReimbursment_amount(0);
			}
		}
		
		fserv.updateFormReimbursementAmount(form);
		
		Assessment assessment = aserv.getAssessment(form.getAssessment_id());
		assessment.setPassing_grade(passing_grade.charAt(0));
		aserv.updateAssessment(assessment);
		
		System.out.println(event);		
		
		response.getWriter().append("Your current estimated Reimbursement: " + form.getReimbursment_amount());
	}
	
	public static void updateReimbusementStatus(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException {
		Form form = gson.fromJson(request.getReader(), Form.class);
		
		fserv.updatFormReimbursementStatus(form);
		response.getWriter().append("Updated");
	}
}
