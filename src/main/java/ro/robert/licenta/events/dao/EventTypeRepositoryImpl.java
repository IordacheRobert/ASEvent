package ro.robert.licenta.events.dao;

import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.model.EventType;

@Singleton
public class EventTypeRepositoryImpl extends BaseRepositoryImpl<EventType> implements EventTypeRepository {

	public EventTypeRepositoryImpl() {
		super(EventType.class);
		// TODO Auto-generated constructor stub
	}

}
