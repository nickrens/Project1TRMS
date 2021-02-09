package dev.rens.models;

public class Event {

	private int id;
	private String event_start_date;
	private String event_end_date;
	private String event_type;
	private String location;
	private String description;
	private double cost;
	private String grading_format;
	private String event_timeframe;
	
	public Event() {
		super();
	}

	public Event(int id, String event_start_date, String event_end_date, String event_type, String location,
			String description, double cost, String grading_format, String event_timeframe) {
		super();
		this.id = id;
		this.event_start_date = event_start_date;
		this.event_end_date = event_end_date;
		this.event_type = event_type;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.grading_format = grading_format;
		this.event_timeframe = event_timeframe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEvent_start_date() {
		return event_start_date;
	}

	public void setEvent_start_date(String event_start_date) {
		this.event_start_date = event_start_date;
	}

	public String getEvent_end_date() {
		return event_end_date;
	}

	public void setEvent_end_date(String event_end_date) {
		this.event_end_date = event_end_date;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getGrading_format() {
		return grading_format;
	}

	public void setGrading_format(String grading_format) {
		this.grading_format = grading_format;
	}

	public String getEvent_timeframe() {
		return event_timeframe;
	}

	public void setEvent_timeframe(String event_timeframe) {
		this.event_timeframe = event_timeframe;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", event_start_date=" + event_start_date + ", event_end_date=" + event_end_date
				+ ", event_type=" + event_type + ", location=" + location + ", description=" + description + ", cost="
				+ cost + ", grading_format=" + grading_format + ", event_timeframe="
				+ event_timeframe + "]";
	}
	
	
	
	
	
}
