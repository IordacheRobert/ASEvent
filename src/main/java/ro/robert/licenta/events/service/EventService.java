package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;

import ro.robert.licenta.events.model.Event;

public interface EventService {
	
	List<Event> getListFromNamedParams(Map<String, Object> params);

    Event saveOrUpdate(Event item);

    Event getSingleFromNamedParams(Map<String, Object> params);

    Integer delete(String deleteById, Long id);
}
