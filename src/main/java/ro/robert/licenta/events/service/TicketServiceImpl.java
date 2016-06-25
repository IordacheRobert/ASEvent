package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.dao.ItemRepository;
import ro.robert.licenta.events.dao.ItemRepositoryImpl;
import ro.robert.licenta.events.dao.TicketRepository;
import ro.robert.licenta.events.dao.TicketRepositoryImpl;
import ro.robert.licenta.events.model.Ticket;

@Singleton
public class TicketServiceImpl implements TicketService{

	private static final Logger LOG = Logger.getLogger(ItemServiceImpl.class);

	private TicketRepository ticketRepository;

    @InjectParam
    public void setItemRepository(TicketRepositoryImpl ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
	
	
	@Override
	public List<Ticket> getListFromNamedParams(Map<String, Object> params) {
		return ticketRepository.getListFromNamedParams(params);
	}

	@Override
	public Ticket saveOrUpdate(Ticket item) {
		return ticketRepository.saveOrUpdate(item);
	}

	@Override
	public Ticket getSingleFromNamedParams(Map<String, Object> params) {
		return ticketRepository.getSingleValue(params);
	}

	@Override
	public Integer delete(String deleteById, Long id) {
		return ticketRepository.delete(deleteById, id);
	}

}
