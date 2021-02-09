package dev.rens.repositories;

import java.util.List;

import dev.rens.models.Event;


public interface EventRepository {

	Event createEvent(Event e);
	Event getEvent(int id);
	List<Event> getAllEvents();
	
	Event updateEvent(Event update);
	boolean deleteEvent(int id);
}
