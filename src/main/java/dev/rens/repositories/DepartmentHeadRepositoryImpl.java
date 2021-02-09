package dev.rens.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.rens.models.DepartmentHead;
import dev.rens.util.JDBCConnection;

public class DepartmentHeadRepositoryImpl implements DepartmentHeadRepository {
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public List<DepartmentHead> getAllDepartmentHeads() {

		try {
			
			String sql = "Select * From department_heads";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			List<DepartmentHead> list = new ArrayList<DepartmentHead>();
			while(rs.next()) {
				DepartmentHead dh = new DepartmentHead();
				dh.setDepartment(rs.getString("DEPARTMENT"));
				dh.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				
				list.add(dh);
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
	public DepartmentHead findDepartmentHead(String department) {

		try {
			
			String sql = "Select * From department_heads Where DEPARTMENT = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, department);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				DepartmentHead dh = new DepartmentHead();
				dh.setDepartment(rs.getString("DEPARTMENT"));
				dh.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				
				rs.close();
				ps.close();
				return dh;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
