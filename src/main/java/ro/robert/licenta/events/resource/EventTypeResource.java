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

import ro.robert.licenta.events.model.EventType;
import ro.robert.licenta.events.model.Item;
import ro.robert.licenta.events.service.EventTypeService;
import ro.robert.licenta.events.service.EventTypeServiceImpl;
import ro.robert.licenta.events.service.ItemService;
import ro.robert.licenta.events.service.ItemServiceImpl;
import ro.robert.licenta.events.util.Utils;

@Singleton
@Path("/event-types")
@Produces(MediaType.APPLICATION_JSON)
public class EventTypeResource {
	private static final Logger LOG = Logger.getLogger(EventTypeResource.class);//optional
	
	

	private EventTypeService eventTypeService;

	@InjectParam
	public void setEventTypeService(EventTypeServiceImpl eventTypeService) {
		this.eventTypeService = eventTypeService;
	}
	
	
	@GET
	public Response getAllEventTypes() {
		List<EventType> list = eventTypeService.getListFromNamedParams(null);
		
		LOG.info("Found " + list.size() + " types");
		return Response.ok(list).build();
	}

	@POST
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response create(EventType eventType) {
		LOG.info("Creating item " + eventType.getTitle());
		
		if (eventType.getCreated() == null) {
			eventType.setCreated(new Date());
		}

		try {
			eventType = eventTypeService.saveOrUpdate(eventType);
			return Response.ok(eventType).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}
	
	

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response update(@PathParam("id") Long id, EventType eventType) {
		LOG.info("Updating eventType " + eventType.getTitle());
		
		try {
			eventType.setId(id);
			eventType = eventTypeService.saveOrUpdate(eventType);
			return Response.ok(eventType).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}

	@GET
	@Path("/{id}")
	public Response getEventType(@PathParam("id") Long id) {
		Map<String, Object> params = Utils.create("id", id).build();
		EventType eventType = eventTypeService.getSingleFromNamedParams(params);
		LOG.info("Found " + eventType.getTitle());
		return Response.ok(eventType).build();
	}

	
	
	@DELETE
	@Path("/{id}")
	public Response deleteItem(@PathParam("id") Long id) {
		Integer deleted = eventTypeService.delete(EventType.DELETE_BY_ID, id);
		LOG.info("Deleted " + deleted + " eventType(s)");
		return Response.ok(deleted).build();
	}
	

}
