package ro.robert.licenta.events.dao;

import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.sun.jersey.spi.resource.Singleton;

import ro.robert.licenta.events.model.Event;
import ro.robert.licenta.events.model.User;

@Singleton
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

	public UserRepositoryImpl() {
		super(User.class);
	}

	
	public List<Event> getEventsUser(Map<String, Object> params) {
		List<Event> list=null;
		Session session=getSession();
		Criteria criteria=session.createCriteria(User.class);
		Transaction transaction=null;
		
		if(params!=null)
		for (Map.Entry<String, Object> param : params.entrySet()) {
			criteria.add(Restrictions.eq(param.getKey(), param.getValue()));
        }
		
		try{
			transaction=session.beginTransaction();
			
			User user=(User) criteria.uniqueResult();
			list=user.getMyEvents();
			if(list==null || list.size()==0)throw new  WebApplicationException(404);
			transaction.commit();
			
		}catch(HibernateException ex){
			ex.printStackTrace();
			if(transaction!=null)transaction.rollback();
		}finally {
			session.close();
		}
		
		return list;
	}
}
