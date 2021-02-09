package dev.rens.repositories;

import dev.rens.models.Assessment;

public interface AssessmentRepository {

	Assessment createAssessment(Assessment a);
	Assessment getAssessment(int id);
	Assessment updateAssessment(Assessment update);
	boolean deleteAssessment(int id);
	
	
}
