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

import ro.robert.licenta.events.model.Report;
import ro.robert.licenta.events.service.ReportService;
import ro.robert.licenta.events.service.ReportServiceImpl;
import ro.robert.licenta.events.util.Utils;

@Singleton
@Path("/reports")
@Produces(MediaType.APPLICATION_JSON)
public class ReportResource {
	private static final Logger LOG = Logger.getLogger(ReportResource.class);
	
	private ReportService reportService;

	@InjectParam
	public void setReportService(ReportServiceImpl reportService) {
		this.reportService = reportService;
	}

	@GET
	public Response getAllReports() {
		List<Report> list = reportService.getListFromNamedParams(null);
		
		LOG.info("Found " + list.size() + " reports");
		return Response.ok(list).build();
	}

	@POST
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response create(Report report) {
		LOG.info("Creating report " + report.getTitle());
		
		if (report.getCreated() == null) {
			report.setCreated(new Date());
		}

		try {
			report = reportService.saveOrUpdate(report);
			return Response.ok(report).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Response update(@PathParam("id") Long id, Report report) {
		LOG.info("Updating report " + report.getTitle());
		
		try {
			report.setId(id);
			report = reportService.saveOrUpdate(report);
			return Response.ok(report).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getLocalizedMessage()).build();
		}
	}

	@GET
	@Path("/{id}")
	public Response getreport(@PathParam("id") Long id) {
		Map<String, Object> params = Utils.create("id", id).build();
		Report report= reportService.getSingleFromNamedParams(params);
		LOG.info("Found " + report.getTitle());
		return Response.ok(report).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deletereport(@PathParam("id") Long id) {
		Integer deleted = reportService.delete(Report.DELETE_BY_ID, id);
		LOG.info("Deleted " + deleted + " report(s)");
		return Response.ok(deleted).build();
	}
}
