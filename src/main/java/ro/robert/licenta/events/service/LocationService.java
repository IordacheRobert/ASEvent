package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;

import ro.robert.licenta.events.model.Location;

public interface LocationService {
	
	List<Location> getListFromNamedParams(Map<String, Object> params);

	Location saveOrUpdate(Location item);

	Location getSingleFromNamedParams(Map<String, Object> params);

    Integer delete(String deleteById, Long id);

}
