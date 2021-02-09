package dev.rens.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.rens.models.AdditionalInfo;
import dev.rens.util.JDBCConnection;

public class AdditionalInfoRepositoryImpl implements AdditionalInfoRepository {
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public AdditionalInfo createAdditionalInfo(AdditionalInfo info) {

		try {
			
			String sql = "Call ADD_ADDITIONAL_INFO(?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, info.getInfo_needed());
			cs.setString(2, Integer.toString(info.getEmployee_id()));
			
			cs.execute();
			return info;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
//	private String setBoolean(boolean item) {
//		if(item) {
//			return "1";
//		}else {
//			return "0";
//		}
//	}

	@Override
	public AdditionalInfo getAdditionalInfo(int id) {

		try {
			
			String sql = "Select * From additional_info Where id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(id));
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				AdditionalInfo info = new AdditionalInfo();
				info.setId(rs.getInt("ID"));
				info.setInfo_needed(rs.getString("INFO_NEEDED"));
				info.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				
				rs.close();
				ps.close();
				return info;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public AdditionalInfo getLatestAdditionalInfo() {

		try {
			
			String sql = "Select * From additional_info Order By ID desc";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				AdditionalInfo info = new AdditionalInfo();
				info.setId(rs.getInt("ID"));
				info.setInfo_needed(rs.getString("INFO_NEEDED"));
				info.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				
				rs.close();
				ps.close();
				return info;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<AdditionalInfo> getAllAdditionalInfo() {
		try {
			
			String sql = "Select * From additional_info";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			List<AdditionalInfo> list = new ArrayList<AdditionalInfo>();
			while(rs.next()) {
				AdditionalInfo info = new AdditionalInfo();
				info.setId(rs.getInt("ID"));
				info.setInfo_needed(rs.getString("INFO_NEEDED"));
				info.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				
				
				list.add(info);
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
	public List<AdditionalInfo> getAllAdditionalInfoForEmployee(int employee_id) {
		try {
			
			String sql = "Select * From additional_info Where employee_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(employee_id));
			
			ResultSet rs = ps.executeQuery();
			
			List<AdditionalInfo> list = new ArrayList<AdditionalInfo>();
			while(rs.next()) {
				AdditionalInfo info = new AdditionalInfo();
				info.setId(rs.getInt("ID"));
				info.setInfo_needed(rs.getString("INFO_NEEDED"));
				info.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				
				
				list.add(info);
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
	public AdditionalInfo updateAdditionalInfo(AdditionalInfo update) {

		try {
			
			String sql = "Update additional_info Set info_needed = ?, employee_id = ? Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, update.getInfo_needed());
			ps.setString(2, Integer.toString(update.getEmployee_id()));
			ps.setString(3, Integer.toString(update.getId()));
			
			ps.execute();
			
			ps.close();
			return update;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean deleteAdditionalInfo(int id) {

		try {
			
			String sql = "Delete additional_info Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(id));
			ps.execute();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
