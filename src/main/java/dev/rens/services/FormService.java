package dev.rens.services;

import java.util.Calendar;
import java.util.List;

import dev.rens.models.Form;
import dev.rens.repositories.FormRepositoryImpl;

public class FormService {
	
	public FormRepositoryImpl frepo = new FormRepositoryImpl();

	public Form createForm(Form f) {
		return frepo.createForm(f);
	}
	
	public Form getForm(int id) {
		return frepo.getForm(id);
	}
	
	public Form getLatestForm() {
		return frepo.getLatestForm();
	}
	
	public List<Form> getAllForms() {
		return frepo.getAllForms();
	}
	
	public Form updateForm(Form update) {
		return frepo.updateForm(update);
	}
	
	public Form updatFormReimbursementStatus(Form update) {
		return frepo.updateFormStatus(update);
	}
	
	public Form updateReimbursementAmount(Form update) {
		return frepo.updateFormReimbursmentAmount(update);
	}
	
	public Form updateFormSupervisorApproval(Form update) {
		return frepo.updateFormSupervisorApproval(update);
	}
	
	public Form updateFormDHApproval(Form update) {
		return frepo.updateFormDHApproval(update);
	}
	
	public Form updateFormAdditionalInfo(Form update) {
		return frepo.updateFormAdditionalInfo(update);
	}
	
	public Form updateFormAdditionalInfoToNull(Form update) {
		return frepo.updateFormAdditionalInfoToNull(update);
	}
	
	public Form updateFormBencoApproval(Form update) {
		return frepo.updateFormBencoApproval(update);
	}
	
	public Form updateFormReimbursementAmount(Form update) {
		return frepo.updateFormReimbursementAmount(update);
	}
	
	public boolean deleteForm(int id) {
		return frepo.deleteForm(id);
	}
	
	public double calculateReimbursementAmmount(double cost, String event_type) {
		
		double amount = 0;
		
		switch(event_type) {
		case"university_course":{
			amount = cost * 0.8;
		}break;
		case"seminar":{
			amount = cost * 0.6;
		}break;
		case"certification_preparation_class":{
			amount = cost * 0.75;
		}break;
		case"certification":{
			amount = cost;
		}break;
		case"technical_training":{
			amount = cost * 0.9;
		}break;
		default:{
			amount = cost * 0.3;
		}break;
		}
		
		return amount;
	}
	
	public double calculateFundsLeft(int employee_id) {
		List<Form> forms = getAllForms();
		
		double yearlyAmount = 1000;
		double amountUsed = 0;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		
		for(Form f: forms) {
			if(f.getEmployee_id() == employee_id && f.getSubmission_date().contains(year + "-") && !f.getForm_status().contains("Denied")
					&& (f.getReimbursment_status() == null ? true : !f.getReimbursment_status().contains("Denied"))) {
				System.out.println(f.getReimbursment_amount());
				amountUsed += f.getReimbursment_amount();
			}
		}
		System.out.println(amountUsed);
		System.out.println(yearlyAmount - amountUsed);
		
		return yearlyAmount - amountUsed;
	}
}
