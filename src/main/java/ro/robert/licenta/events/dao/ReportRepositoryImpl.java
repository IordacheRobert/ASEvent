package ro.robert.licenta.events.dao;

import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.model.Report;


@Singleton
public class ReportRepositoryImpl extends BaseRepositoryImpl<Report> implements ReportRepository{

	public ReportRepositoryImpl() {
		super(Report.class);
		// TODO Auto-generated constructor stub
	}

}
