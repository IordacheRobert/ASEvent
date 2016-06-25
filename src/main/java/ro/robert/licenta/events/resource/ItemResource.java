package ro.robert.licenta.events.resource;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.model.Item;
import ro.robert.licenta.events.service.ItemService;
import ro.robert.licenta.events.service.ItemServiceImpl;
import ro.robert.licenta.events.util.Utils;

import org.apache.log4j.Logger;

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
import java.util.Date;
import java.util.List;
import java.util.Map;


@Singleton
@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {
	private static final Logger LOG = Logger.getLogger(ItemResource.class);//optional
	
	private ItemService itemService;

	@InjectParam
	public void setItemService(ItemServiceImpl itemService) {
		this.itemService = itemService;
	}

	@GET
	public Response getAllItems() {
		List<Item> list = itemService.getListFromNamedParams(null);
		
		LOG.info("Found " + list.size() + " items");
		return Response.ok(list).build();
	}

	@POST
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response create(Item item) {
		LOG.info("Creating item " + item.getText());
		
		if (item.getCreated() == null) {
			item.setCreated(new Date());
		}

		try {
			item = itemService.saveOrUpdate(item);
			return Response.ok(item).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response update(@PathParam("id") Long id, Item item) {
		LOG.info("Updating item " + item.getText());
		
		try {
			item.setId(id);
			item = itemService.saveOrUpdate(item);
			return Response.ok(item).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}

	@GET
	@Path("/{id}")
	public Response getItem(@PathParam("id") Long id) {
		Map<String, Object> params = Utils.create("id", id).build();
		Item item = itemService.getSingleFromNamedParams(params);
		LOG.info("Found " + item.getText());
		return Response.ok(item).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteItem(@PathParam("id") Long id) {
		Integer deleted = itemService.delete(Item.DELETE_BY_ID, id);
		LOG.info("Deleted " + deleted + " item(s)");
		return Response.ok(deleted).build();
	}

}
