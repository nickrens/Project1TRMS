package dev.rens.models;

public class AdditionalInfo {

	private int id;
	private String info_needed;
	private int employee_id;
	
	public AdditionalInfo() {
		super();
	}

	public AdditionalInfo(int id, String info_needed, int employee_id) {
		super();
		this.id = id;
		this.info_needed = info_needed;
		this.employee_id = employee_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfo_needed() {
		return info_needed;
	}

	public void setInfo_needed(String info_needed) {
		this.info_needed = info_needed;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	@Override
	public String toString() {
		return "AdditionalInfo [id=" + id + ", " + ", info_needed=" + info_needed + ", employee_id="
				+ employee_id + "]";
	}
	
	
	
	
}
