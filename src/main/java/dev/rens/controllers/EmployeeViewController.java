package dev.rens.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

public class EmployeeViewController {

	public static Gson gson = new Gson();
	public static EmployeeService eserv = new EmployeeService();
	public static FormService fserv = new FormService();
	public static EventService evserv = new EventService();
	public static AssessmentService aserv = new AssessmentService();
	public static AdditionalInfoService addserv = new AdditionalInfoService();
	public static DepartmentHeadService dserv = new DepartmentHeadService();
	
	public static void getAllForms(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sess = request.getSession();
		int employee_id = (int) sess.getAttribute("id");
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		System.out.println("Got request with id of : " + employee_id);
		
		List<Form> list = new ArrayList<Form>();
		
		list = fserv.getAllForms();
		
		List<Form> newList = new ArrayList<Form>();
		
		
		for(Form f: list) {
			System.out.println(f);
			
			if(f.getEmployee_id() == employee_id && f.getSubmission_date().contains(year + "-")) {
				f.setForm_event(evserv.getEvent(f.getEvent_id()));
				f.setForm_assessment(aserv.getAssessment(f.getAssessment_id()));
				newList.add(f);
			}
		}
		
		response.getWriter().append(gson.toJson(newList));
	}
	
	public static void getForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int form_id = Integer.parseInt(request.getParameter("form_id"));
		
		Form f = fserv.getForm(form_id);
		f.setForm_event(evserv.getEvent(f.getEvent_id()));
		f.setForm_assessment(aserv.getAssessment(f.getAssessment_id()));
		
		
		response.getWriter().append(gson.toJson(f));
	}
	
	public static void getNotifications(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sess = request.getSession();
		int employee_id = (int) sess.getAttribute("id");
		
		Employee e = eserv.getEmployee(employee_id);
		
		List<AdditionalInfo> infoList = new ArrayList<AdditionalInfo>();
		infoList = addserv.getAllAdditionaInfo();
		
		List<AdditionalInfo> myList = new ArrayList<AdditionalInfo>();
		List<Integer> infoIDs = new ArrayList<Integer>();
		
		for(AdditionalInfo i: infoList) {
			if(i.getEmployee_id() == e.getId()) {
				myList.add(i);
				infoIDs.add(i.getId());
			}
		}
		System.out.println(myList);
		System.out.println(infoIDs);
		
		List<Form> formsList = new ArrayList<Form>();
		formsList = fserv.getAllForms();
		
		List<Form> myForms = new ArrayList<Form>();
		
		System.out.println(formsList);
		
		for(Form f: formsList) {
			if(infoIDs.contains(f.getAdditional_info_id())) {
				f.setForm_additional_info(addserv.getAdditionalInfo(f.getAdditional_info_id()));
				f.setForm_event(evserv.getEvent(f.getEvent_id()));
				f.setForm_employee(eserv.getEmployee(f.getEmployee_id()));
				myForms.add(f);
			}
		}
		
		response.getWriter().append(gson.toJson(myForms));
		
	}
	
	public static void removeAdditionalInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int form_id = Integer.parseInt(request.getParameter("form_id"));
//		int additiona_info_id = Integer.parseInt(request.getParameter("additional_info_id"));
		
//		Update forms set additional_info_id = null where id = 63;
//		delete additional_info ;
		
		Form f = fserv.getForm(form_id);
		fserv.updateFormAdditionalInfoToNull(f);
		
		int additiona_info_id = f.getAdditional_info_id();
		AdditionalInfo info = addserv.getAdditionalInfo(additiona_info_id);
		addserv.deleteAdditionalInfo(info.getId());
		
		response.getWriter().append("Notification Removed");
	}
	
	public static void getAllFormsForSupervisorToApprove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sess = request.getSession();
		int employee_id = (int) sess.getAttribute("id");
		
		List<Form> allForms = new ArrayList<Form>();
		List<Form> myForms = new ArrayList<Form>();
		
		allForms = fserv.getAllForms();
		
		Employee e = new Employee();
		
		
		for(Form f: allForms) {
			e = eserv.getEmployee(f.getEmployee_id());
			if(e.getSupervisor_id() == employee_id) {
				if(f.isSupervisor_approval() == false && !f.getForm_status().contains("Denied") && f.getAdditional_info_id() == 0) {
					f.setForm_assessment(aserv.getAssessment(f.getAssessment_id()));
					f.setForm_event(evserv.getEvent(f.getEvent_id()));
					f.setForm_employee(e);
					myForms.add(f);
				}
			}
		}
		
		response.getWriter().append(gson.toJson(myForms));
	}
	
	public static void getAllFormsForDHToApprove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sess = request.getSession();
		int employee_id = (int) sess.getAttribute("id");
		
		Employee me = eserv.getEmployee(employee_id);
		//if user is not department head return nothing
		
		
		List<Form> allForms = new ArrayList<Form>();
		List<Form> myForms = new ArrayList<Form>();
		
		if(!me.isDepartment_head()) {
			System.out.println("not a department head");
			response.getWriter().append(gson.toJson(myForms));
			return;
		}
		
		allForms = fserv.getAllForms();
		
		Employee e = new Employee();
		
		List<DepartmentHead> dhList = new ArrayList<DepartmentHead>();
		dhList = dserv.getAllDepartmentHeads();
		DepartmentHead dh = new DepartmentHead();
		
		for(Form f: allForms) {
			e = eserv.getEmployee(f.getEmployee_id());
			
			for(DepartmentHead d: dhList) {
				if(d.getDepartment().equals(e.getDepartment())) {
					dh = d;
				}
			}
			System.out.println(dh);
			if(dh.getEmployee_id() == employee_id) {
				if(f.isSupervisor_approval() == true && f.isDepartment_head_approval() == false && !f.getForm_status().contains("Denied") && f.getAdditional_info_id() == 0) {
					f.setForm_assessment(aserv.getAssessment(f.getAssessment_id()));
					f.setForm_event(evserv.getEvent(f.getEvent_id()));
					f.setForm_employee(e);
					myForms.add(f);
				}
			}
		}
		
		response.getWriter().append(gson.toJson(myForms));
	}
	
	public static void getAllFormsForBencoToApprove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sess = request.getSession();
		int employee_id = (int) sess.getAttribute("id");
		
		Employee me = eserv.getEmployee(employee_id);
		List<Form> allForms = new ArrayList<Form>();
		List<Form> myForms = new ArrayList<Form>();
		
		if(!me.isBenco()) {
			System.out.println("not Benco");
			response.getWriter().append(gson.toJson(myForms));
			return;
		}
		
		allForms = fserv.getAllForms();
		
		Employee e = new Employee();
		
		for(Form f: allForms) {
			e = eserv.getEmployee(f.getEmployee_id());
			if(f.isSupervisor_approval() == true && f.isDepartment_head_approval() == true && f.isBenco_approval() == false 
					&& !f.getForm_status().contains("Denied") && f.getAdditional_info_id() == 0) {
				f.setForm_assessment(aserv.getAssessment(f.getAssessment_id()));
				f.setForm_event(evserv.getEvent(f.getEvent_id()));
				f.setForm_employee(e);
				myForms.add(f);
			}
		}
		
		response.getWriter().append(gson.toJson(myForms));
	}
	
	public static void updateAssessment(HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException {
		int form_id = Integer.parseInt(request.getParameter("form_id"));
		Assessment a = gson.fromJson(request.getReader(), Assessment.class);
		
		Form form = fserv.getForm(form_id);
		
		a.setId(form.getAssessment_id());
		
		Assessment oldAssessment = aserv.getAssessment(a.getId());
		Event e = evserv.getEvent(form.getEvent_id());
		
		a = aserv.fillInBlanks(a, oldAssessment);
		
		
		if((e.getGrading_format().equals("letter_grade") && a.isGrade_status()) || (e.getGrading_format().equals("presentation") && a.isPresentation_status()) || 
				(e.getGrading_format().equals("both") && a.isPresentation_status() && a.isGrade_status())) {
			
			form.setReimbursment_status("Approved");
			
			System.out.println("\n\n\n" + form);
			
			fserv.updatFormReimbursementStatus(form);
		}

		System.out.println("\n\n\n" + a);
		
		aserv.updateAssessment(a);
		
		response.getWriter().append("Assesment Updated");
	}
	
	public static void getFormsToAppoveGrade(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sess = request.getSession();
		int employee_id = (int) sess.getAttribute("id");
		
		Employee me = eserv.getEmployee(employee_id);
		List<Form> allForms = new ArrayList<Form>();
		List<Form> myForms = new ArrayList<Form>();
		
		if(!me.isBenco()) {
			System.out.println("not Benco");
			response.getWriter().append(gson.toJson(myForms));
			return;
		}
		
		allForms = fserv.getAllForms();
		
		List<String> acceptedFormats = new ArrayList<>();
		acceptedFormats.add("letter_grade");
		acceptedFormats.add("both");
		
		List<Character> acceptedGrades = new ArrayList<>();
		acceptedGrades.add('A');
		acceptedGrades.add('B');
		acceptedGrades.add('C');
		acceptedGrades.add('D');
		acceptedGrades.add('F');
		
		Assessment a = new Assessment();
		Event e = new Event();
		
		for(Form f: allForms) {
			a = aserv.getAssessment(f.getAssessment_id());
			e = evserv.getEvent(f.getEvent_id());
			if(f.isBenco_approval() && !f.getForm_status().contains("Denied") && acceptedFormats.contains(e.getGrading_format()) && acceptedGrades.contains(a.getGrade()) 
					&& a.isGrade_status() == false && !f.getReimbursment_status().contains("Denied")) {
				f.setForm_assessment(a);
				f.setForm_event(e);
				f.setForm_employee(eserv.getEmployee(f.getEmployee_id()));
				myForms.add(f);
			}
		}
		
		response.getWriter().append(gson.toJson(myForms));
	}
	
	public static void getFormsToApprovePresentation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sess = request.getSession();
		int employee_id = (int) sess.getAttribute("id");
		
		
		
		List<Form> allForms = new ArrayList<Form>();
		List<Form> myForms = new ArrayList<Form>();
		
		allForms = fserv.getAllForms();
		
		Employee e = new Employee();
		Event ev = new Event();
		Assessment a = new Assessment();
		
		List<String> acceptedFormats = new ArrayList<>();
		acceptedFormats.add("presentation");
		acceptedFormats.add("both");
		
		
		for(Form f: allForms) {
			e = eserv.getEmployee(f.getEmployee_id());
			ev = evserv.getEvent(f.getEvent_id());
			a = aserv.getAssessment(f.getAssessment_id());
			if(e.getSupervisor_id() == employee_id) {
				if(f.isBenco_approval() && !f.getForm_status().contains("Denied") && acceptedFormats.contains(ev.getGrading_format()) 
						&& a.isPresentation_status() == false && (f.getReimbursment_status() == null ? true : !f.getReimbursment_status().contains("Denied"))) {
					f.setForm_assessment(aserv.getAssessment(f.getAssessment_id()));
					f.setForm_event(evserv.getEvent(f.getEvent_id()));
					f.setForm_employee(e);
					myForms.add(f);
				}
			}
		}
		
		response.getWriter().append(gson.toJson(myForms));
	}
	
	public static void getEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sess = request.getSession();
		int employee_id = (int) sess.getAttribute("id");
		
		Employee e = eserv.getEmployee(employee_id);
		
		double amount = fserv.calculateFundsLeft(employee_id);
		
		e.setReimbursement_remaining(amount);
		eserv.updateEmployeeReimbursementRemaining(e);
		
		response.getWriter().append(gson.toJson(e));
	}
}
















