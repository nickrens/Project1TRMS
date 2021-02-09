package dev.rens.services;

import java.util.List;

import dev.rens.models.Event;
import dev.rens.repositories.EventRepositoryImpl;

public class EventService {

	public EventRepositoryImpl erepo = new EventRepositoryImpl();
	
	public Event createEvent(Event e) {
		return erepo.createEvent(e);
	}
	public Event getEvent(int id) {
		return erepo.getEvent(id);
	}
	public Event getLatestEvent() {
		return erepo.getLatestEvent();
	}
	public List<Event> getAllEvents() {
		return erepo.getAllEvents();
	}
	
	public Event updateEvent(Event update) {
		return erepo.updateEvent(update);
	}
	public boolean deleteEvent(int id) {
		return erepo.deleteEvent(id);
	}
}
