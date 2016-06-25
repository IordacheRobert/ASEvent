package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;

import ro.robert.licenta.events.model.Ticket;

public interface TicketService {
	
	List<Ticket> getListFromNamedParams(Map<String, Object> params);

	Ticket saveOrUpdate(Ticket item);

	Ticket getSingleFromNamedParams(Map<String, Object> params);

    Integer delete(String deleteById, Long id);


}
