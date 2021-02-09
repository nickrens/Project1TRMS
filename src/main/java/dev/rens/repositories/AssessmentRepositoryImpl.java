package dev.rens.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dev.rens.models.Assessment;
import dev.rens.util.JDBCConnection;

public class AssessmentRepositoryImpl implements AssessmentRepository {
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public Assessment createAssessment(Assessment a) {

		try {
			
			String sql = "Call add_assessment(?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, Character.toString(a.getGrade()));
			cs.setString(2, Character.toString(a.getPassing_grade()));
			cs.setString(3, a.getPresentaion());
			cs.setString(4, setBoolean(a.isGrade_status()));
			cs.setString(5, setBoolean(a.isPresentation_status()));
			
			cs.execute();
			return a;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Assessment getAssessment(int id) {

		try {
			
			String sql = "Select * From assessments Where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(id));
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Assessment a = new Assessment();
				
				a.setId(rs.getInt("ID"));
				a.setGrade((isSet(rs.getString("GRADE")) ? rs.getString("GRADE").charAt(0) : ' '));
				a.setPassing_grade((isSet(rs.getString("PASSING_GRADE")) ? rs.getString("PASSING_GRADE").charAt(0) : ' '));
				a.setPresentaion(rs.getString("PRESENTATION"));
				a.setGrade_status(rs.getBoolean("GRADE_STATUS"));
				a.setPresentation_status((isSet(rs.getBoolean("PRESENTATION_STATUS")) ? rs.getBoolean("PRESENTATION_STATUS") : null));
				
				ps.close();
				rs.close();
				return a;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean isSet(Object a) {
		if(a != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public Assessment getLatestAssessment() {

		try {
			
			String sql = "Select * From assessments Order By ID desc";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Assessment a = new Assessment();
				
				a.setId(rs.getInt("ID"));
				a.setGrade(rs.getString("GRADE").charAt(0));
				a.setPassing_grade(rs.getString("PASSING_GRADE").charAt(0));
				a.setPresentaion(rs.getString("PRESENTATION"));
				a.setGrade_status(rs.getBoolean("GRADE_STATUS"));
				a.setPresentation_status(rs.getBoolean("PRESENTATION_STATUS"));
				
				rs.close();
				ps.close();
				return a;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Assessment updateAssessment(Assessment update) {

		try {
			
			String sql = "Update assessments Set GRADE = ?, PASSING_GRADE = ?, PRESENTATION = ?, GRADE_STATUS = ?, PRESENTATION_STATUS = ? Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Character.toString(update.getGrade()));
			ps.setString(2, Character.toString(update.getPassing_grade()));
			ps.setString(3, update.getPresentaion());
			ps.setString(4, setBoolean(update.isGrade_status()));
			ps.setString(5, setBoolean(update.isPresentation_status()));
			ps.setString(6, Integer.toString(update.getId()));
			
			ps.execute();
			
			ps.close();
			return update;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public boolean deleteAssessment(int id) {

		try {
			
			String sql = "Delete assessments Where ID = ?";
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
