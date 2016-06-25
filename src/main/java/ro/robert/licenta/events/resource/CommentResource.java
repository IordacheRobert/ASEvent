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

import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.model.Comment;
import ro.robert.licenta.events.service.CommentService;
import ro.robert.licenta.events.service.CommentServiceImpl;
import ro.robert.licenta.events.service.ItemServiceImpl;
import ro.robert.licenta.events.util.Utils;

@Singleton
@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	private static final Logger LOG = Logger.getLogger(CommentResource.class);//optional
	
	private CommentService commentService;

	@InjectParam
	public void setCommentService(CommentServiceImpl commentService) {
		this.commentService = commentService;
	}

	@GET
	public Response getAllComments() {
		List<Comment> list = commentService.getListFromNamedParams(null);
		
		LOG.info("Found " + list.size() + " commet(s)");
		return Response.ok(list).build();
	}

	@POST
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response create(Comment comment) {
		LOG.info("Creating comment " + comment.getContent());
		
		if (comment.getCreated() == null) {
			comment.setCreated(new Date());
		}

		try {
			comment = commentService.saveOrUpdate(comment);
			return Response.ok(comment).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response update(@PathParam("id") Long id, Comment comment) {
		LOG.info("Updating comment " + comment.getContent());
		try {
			comment.setId(id);
			comment = commentService.saveOrUpdate(comment);
			return Response.ok(comment).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}

	@GET
	@Path("/{id}")
	public Response getComment(@PathParam("id") Long id) {
		Map<String, Object> params = Utils.create("id", id).build();
		Comment comment = commentService.getSingleFromNamedParams(params);
		
		LOG.info("Found " + comment.getContent());
		return Response.ok(comment).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteComment(@PathParam("id") Long id) {
		Integer deleted = commentService.delete(Comment.DELETE_BY_ID, id);
		LOG.info("Deleted " + deleted + " comment(s)");
		return Response.ok(deleted).build();
	}
	

	@POST
	@Path("/test")
	public Response test(Comment comment) {
		comment.setId(1000);
		return Response.ok(comment).build();
	}

}
