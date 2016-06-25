package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;

import ro.robert.licenta.events.model.EventType;

public interface EventTypeService {
	
	List<EventType> getListFromNamedParams(Map<String, Object> params);

	EventType saveOrUpdate(EventType item);

	EventType getSingleFromNamedParams(Map<String, Object> params);

    Integer delete(String deleteById, Long id);

}
