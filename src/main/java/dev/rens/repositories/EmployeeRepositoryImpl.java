package dev.rens.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.rens.models.Employee;
import dev.rens.util.JDBCConnection;

public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public Employee createEmployee(Employee e) {
		
		try {
			
			String sql = "Call ADD_EMPLOYEE(?,?,?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, Integer.toString(e.getSupervisor_id()));
			cs.setString(2, e.getName());
			cs.setString(3, e.getDepartment());
			cs.setString(4, setBoolean(e.isDepartment_head()));
			cs.setString(5, setBoolean(e.isBenco()));
			cs.setString(6, e.getEmail());
			cs.setString(7, e.getPassword());
			
			
			cs.execute();
			return e;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Employee getEmployee(int id) {

		try {
			
			String sql = "Select * From employees Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(id));
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Employee e = new Employee();
				
				e.setId(rs.getInt("ID"));
				e.setSupervisor_id(rs.getInt("SUPERVISOR_ID"));
				e.setName(rs.getString("NAME"));
				e.setDepartment(rs.getString("DEPARTMENT"));
				e.setDepartment_head(rs.getBoolean("DEPARTMENT_HEAD"));
				e.setBenco(rs.getBoolean("BENCO"));
				e.setEmail(rs.getString("EMAIL"));
				e.setPassword(rs.getString("PASSWORD"));
				
				rs.close();
				ps.close();
				return e;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Employee getLatestEmployee() {

		try {
			
			String sql = "Select * From employees Oder By ID desc";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Employee e = new Employee();
				
				e.setId(rs.getInt("ID"));
				e.setSupervisor_id(rs.getInt("SUPERVISOR_ID"));
				e.setName(rs.getString("NAME"));
				e.setDepartment(rs.getString("DEPARTMENT"));
				e.setDepartment_head(rs.getBoolean("DEPARTMENT_HEAD"));
				e.setBenco(rs.getBoolean("BENCO"));
				e.setEmail(rs.getString("EMAIL"));
				e.setPassword(rs.getString("PASSWORD"));
				
				rs.close();
				ps.close();
				return e;
			} 
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {

		try {
			
			String sql = "Select * From employees";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ResultSet rs = ps.executeQuery();
			
			List<Employee> list = new ArrayList<Employee>();
			while(rs.next()) {
				Employee e = new Employee();
				
				e.setId(rs.getInt("ID"));
				e.setSupervisor_id(rs.getInt("SUPERVISOR_ID"));
				e.setName(rs.getString("NAME"));
				e.setDepartment(rs.getString("DEPARTMENT"));
				e.setDepartment_head(rs.getBoolean("DEPARTMENT_HEAD"));
				e.setBenco(rs.getBoolean("BENCO"));
				e.setEmail(rs.getString("EMAIL"));
				e.setPassword(rs.getString("PASSWORD"));
				
				
				list.add(e);
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
	public List<Employee> GetAllBencos() {

		try {
			
			String sql = "Select * From employees Where BENCO = 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			List<Employee> list = new ArrayList<Employee>();
			while(rs.next()) {
				Employee e = new Employee();
				
				e.setId(rs.getInt("ID"));
				e.setSupervisor_id(rs.getInt("SUPERVISOR_ID"));
				e.setName(rs.getString("NAME"));
				e.setDepartment(rs.getString("DEPARTMENT"));
				e.setDepartment_head(rs.getBoolean("DEPARTMENT_HEAD"));
				e.setBenco(rs.getBoolean("BENCO"));
				e.setEmail(rs.getString("EMAIL"));
				e.setPassword(rs.getString("PASSWORD"));
				
				
				list.add(e);
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
	public Employee updateEmployee(Employee update) {

		try {
			
			String sql = "Update employees Set supervisor_id = ?, name = ?, department = ?, department_head = ?, benco = ?, email = ?, password = ? Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(update.getSupervisor_id()));
			ps.setString(2, update.getName());
			ps.setString(3, update.getDepartment());
			ps.setString(4, setBoolean(update.isDepartment_head()));
			ps.setString(5, setBoolean(update.isBenco()));
			ps.setString(6, update.getEmail());
			ps.setString(7, update.getPassword());
			ps.setString(8, Integer.toString(update.getId()));
			
			
			ps.execute();
			

			ps.close();
			return update;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public Employee updateEmployeeReimbursementReamaining(Employee update) {

		try {
			
			String sql = "Update employees Set REIMBURSEMENT_REMAINING = ? Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Double.toString(update.getReimbursement_remaining()));
			ps.setString(2, Integer.toString(update.getId()));
			
			
			ps.execute();
			
			ps.close();
			return update;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean deleteEmployee(int id) {

		try {
			
			String sql = "Delete employees Where ID = ?";
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
