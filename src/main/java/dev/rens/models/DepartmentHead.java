package dev.rens.models;

public class DepartmentHead {

	private String department;
	private int employee_id;
	
	public DepartmentHead() {
		super();
	}

	public DepartmentHead(String department, int employee_id) {
		super();
		this.department = department;
		this.employee_id = employee_id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	@Override
	public String toString() {
		return "DepartmentHead [department=" + department + ", employee_id=" + employee_id + "]";
	}
	
	
	
	
}
