package ro.robert.licenta.events.dao;

import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.model.Ticket;

@Singleton
public class TicketRepositoryImpl extends BaseRepositoryImpl<Ticket> implements TicketRepository{

	public TicketRepositoryImpl() {
		super(Ticket.class);
		// TODO Auto-generated constructor stub
	}

}
