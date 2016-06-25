package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.dao.ReportRepository;
import ro.robert.licenta.events.dao.ReportRepositoryImpl;
import ro.robert.licenta.events.model.Report;

@Singleton
public class ReportServiceImpl implements ReportService{

	
	private static final Logger LOG = Logger.getLogger(ItemServiceImpl.class);

    private ReportRepository reportRepository;

    @InjectParam
    public void setItemRepository(ReportRepositoryImpl reportRepository) {
        this.reportRepository = reportRepository;
    }
	
	@Override
	public List<Report> getListFromNamedParams(Map<String, Object> params) {
		return reportRepository.getListFromNamedParams(params);
	}

	@Override
	public Report saveOrUpdate(Report item) {
		return reportRepository.saveOrUpdate(item);
	}

	@Override
	public Report getSingleFromNamedParams(Map<String, Object> params) {
		return reportRepository.getSingleValue(params);
	}

	@Override
	public Integer delete(String deleteById, Long id) {
		return reportRepository.delete(deleteById, id);
	}

}
