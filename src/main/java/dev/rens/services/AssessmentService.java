package dev.rens.services;

import dev.rens.models.Assessment;
import dev.rens.repositories.AssessmentRepositoryImpl;

public class AssessmentService {

	public AssessmentRepositoryImpl arepo = new AssessmentRepositoryImpl();
	
	public Assessment createAssessment(Assessment a) {
		return arepo.createAssessment(a);
	}
	public Assessment getAssessment(int id) {
		return arepo.getAssessment(id);
	}
	public Assessment getLatestAssessment() {
		return arepo.getLatestAssessment();
	}
	public Assessment updateAssessment(Assessment update) {
		return arepo.updateAssessment(update);
	}
	public boolean deleteAssessment(int id) {
		return arepo.deleteAssessment(id);
	}
	
	public Assessment fillInBlanks(Assessment newa, Assessment olda) {
		
		if(newa.getGrade() == '?') {
			System.out.println(olda.getGrade());
			newa.setGrade(olda.getGrade());
		}
		if(newa.getPassing_grade() == '?') {
			newa.setPassing_grade(olda.getPassing_grade());
		}
		if(newa.getPresentaion() == null) {
			newa.setPresentaion(olda.getPresentaion());
		}
		if(olda.isGrade_status()) {
			newa.setGrade_status(true);
		}
		if(olda.isPresentation_status()) {
			newa.setPresentation_status(true);
		}
		
		return newa;
	}
}
