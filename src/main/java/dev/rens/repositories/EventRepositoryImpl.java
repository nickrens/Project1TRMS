package dev.rens.repositories;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.rens.models.Event;
import dev.rens.util.JDBCConnection;

public class EventRepositoryImpl implements EventRepository{
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public Event createEvent(Event e) {

		try {
			
			String sql = "Call ADD_EVENT(?,?,?,?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, e.getEvent_start_date());
			cs.setString(2, e.getEvent_end_date());
			cs.setString(3, e.getEvent_type());
			cs.setString(4, e.getLocation());
			cs.setString(5, e.getDescription());
			cs.setString(6, Double.toString(e.getCost()));
			cs.setString(7, e.getGrading_format());
			cs.setString(8, e.getEvent_timeframe());
			
			cs.execute();
			return e;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Event getEvent(int id) {

		try {
			
			String sql = "Select * From events Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(id));
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Event e = new Event();
				
				e.setId(rs.getInt("ID"));
				e.setEvent_start_date(rs.getString("EVENT_START_DATE"));
				e.setEvent_end_date(rs.getString("EVENT_END_DATE"));
				e.setEvent_type(rs.getString("EVENT_TYPE"));
				e.setLocation(rs.getString("LOCATION"));
				e.setDescription(rs.getString("DESCRIPTION"));
				e.setCost(rs.getDouble("COST"));
				e.setGrading_format(rs.getString("GRADING_FORMAT"));
				e.setEvent_timeframe(rs.getString("EVENT_TIMEFRAME"));
				
				ps.close();
				rs.close();
				return e;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Event getLatestEvent() {

		try {
			
			String sql = "Select * From events Order By ID desc";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Event e = new Event();
				
				e.setId(rs.getInt("ID"));
				e.setEvent_start_date(rs.getString("EVENT_START_DATE"));
				e.setEvent_end_date(rs.getString("EVENT_END_DATE"));
				e.setEvent_type(rs.getString("EVENT_TYPE"));
				e.setLocation(rs.getString("LOCATION"));
				e.setDescription(rs.getString("DESCRIPTION"));
				e.setCost(rs.getDouble("COST"));
				e.setGrading_format(rs.getString("GRADING_FORMAT"));
				e.setEvent_timeframe(rs.getString("EVENT_TIMEFRAME"));
				
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
	public List<Event> getAllEvents() {

		try {
			
			String sql = "Select * From events";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			List<Event> list = new ArrayList<Event>();
			while(rs.next()) {
				Event e = new Event();
				
				e.setId(rs.getInt("ID"));
				e.setEvent_start_date(rs.getString("EVENT_START_DATE"));
				e.setEvent_end_date(rs.getString("EVENT_END_DATE"));
				e.setEvent_type(rs.getString("EVENT_TYPE"));
				e.setLocation(rs.getString("LOCATION"));
				e.setDescription(rs.getString("DESCRIPTION"));
				e.setCost(rs.getDouble("COST"));
				e.setGrading_format(rs.getString("GRADING_FORMAT"));
				e.setEvent_timeframe(rs.getString("EVENT_TIMEFRAME"));
				
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
	public Event updateEvent(Event update) {

try {
			
			String sql = "Update events Set event_start_date = ?, event_end_date = ?, event_type = ?, location = ?, description = ?, cost = ?, grading_format = ?, "
					+ "event_timeframe = ? Where ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, update.getEvent_start_date());
			ps.setString(2, update.getEvent_end_date());
			ps.setString(3, update.getEvent_type());
			ps.setString(4, update.getLocation());
			ps.setString(5, update.getDescription());
			ps.setString(6, Double.toString(update.getCost()));
			ps.setString(7, update.getGrading_format());
			ps.setString(8, update.getEvent_timeframe());
			ps.setString(9, Integer.toString(update.getId()));
			
			ps.execute();
			
			ps.close();
			return update;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean deleteEvent(int id) {

		try {
			
			String sql = "Delete events Where ID = ?";
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
