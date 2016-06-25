package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;

import ro.robert.licenta.events.model.Report;

public interface ReportService {
	
	
	List<Report> getListFromNamedParams(Map<String, Object> params);

	Report saveOrUpdate(Report item);

	Report getSingleFromNamedParams(Map<String, Object> params);

    Integer delete(String deleteById, Long id);

}
