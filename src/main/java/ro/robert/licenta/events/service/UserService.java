package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;

import ro.robert.licenta.events.model.Event;
import ro.robert.licenta.events.model.User;

public interface UserService {

	
	List<User> getListFromNamedParams(Map<String, Object> params);

	User saveOrUpdate(User item);

	User getSingleFromNamedParams(Map<String, Object> params);

    Integer delete(String deleteById, Long id);
    
    public List<Event> getEventsUser(Map<String, Object> params);
}
