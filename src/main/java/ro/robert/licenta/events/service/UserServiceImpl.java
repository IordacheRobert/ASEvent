package ro.robert.licenta.events.service;

import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;

import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.dao.UserRepositoryImpl;
import ro.robert.licenta.events.model.Event;
import ro.robert.licenta.events.model.User;

@Singleton
public class UserServiceImpl implements UserService{

	private static final Logger LOG = Logger.getLogger(ItemServiceImpl.class);

    private UserRepositoryImpl userRepository;

    @InjectParam
    public void setItemRepository(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }
	
	
	@Override
	public List<User> getListFromNamedParams(Map<String, Object> params) {
		return userRepository.getListFromNamedParams(params);
	}

	@Override
	public User saveOrUpdate(User item) {
		return userRepository.saveOrUpdate(item);
	}

	@Override
	public User getSingleFromNamedParams(Map<String, Object> params) {
		return userRepository.getSingleValue(params);
	}

	@Override
	public Integer delete(String deleteById, Long id) {
		return userRepository.delete(deleteById, id);
	}


	@Override
	public List<Event> getEventsUser(Map<String, Object> params) {
		return userRepository.getEventsUser(params);
	}
	
	 
	
}
