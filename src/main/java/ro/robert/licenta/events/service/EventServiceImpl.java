package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.dao.EventRepository;
import ro.robert.licenta.events.dao.EventRepositoryImpl;
import ro.robert.licenta.events.model.Event;

@Singleton
public class EventServiceImpl implements EventService {
	
	private static final Logger LOG = Logger.getLogger(ItemServiceImpl.class);

    private EventRepository eventRepository;

    @InjectParam
    public void setItemRepository(EventRepositoryImpl eventRepository) {
        this.eventRepository = eventRepository;
    }


	@Override
	public List<Event> getListFromNamedParams(Map<String, Object> params) {
		return eventRepository.getListFromNamedParams(params);
	}

	@Override
	public Event saveOrUpdate(Event item) {
		return eventRepository.saveOrUpdate(item);
	}

	@Override
	public Event getSingleFromNamedParams(Map<String, Object> params) {
		return eventRepository.getSingleValue(params);
	}

	@Override
	public Integer delete(String deleteById, Long id) {
		// TODO Auto-generated method stub
		return eventRepository.delete(deleteById, id);
	}

}
