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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.model.Location;
import ro.robert.licenta.events.service.LocationService;
import ro.robert.licenta.events.service.LocationServiceImpl;
import ro.robert.licenta.events.util.Utils;

@Singleton
@Path("/locations")
@Produces(MediaType.APPLICATION_JSON)
public class LocationResource {
private static final Logger LOG = Logger.getLogger(LocationResource.class);//optional
	
	private LocationService locationService;

	@InjectParam
	public void setlocationService(LocationServiceImpl locationService) {
		this.locationService = locationService;
	}

	@GET
	public Response getAllLocations() {
		List<Location> list = locationService.getListFromNamedParams(null);
		LOG.info("Found " + list.size() + " locations");
		return Response.ok(list).build();
	}

	@POST
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response create(Location location) {
		LOG.info("Creating location " + location.getAddress());
		
		
		Map<String,Object> params=Utils.create("latitude",location.getLatitude())
				.add("longitude", location.getLongitude()).build();
		
//		Location temp=null;
//		try{
//			 temp=locationService.getSingleFromNamedParams(params);
//		}catch(WebApplicationException ex){
//			System.out.println("Exista locatia!");
//			location=temp;
//		}
		
		if (location.getCreated() == null) {
			location.setCreated(new Date());
		}
		
		try {
			location = locationService.saveOrUpdate(location);
			return Response.ok(location).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response update(@PathParam("id") Long id, Location location) {
		LOG.info("Updating location " + location.getAddress());
		
		try {
			location.setId(id);
			location = locationService.saveOrUpdate(location);
			return Response.ok(location).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}

	@GET
	@Path("/{id}")
	public Response getLocation(@PathParam("id") Long id) {
		Map<String, Object> params = Utils.create("id", id).build();
		Location location = locationService.getSingleFromNamedParams(params);
		LOG.info("Found " + location.getAddress());
		return Response.ok(location).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteLocation(@PathParam("id") Long id) {
		Integer deleted = locationService.delete(Location.DELETE_BY_ID, id);
		LOG.info("Deleted " + deleted + " location(s)");
		return Response.ok(deleted).build();
	}
	
	
}
