package ro.robert.licenta.events.resource;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.model.Item;
import ro.robert.licenta.events.model.Ticket;
import ro.robert.licenta.events.service.ItemService;
import ro.robert.licenta.events.service.ItemServiceImpl;
import ro.robert.licenta.events.service.TicketService;
import ro.robert.licenta.events.service.TicketServiceImpl;
import ro.robert.licenta.events.util.Utils;

@Singleton
@Path("/tickets")
@Produces(MediaType.APPLICATION_JSON)
public class TicketResource {
	private static final Logger LOG = Logger.getLogger(TicketResource.class);//optional
	
	private TicketService ticketService;

	@InjectParam
	public void setTicketService(TicketServiceImpl ticketService) {
		this.ticketService = ticketService;
	}

	@GET
	public Response getAlltickets() {
		List<Ticket> list = ticketService.getListFromNamedParams(null);
		
		LOG.info("Found " + list.size() + " tickets");
		return Response.ok(list).build();
	}

	@POST
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response create(Ticket ticket) {
		LOG.info("Creating ticket " );
		
		if (ticket.getCreated() == null) {
			ticket.setCreated(new Date());
		}

		try {
			ticket = ticketService.saveOrUpdate(ticket);
			return Response.ok(ticket).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response update(@PathParam("id") Long id,Ticket ticket) {
		LOG.info("Updating ticket " );
		
		try {
			ticket.setId(id);
			ticket = ticketService.saveOrUpdate(ticket);
			return Response.ok(ticket).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}

	@GET
	@Path("/{id}")
	public Response getTicket(@PathParam("id") Long id) {
		Map<String, Object> params = Utils.create("id", id).build();
		Ticket ticket = ticketService.getSingleFromNamedParams(params);
		LOG.info("Found ticket " );
		return Response.ok(ticket).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteItem(@PathParam("id") Long id) {
		Integer deleted = ticketService.delete(Ticket.DELETE_BY_ID, id);
		LOG.info("Deleted " + deleted + " ticket(s)");
		return Response.ok(deleted).build();
	}
}
