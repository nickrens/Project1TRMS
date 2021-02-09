package dev.rens.models;

public class Assessment {

	private int id;
	private char grade;
	private char passing_grade;
	private String presentation;
	private boolean grade_status;
	private boolean presentation_status;
	
	public Assessment() {
		super();
	}

	public Assessment(int id, char grade, char passing_grade, String presentaion, boolean grade_status,
			boolean presentation_status) {
		super();
		this.id = id;
		this.grade = grade;
		this.passing_grade = passing_grade;
		this.presentation = presentaion;
		this.grade_status = grade_status;
		this.presentation_status = presentation_status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getGrade() {
		return grade;
	}

	public void setGrade(char grade) {
		this.grade = grade;
	}

	public char getPassing_grade() {
		return passing_grade;
	}

	public void setPassing_grade(char passing_grade) {
		this.passing_grade = passing_grade;
	}

	public String getPresentaion() {
		return presentation;
	}

	public void setPresentaion(String presentaion) {
		this.presentation = presentaion;
	}

	public boolean isGrade_status() {
		return grade_status;
	}

	public void setGrade_status(boolean grade_status) {
		this.grade_status = grade_status;
	}

	public boolean isPresentation_status() {
		return presentation_status;
	}

	public void setPresentation_status(boolean presentation_status) {
		this.presentation_status = presentation_status;
	}

	@Override
	public String toString() {
		return "Assessment [id=" + id + ", grade=" + grade + ", passing_grade=" + passing_grade + ", presentaion="
				+ presentation + ", grade_status=" + grade_status + ", presentation_status="
				+ presentation_status + "]";
	}
	
	
	
	
}
