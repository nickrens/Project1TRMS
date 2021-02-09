package dev.rens.models;

public class Employee {

	private int id;
	private int supervisor_id;
	private String name;
	private String department;
	private boolean department_head;
	private boolean benco;
	private String email;
	private String password;
	private double reimbursement_remaining; 
	
	public Employee() {
		super();
	}

	public Employee(int id, int supervisor_id, String name, String department, boolean department_head, boolean benco,
			String email, String password) {
		super();
		this.id = id;
		this.supervisor_id = supervisor_id;
		this.name = name;
		this.department = department;
		this.department_head = department_head;
		this.benco = benco;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSupervisor_id() {
		return supervisor_id;
	}

	public void setSupervisor_id(int supervisor_id) {
		this.supervisor_id = supervisor_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean isDepartment_head() {
		return department_head;
	}

	public void setDepartment_head(boolean department_head) {
		this.department_head = department_head;
	}

	public boolean isBenco() {
		return benco;
	}

	public void setBenco(boolean benco) {
		this.benco = benco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getReimbursement_remaining() {
		return reimbursement_remaining;
	}

	public void setReimbursement_remaining(double reimbursement_remaining) {
		this.reimbursement_remaining = reimbursement_remaining;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", supervisor_id=" + supervisor_id + ", name=" + name + ", department="
				+ department + ", department_head=" + department_head + ", benco=" + benco + ", email=" + email
				+ ", password=" + password + "]";
	}
	
	
	
	
}
