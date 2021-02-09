package dev.rens.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.rens.controllers.ApproveController;
import dev.rens.controllers.EmployeeViewController;
import dev.rens.controllers.FormController;
import dev.rens.controllers.LoginController;


public class RequestHelper {

	
public static void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String uri = request.getRequestURI();
		
		System.out.println(uri);
		
		switch(uri) {
		case"/Project1/login.do":{
			LoginController.login(request, response);
		}break;
		case"/Project1/viewForms.do":{
			EmployeeViewController.getAllForms(request, response);
		}break;
		case"/Project1/viewForm.do":{
			EmployeeViewController.getForm(request, response);
		}break;
		case"/Project1/submitForm.do":{
			FormController.submitForm(request, response);
		}break;
		case"/Project1/supervisorApproval.do":{
			ApproveController.approveFormAsSupervisor(request, response);
		}break;
		case"/Project1/dhApproval.do":{
			ApproveController.approveFormAsDepartmentHead(request, response);
		}break;
		case"/Project1/bencoApproval.do":{
			ApproveController.approveFormAsBenco(request, response);
		}break;
		case"/Project1/addInfo.do":{
			ApproveController.addAdditionalInfo(request, response);
		}break;
		case"/Project1/veiwNotifications.do":{
			EmployeeViewController.getNotifications(request, response);
		}break;
		case"/Project1/removeNotification.do":{
			EmployeeViewController.removeAdditionalInfo(request, response);
		}break;
		case"/Project1/getSupervisorFormsToApprove.do":{
			EmployeeViewController.getAllFormsForSupervisorToApprove(request, response);
		}break;
		case"/Project1/getDHFormsToApprove.do":{
			EmployeeViewController.getAllFormsForDHToApprove(request, response);
		}break;
		case"/Project1/getBencoFormsToApprove.do":{
			EmployeeViewController.getAllFormsForBencoToApprove(request, response);
		}break;
		case"/Project1/updateAssessment.do":{
			EmployeeViewController.updateAssessment(request, response);
		}break;
		case"/Project1/updateReimbursementAmount.do":{
			ApproveController.updateReimbursementAmount(request, response);
		}break;
		case"/Project1/getGradesToApprove.do": {
			EmployeeViewController.getFormsToAppoveGrade(request, response);
		}break;
		case"/Project1/getPresenationsToApprove.do":{
			EmployeeViewController.getFormsToApprovePresentation(request, response);
		}break;
		case"/Project1/updateReimbursementStatus.do":{
			FormController.updateReimbusementStatus(request, response);
		}break;
		case"/Project1/getEmployee.do":{
			EmployeeViewController.getEmployee(request, response);
		}break;
		default: {
			response.sendError(418, "Default case hit. Cannot brew coffee is a teapot!");
		}break;
		}
		
	}
}
