package ro.robert.licenta.events.dao;

import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.model.Location;

@Singleton
public class LocationRepositoryImpl extends BaseRepositoryImpl<Location> implements LocationRepository{

	public LocationRepositoryImpl() {
		super(Location.class);
		// TODO Auto-generated constructor stub
	}

}
