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

import ro.robert.licenta.events.model.Event;
import ro.robert.licenta.events.model.Item;
import ro.robert.licenta.events.service.EventService;
import ro.robert.licenta.events.service.EventServiceImpl;
import ro.robert.licenta.events.util.Utils;

@Singleton
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {
	private static final Logger LOG = Logger.getLogger(EventResource.class);//optional
	
	private EventService eventService;
	
	@InjectParam
	public void setEventService(EventServiceImpl eventServiceImpl){
		this.eventService=eventServiceImpl;
	}
	
	@GET
	public Response getAllEvents() {
		List<Event> list = eventService.getListFromNamedParams(null);
		
		LOG.info("Found " + list.size() + " events");
		return Response.ok(list).build();
	}
	
	
//	// neimplementata
//	@GET
//	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
//	public Response getAllEventsAtDistance() {
//		List<Event> list = eventService.getListFromNamedParams(null);
//		
//		LOG.info("Found " + list.size() + " events");
//		return Response.ok(list).build();
//	}
//	
	
	
	
	@POST
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response create(Event event) {
		LOG.info("Creating item " + event.getTitle());
		
		if (event.getCreated() == null) {
			event.setCreated(new Date());
		}

		try {
			event = eventService.saveOrUpdate(event);
			return Response.ok(event).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response update(@PathParam("id") Long id, Event event) {
		LOG.info("Updating item " + event.getTitle());
		
		try {
			event.setId(id);
			event = eventService.saveOrUpdate(event);
			return Response.ok(event).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}
	
	@GET
	@Path("/{id}")
	public Response getItem(@PathParam("id") Long id) {
		Map<String, Object> params = Utils.create("id", id).build();
		Event event = eventService.getSingleFromNamedParams(params);
		LOG.info("Found " + event.getTitle());
		return Response.ok(event).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteItem(@PathParam("id") Long id) {
		Integer deleted = eventService.delete(Event.DELETE_BY_ID, id);
		LOG.info("Deleted " + deleted + " item(s)");
		return Response.ok(deleted).build();
	}
	
}
