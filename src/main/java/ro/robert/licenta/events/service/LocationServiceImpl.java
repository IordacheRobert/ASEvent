package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.dao.LocationRepository;
import ro.robert.licenta.events.dao.LocationRepositoryImpl;
import ro.robert.licenta.events.model.Location;

@Singleton
public class LocationServiceImpl implements LocationService{

	private static final Logger LOG = Logger.getLogger(ItemServiceImpl.class);

    private LocationRepository locationRepository;

   
    @InjectParam
    public void setItemRepository(LocationRepositoryImpl locationRepository) {
        this.locationRepository = locationRepository;
    }
	
    
	
	@Override
	public List<Location> getListFromNamedParams(Map<String, Object> params) {
		return locationRepository.getListFromNamedParams(params);
	}

	@Override
	public Location saveOrUpdate(Location item) {
		return locationRepository.saveOrUpdate(item);
	}

	@Override
	public Location getSingleFromNamedParams(Map<String, Object> params) {
		return locationRepository.getSingleValue(params);
	}

	@Override
	public Integer delete(String deleteById, Long id) {
		return locationRepository.delete(deleteById, id);
	}

}
