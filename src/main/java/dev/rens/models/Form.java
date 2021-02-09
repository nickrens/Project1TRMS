package dev.rens.models;

public class Form {

	private int id;
	private int employee_id;
	private int assessment_id;
	private boolean supervisor_approval;
	private String supervisor_reason_for_denial;
	private boolean department_head_approval;
	private int additional_info_id;
	private boolean benco_approval;
	private String form_status;
	private double reimbursment_amount;
	private String reimbursment_status;
	private String submission_date;
	private int event_id;
	private String work_day_missing_start;
	private String work_day_missing_end;
	private Assessment form_assessment;
	private Event form_event;
	private AdditionalInfo form_additional_info;
	private Employee form_employee;
	
	public Form() {
		super();
	}

	public Form(int id, int employee_id, int assessment_id, boolean supervisor_approval,
			String supervisor_reason_for_denial, boolean department_head_approval, int additional_info_id,
			boolean benco_approval, String form_status, double reimbursment_amount, String reimbursment_status,
			String submission_date, int event_id, String work_day_missing_start, String work_day_missing_end) {
		super();
		this.id = id;
		this.employee_id = employee_id;
		this.assessment_id = assessment_id;
		this.supervisor_approval = supervisor_approval;
		this.supervisor_reason_for_denial = supervisor_reason_for_denial;
		this.department_head_approval = department_head_approval;
		this.additional_info_id = additional_info_id;
		this.benco_approval = benco_approval;
		this.form_status = form_status;
		this.reimbursment_amount = reimbursment_amount;
		this.reimbursment_status = reimbursment_status;
		this.submission_date = submission_date;
		this.event_id = event_id;
		this.work_day_missing_start = work_day_missing_start;
		this.work_day_missing_end = work_day_missing_end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public int getAssessment_id() {
		return assessment_id;
	}

	public void setAssessment_id(int assessment_id) {
		this.assessment_id = assessment_id;
	}

	public boolean isSupervisor_approval() {
		return supervisor_approval;
	}

	public void setSupervisor_approval(boolean supervisor_approval) {
		this.supervisor_approval = supervisor_approval;
	}

	public String getSupervisor_reason_for_denial() {
		return supervisor_reason_for_denial;
	}

	public void setSupervisor_reason_for_denial(String supervisor_reason_for_denial) {
		this.supervisor_reason_for_denial = supervisor_reason_for_denial;
	}

	public boolean isDepartment_head_approval() {
		return department_head_approval;
	}

	public void setDepartment_head_approval(boolean department_head_approval) {
		this.department_head_approval = department_head_approval;
	}

	public int getAdditional_info_id() {
		return additional_info_id;
	}

	public void setAdditional_info_id(int additional_info_id) {
		this.additional_info_id = additional_info_id;
	}

	public boolean isBenco_approval() {
		return benco_approval;
	}

	public void setBenco_approval(boolean benco_approval) {
		this.benco_approval = benco_approval;
	}

	public String getForm_status() {
		return form_status;
	}

	public void setForm_status(String form_status) {
		this.form_status = form_status;
	}

	public double getReimbursment_amount() {
		return reimbursment_amount;
	}

	public void setReimbursment_amount(double reimbursment_amount) {
		this.reimbursment_amount = reimbursment_amount;
	}

	public String getReimbursment_status() {
		return reimbursment_status;
	}

	public void setReimbursment_status(String reimbursment_status) {
		this.reimbursment_status = reimbursment_status;
	}

	public String getSubmission_date() {
		return submission_date;
	}

	public void setSubmission_date(String submission_date) {
		this.submission_date = submission_date;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public String getWork_day_missing_start() {
		return work_day_missing_start;
	}

	public void setWork_day_missing_start(String work_day_missing_start) {
		this.work_day_missing_start = work_day_missing_start;
	}

	public String getWork_day_missing_end() {
		return work_day_missing_end;
	}

	public void setWork_day_missing_end(String work_day_missing_end) {
		this.work_day_missing_end = work_day_missing_end;
	}

	public Assessment getForm_assessment() {
		return form_assessment;
	}

	public void setForm_assessment(Assessment form_assessment) {
		this.form_assessment = form_assessment;
	}

	public Event getForm_event() {
		return form_event;
	}

	public void setForm_event(Event form_event) {
		this.form_event = form_event;
	}

	public AdditionalInfo getForm_additional_info() {
		return form_additional_info;
	}

	public void setForm_additional_info(AdditionalInfo form_additional_info) {
		this.form_additional_info = form_additional_info;
	}

	public Employee getForm_employee() {
		return form_employee;
	}

	public void setForm_employee(Employee form_employee) {
		this.form_employee = form_employee;
	}

	@Override
	public String toString() {
		return "Form [id=" + id + ", employee_id=" + employee_id + ", assessment_id=" + assessment_id
				+ ", supervisor_approval=" + supervisor_approval + ", supervisor_reason_for_denial="
				+ supervisor_reason_for_denial + ", department_head_approval=" + department_head_approval
				+ ", additional_info_id=" + additional_info_id + ", benco_approval=" + benco_approval + ", form_status="
				+ form_status + ", reimbursment_amount=" + reimbursment_amount + ", reimbursment_status="
				+ reimbursment_status + ", submission_date=" + submission_date + ", event_id=" + event_id
				+ ", work_day_missing_start=" + work_day_missing_start + ", work_day_missing_end="
				+ work_day_missing_end + "]";
	}
	
	
	
}
