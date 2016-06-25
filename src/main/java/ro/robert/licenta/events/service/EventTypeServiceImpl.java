package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.dao.EventTypeRepository;
import ro.robert.licenta.events.dao.EventTypeRepositoryImpl;
import ro.robert.licenta.events.model.EventType;

@Singleton
public class EventTypeServiceImpl implements EventTypeService{

	
	private static final Logger LOG = Logger.getLogger(ItemServiceImpl.class);

    private EventTypeRepository eventTypeRepository;

    @InjectParam
    public void setItemRepository(EventTypeRepositoryImpl eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

	@Override
	public List<EventType> getListFromNamedParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return eventTypeRepository.getListFromNamedParams(params);
	}

	@Override
	public EventType saveOrUpdate(EventType item) {
		// TODO Auto-generated method stub
		return eventTypeRepository.saveOrUpdate(item);
	}

	@Override
	public EventType getSingleFromNamedParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return eventTypeRepository.getSingleValue(params);
	}

	@Override
	public Integer delete(String deleteById, Long id) {
		// TODO Auto-generated method stub
		return eventTypeRepository.delete(deleteById, id);
	}
	
	

}
