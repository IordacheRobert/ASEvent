package ro.robert.licenta.events.dao;

import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.model.Event;

@Singleton
public class EventRepositoryImpl extends BaseRepositoryImpl<Event> implements EventRepository{

	public EventRepositoryImpl() {
		super(Event.class);
	}

}
