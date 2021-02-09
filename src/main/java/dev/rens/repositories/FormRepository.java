package dev.rens.repositories;

import java.util.List;

import dev.rens.models.Form;


public interface FormRepository {

	Form createForm(Form f);
	Form getForm(int id);
	List<Form> getAllForms();
	
	Form updateForm(Form update);
	boolean deleteForm(int id);
}
