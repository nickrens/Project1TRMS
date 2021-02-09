package dev.rens.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import dev.rens.models.AdditionalInfo;
import dev.rens.models.Assessment;
import dev.rens.models.DepartmentHead;
import dev.rens.models.Employee;
import dev.rens.models.Event;
import dev.rens.models.Form;
import dev.rens.services.AdditionalInfoService;
import dev.rens.services.AssessmentService;
import dev.rens.services.DepartmentHeadService;
import dev.rens.services.EmployeeService;
import dev.rens.services.EventService;
import dev.rens.services.FormService;

public class ApproveController {

	public static Gson gson = new Gson();
	public static EventService evserv = new EventService();
	public static EmployeeService eserv = new EmployeeService();
	public static FormService fserv = new FormService();
	public static AssessmentService sserv = new AssessmentService();
	public static AdditionalInfoService addserv = new AdditionalInfoService();
	public static DepartmentHeadService dserv = new DepartmentHeadService();
	
	public static void approveFormAsSupervisor(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException {
		int form_id = Integer.parseInt(request.getParameter("form_id"));
//		int event_id = Integer.parseInt(request.getParameter("event_id"));
//		int assessment_id = Integer.parseInt(request.getParameter("assessment_id"));
		
		Form form = gson.fromJson(request.getReader(), Form.class);
		Form currentForm = fserv.getForm(form_id);
		form.setId(form_id);
		
		if(form.isSupervisor_approval()) {
			form.setForm_status(currentForm.getForm_status());
			response.getWriter().append("Form Approved");
			
			//if supervisor is also department head auto approve
			Employee e = eserv.getEmployee(currentForm.getEmployee_id());
			Employee supervisor = eserv.getEmployee(e.getSupervisor_id());
			if(supervisor.isDepartment_head()) {
				List<DepartmentHead> dhList = new ArrayList<DepartmentHead>();
				dhList = dserv.getAllDepartmentHeads();
				for(DepartmentHead d: dhList) {
					if(d.getDepartment().equals(e.getDepartment())) {
						Form dhForm = new Form();
						dhForm.setDepartment_head_approval(true);
						dhForm.setId(form_id);
						dhForm.setForm_status(currentForm.getForm_status());
						
						fserv.updateFormDHApproval(dhForm);
					}
				}
			}
		} else {
			response.getWriter().append("Form Denied");
			form.setForm_status("Denied by Supervisor");;
		}
		fserv.updateFormSupervisorApproval(form);
		
	}
	
	public static void approveFormAsDepartmentHead(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException {
		int form_id = Integer.parseInt(request.getParameter("form_id"));
		
		Form form = gson.fromJson(request.getReader(), Form.class);
		Form currentForm = fserv.getForm(form_id);
		form.setId(form_id);
		
		if(form.isDepartment_head_approval()) {
			form.setForm_status(currentForm.getForm_status());
			response.getWriter().append("Form Approved");
		} else {
			form.setForm_status("Denied by Department Head");;
			response.getWriter().append("Form Denied");
		}
		fserv.updateFormDHApproval(form);
	}

	public static void approveFormAsBenco(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException {
		int form_id = Integer.parseInt(request.getParameter("form_id"));
		
		Form form = gson.fromJson(request.getReader(), Form.class);
		form.setId(form_id);
		
		if(form.isBenco_approval()) {
			form.setForm_status("Approved");
			response.getWriter().append("Form Approved");
		} else {
			form.setForm_status("Denied by Benco");;
			response.getWriter().append("Form Denied");
		}
		fserv.updateFormBencoApproval(form);
	}
	
	public static void addAdditionalInfo(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException {
		int form_id = Integer.parseInt(request.getParameter("form_id"));
		String info_for = request.getParameter("info_for");
		
		AdditionalInfo info = gson.fromJson(request.getReader(), AdditionalInfo.class);
		
		Form form = fserv.getForm(form_id);
		Employee e = eserv.getEmployee(form.getEmployee_id());
		List<DepartmentHead> dhList = new ArrayList<DepartmentHead>();
		dhList = dserv.getAllDepartmentHeads();
		DepartmentHead dh = new DepartmentHead();
		
		for(DepartmentHead d: dhList) {
			if(d.getDepartment().equals(e.getDepartment())) {
				dh = d;
			}
		}
		
		switch(info_for) {
		case"employee":{
			info.setEmployee_id(e.getId());
		}break;
		case"supervisor":{
			info.setEmployee_id(e.getSupervisor_id());
		}break;
		case"department_head":{
			info.setEmployee_id(dh.getEmployee_id());
		}break;
		}
		
		System.out.println("\n\n\n\n");
		System.out.println(dh);
		System.out.println(e);
		System.out.println(info);
		
		addserv.createAdditionalInfo(info);
		info = addserv.getLatestAdditonalInfo();
		
		form.setAdditional_info_id(info.getId());
		fserv.updateFormAdditionalInfo(form);
		
		response.getWriter().append("Additional Info Requested from: " + info_for);
	}
	
	public static void updateReimbursementAmount(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException {
		Form form = gson.fromJson(request.getReader(), Form.class);
		
		fserv.updateFormReimbursementAmount(form);
		response.getWriter().append("Amount updated");
	}
}









