package dev.rens.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.rens.models.Form;
import dev.rens.util.JDBCConnection;

public class FormRepositoryImpl implements FormRepository {
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public Form createForm(Form f) {

		try {
			
			String sql = "Call ADD_FORM(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, Integer.toString(f.getEmployee_id()));
			cs.setString(2, Integer.toString(f.getAssessment_id()));
			cs.setString(3, setBoolean(f.isSupervisor_approval()));
			cs.setString(4, f.getSupervisor_reason_for_denial());
			cs.setString(5, setBoolean(f.isDepartment_head_approval()));
			cs.setString(6, Integer.toString(f.getAdditional_info_id()));
			cs.setString(7, setBoolean(f.isBenco_approval()));
			cs.setString(8, f.getForm_status());
			cs.setString(9, f.getReimbursment_status());
			cs.setString(10, Double.toString(f.getReimbursment_amount()));
			cs.setString(11, f.getSubmission_date());
			cs.setString(12, Integer.toString(f.getEvent_id()));
			cs.setString(13, f.getWork_day_missing_start());
			cs.setString(14, f.getWork_day_missing_end());
			
			
			cs.execute();
			return f;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Form getForm(int id) {

		try {
			
			String sql = "Select * From forms Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(id));
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Form f = new Form();
				
				f.setId(rs.getInt("ID"));
				f.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				f.setAssessment_id(rs.getInt("ASSESSMENT_ID"));
				f.setSupervisor_approval(rs.getBoolean("SUPERVISOR_APPROVAL"));
				f.setSupervisor_reason_for_denial(rs.getString("SUPERVISOR_REASON_FOR_DENIAL"));
				f.setDepartment_head_approval(rs.getBoolean("DEPARTMENT_HEAD_APPROVAL"));
				f.setAdditional_info_id(rs.getInt("ADDITIONAL_INFO_ID"));
				f.setBenco_approval(rs.getBoolean("BENCO_APPROVAL"));
				f.setForm_status(rs.getString("FORM_STATUS"));
				f.setReimbursment_status(rs.getString("REIMBURSMENT_STATUS"));
				f.setReimbursment_amount(rs.getDouble("REIMBURSMENT_AMOUNT"));
				f.setSubmission_date(rs.getString("SUBMISSION_DATE"));
				f.setEvent_id(rs.getInt("EVENT_ID"));
				f.setWork_day_missing_start(rs.getString("WORK_DAY_MISSING_START"));
				f.setWork_day_missing_end(rs.getString("WORK_DAY_MISSING_END"));
				
				ps.close();
				rs.close();
				return f;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Form getLatestForm() {

		try {
			
			String sql = "Select * From forms Order By ID desc";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Form f = new Form();
				
				f.setId(rs.getInt("ID"));
				f.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				f.setAssessment_id(rs.getInt("ASSESSMENT_ID"));
				f.setSupervisor_approval(rs.getBoolean("SUPERVISOR_APPROVAL"));
				f.setSupervisor_reason_for_denial(rs.getString("SUPERVISOR_REASON_FOR_DENIAL"));
				f.setDepartment_head_approval(rs.getBoolean("DEPARTMENT_HEAD_APPROVAL"));
				f.setAdditional_info_id(rs.getInt("ADDITIONAL_INFO_ID"));
				f.setBenco_approval(rs.getBoolean("BENCO_APPROVAL"));
				f.setForm_status(rs.getString("FORM_STATUS"));
				f.setReimbursment_status(rs.getString("REIMBURSMENT_STATUS"));
				f.setReimbursment_amount(rs.getDouble("REIMBURSMENT_AMOUNT"));
				f.setSubmission_date(rs.getString("SUBMISSION_DATE"));
				f.setEvent_id(rs.getInt("EVENT_ID"));
				f.setWork_day_missing_start(rs.getString("WORK_DAY_MISSING_START"));
				f.setWork_day_missing_end(rs.getString("WORK_DAY_MISSING_END"));
				
				rs.close();
				ps.close();
				return f;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Form> getAllForms() {

try {
			
			String sql = "Select * From forms";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			List<Form> list = new ArrayList<Form>();
			while(rs.next()) {
				Form f = new Form();
				
				f.setId(rs.getInt("ID"));
				f.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				f.setAssessment_id(rs.getInt("ASSESSMENT_ID"));
				f.setSupervisor_approval(rs.getBoolean("SUPERVISOR_APPROVAL"));
				f.setSupervisor_reason_for_denial(rs.getString("SUPERVISOR_REASON_FOR_DENIAL"));
				f.setDepartment_head_approval(rs.getBoolean("DEPARTMENT_HEAD_APPROVAL"));
				f.setAdditional_info_id(rs.getInt("ADDITIONAL_INFO_ID"));
				f.setBenco_approval(rs.getBoolean("BENCO_APPROVAL"));
				f.setForm_status(rs.getString("FORM_STATUS"));
				f.setReimbursment_status(rs.getString("REIMBURSMENT_STATUS"));
				f.setReimbursment_amount(rs.getDouble("REIMBURSMENT_AMOUNT"));
				f.setSubmission_date(rs.getString("SUBMISSION_DATE"));
				f.setEvent_id(rs.getInt("EVENT_ID"));
				f.setWork_day_missing_start(rs.getString("WORK_DAY_MISSING_START"));
				f.setWork_day_missing_end(rs.getString("WORK_DAY_MISSING_END"));
				
				list.add(f);
			}
			
			rs.close();
			ps.close();
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public Form updateForm(Form update) {

		try {
			
			String sql = "Update forms Set employee_id = ?, assessment_id = ?, supervisor_approval = ?, supervisor_reason_for_denial = ?, department_head_approval = ?, additional_info_id = ?, "
					+ "benco_approval = ?, form_status = ?, reimbursment_status = ?, reimbursment_amount = ?, submission_date = ?, event_id = ?, work_day_missing_start = ? work_day_missing_end = ?"
					+ "Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(update.getEmployee_id()));
			ps.setString(2, Integer.toString(update.getAssessment_id()));
			ps.setString(3, setBoolean(update.isSupervisor_approval()));
			ps.setString(4, update.getSupervisor_reason_for_denial());
			ps.setString(5, setBoolean(update.isDepartment_head_approval()));
			ps.setString(6, Integer.toString(update.getAdditional_info_id()));
			ps.setString(7, setBoolean(update.isBenco_approval()));
			ps.setString(8, update.getForm_status());
			ps.setString(9, update.getReimbursment_status());
			ps.setString(10, Double.toString(update.getReimbursment_amount()));
			ps.setString(11, update.getSubmission_date());
			ps.setString(12, Integer.toString(update.getEvent_id()));
			ps.setString(13, update.getWork_day_missing_start());
			ps.setString(14, update.getWork_day_missing_end());
			ps.setString(15, Integer.toString(update.getId()));
			
			ps.execute();
			
			ps.close();
			return update;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Form updateFormAdditionalInfo(Form update) {

		try {
			
			String sql = "Update forms Set additional_info_id = ?  Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(update.getAdditional_info_id()));
			ps.setString(2, Integer.toString(update.getId()));
			
			ps.execute();
			
			ps.close();
			return update;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Form updateFormAdditionalInfoToNull(Form update) {

		try {
			
			String sql = "Update forms Set additional_info_id = null  Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(update.getId()));
			
			ps.execute();
			
			ps.close();
			return update;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Form updateFormSupervisorApproval(Form update) {

		try {
			
			String sql = "Update forms Set supervisor_approval = ?, form_status = ?, supervisor_reason_for_denial = ?  Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, setBoolean(update.isSupervisor_approval()));
			ps.setString(2, update.getForm_status());
			ps.setString(3, update.getSupervisor_reason_for_denial());
			ps.setString(4, Integer.toString(update.getId()));
			
			ps.execute();
			
			ps.close();
			return update;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Form updateFormStatus(Form update) {

		try {
			
			String sql = "Update forms Set REIMBURSMENT_STATUS = ? Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, update.getReimbursment_status());
			ps.setString(2, Integer.toString(update.getId()));
			
			ps.execute();
			
			ps.close();
			return update;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Form updateFormReimbursmentAmount(Form update) {

		try {
			
			String sql = "Update forms Set REIMBURSMENT_AMOUNT = ?  Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Double.toString(update.getReimbursment_amount()));
			ps.setString(2, Integer.toString(update.getId()));
			
			ps.execute();
			
			ps.close();
			return update;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Form updateFormDHApproval(Form update) {

		try {
			
			String sql = "Update forms Set department_head_approval = ?, form_status = ?  Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, setBoolean(update.isDepartment_head_approval()));
			ps.setString(2, update.getForm_status());
			ps.setString(3, Integer.toString(update.getId()));
			
			ps.execute();
			
			ps.close();
			return update;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Form updateFormBencoApproval(Form update) {

		try {
			
			String sql = "Update forms Set benco_approval = ?, form_status = ?  Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, setBoolean(update.isBenco_approval()));
			ps.setString(2, update.getForm_status());
			ps.setString(3, Integer.toString(update.getId()));
			
			ps.execute();
			
			ps.close();
			return update;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Form updateFormReimbursementAmount(Form update) {

		try {
			
			String sql = "Update forms Set reimbursment_amount = ? Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Double.toString(update.getReimbursment_amount()));
			ps.setString(2, Integer.toString(update.getId()));
			
			ps.execute();
			
			ps.close();
			return update;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

	@Override
	public boolean deleteForm(int id) {

		try {
			
			String sql = "Delete forms Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(id));
			ps.execute();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	private String setBoolean(boolean item) {
		if(item) {
			return "1";
		}else {
			return "0";
		}
	}
}
