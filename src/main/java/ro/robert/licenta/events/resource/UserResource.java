package ro.robert.licenta.events.resource;

import java.awt.geom.QuadCurve2D;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.model.Event;
import ro.robert.licenta.events.model.Location;
import ro.robert.licenta.events.model.User;
import ro.robert.licenta.events.service.UserService;
import ro.robert.licenta.events.service.UserServiceImpl;
import ro.robert.licenta.events.util.Utils;

@Singleton
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	
private static final Logger LOG = Logger.getLogger(UserResource.class);//optional
	
	private UserService userService;

	@InjectParam
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	@GET
	public Response getAllUsers() {
		List<User> list = userService.getListFromNamedParams(null);
		
		LOG.info("Found " + list.size() + " users");
		return Response.ok(list).build();
	}
	

	@POST
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response create(User user) {
		LOG.info("Creating user " + user.getEmail());
		
		if (user.getCreated() == null) {
			user.setCreated(new Date());
		}

		try {
			if(user.getLocation()==null){
				Location location=new Location();
				location.setId(15);
				user.setLocation(location);
			}
			
			if(user.getPhoneNumber()==null){
				user.setPhoneNumber("0760000000");
			}
			
			if(user.getAvatarURL()==null){
				user.setAvatarURL("webapi/files/images/bear.jpg");
			}
			if(user.getType()==0){
				user.setType(1);
			}
			user = userService.saveOrUpdate(user);
			return Response.ok(user).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response update(@PathParam("id") Long id, User user) {
		LOG.info("Updating user " + user.getEmail());
		
		try {
			user.setId(id);
			user = userService.saveOrUpdate(user);
			return Response.ok(user).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}

	@GET
	@Path("/{id}")
	public Response getUser(@PathParam("id") Long id) {
		Map<String, Object> params = Utils.create("id", id).build();
		User user = userService.getSingleFromNamedParams(params);
		LOG.info("Found " + user.getEmail());
		return Response.ok(user).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteItem(@PathParam("id") Long id) {
		Integer deleted = userService.delete(User.DELETE_BY_ID, id);
		LOG.info("Deleted " + deleted + " user(s)");
		return Response.ok(deleted).build();
	}
	
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByEmailAndPassword(@HeaderParam("email")String email,@HeaderParam("password")String password){
		Map<String, Object> params = Utils.create("email", email).add("password", password).build();
		User user=userService.getSingleFromNamedParams(params);
		return Response.ok(user).build();
	}
	
	@GET
	@Path("/{id}/events")
	public Response getEventsUser(@PathParam("id") Long id) {
		Map<String, Object> params = Utils.create("id", id).build();
		List<Event> list=userService.getEventsUser(params);
		LOG.info("Found " +list.size()+ "events");
		return Response.ok(list).build();
	}
	
	@GET
	@Path("/email/{email}")
	public Response getUserByEmail(@PathParam("email") String email) {
		Map<String, Object> params = Utils.create("email", email).build();
		User user=userService.getSingleFromNamedParams(params);
		return Response.ok(user).build();
	}

}
