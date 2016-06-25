package ro.robert.licenta.events.dao;

import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

import org.apache.log4j.Logger;

import ro.robert.licenta.events.model.BaseEntity;
import ro.robert.licenta.events.util.HibernateUtils;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.sun.jersey.api.NotFoundException;


///CRUD OPERATIONS
public class BaseRepositoryImpl<T extends BaseEntity> implements BaseRepository<T> {
	
	private SessionFactory factory;
    private static final Logger LOG = Logger.getLogger(BaseRepositoryImpl.class);
    protected Class<T> clazz;
    
    

    public BaseRepositoryImpl(Class<T> clazz) {
		super();
		this.clazz = clazz;
		this.factory=HibernateUtils.getSessionFactory();
	}

	public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public Session getSession() {
        return factory.openSession();
    }

    
    @Override
	public T getSingleValue(Map<String, Object> params) {
    	T object=null;
		Session session=getSession();
		Criteria criteria=session.createCriteria(clazz);
		Transaction transaction=null;
		
		if(params!=null)
		for (Map.Entry<String, Object> param : params.entrySet()) {
			criteria.add(Restrictions.eq(param.getKey(), param.getValue()));
        }
		
		try{
			transaction=session.beginTransaction();
			
			object=clazz.cast(criteria.uniqueResult());
			if(object==null)throw new  WebApplicationException(404);
			transaction.commit();
			
		}catch(HibernateException ex){
			ex.printStackTrace();
			if(transaction!=null)transaction.rollback();
		}finally {
			session.close();
		}
		
		return object;
	}
    
    @Override
	public List<T> getListFromNamedParams(Map<String, Object> params) {
		List<T> list=null;
		Session session=getSession();
		Criteria criteria=session.createCriteria(clazz);
		Transaction transaction=null;
		
		if(params!=null)
		for (Map.Entry<String, Object> param : params.entrySet()) {
			criteria.add(Restrictions.eq(param.getKey(), param.getValue()));
        }
		
		try{
			transaction=session.beginTransaction();
			
			list=criteria.list();
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
	
	@Override
	public List<T> getListFromNamedParams(Map<String, Object> params, Integer page, Integer pageSize) {
		List<T> list=null;
		Session session=getSession();
		Criteria criteria=session.createCriteria(clazz);
		Transaction transaction=null;
		
		if(params!=null)
		for (Map.Entry<String, Object> param : params.entrySet()) {
			criteria.add(Restrictions.eq(param.getKey(), param.getValue()));
        }
		
		try{
			transaction=session.beginTransaction();
			
			if (pageSize != null) {
	            criteria.setMaxResults(pageSize);

	            if (page != null) {
	                criteria.setFirstResult(page * pageSize);
	            }
	        }
			
			list=criteria.list();
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

    @Override
	public T saveOrUpdate(T object) {
		Session session=getSession();
		Transaction transaction=null;

		try{
			transaction=session.beginTransaction();
			
			if(object.getId()>0){
				System.out.println("A intrat pe merge");
				object= (T) session.merge(object);
			}else{
				System.out.println("A intrat pe persist");
				session.persist(object);
			}
			
			transaction.commit();
			
		}catch(HibernateException ex){
			ex.printStackTrace();
			if(transaction!=null)transaction.rollback();
		}finally {
			session.close();
		}
		
		return object;
	}
	
	@Override
	public int delete(final String queryName, final Long id) {
		int rez = 0;
		Session session=getSession();
		Query query=session.createQuery(queryName);
		
		Transaction transaction=null;
		
		try{
			transaction=session.beginTransaction();
		
			query.setParameter("id",id);
			
			
			rez=query.executeUpdate();
			if(rez==0)throw new  WebApplicationException(404);
			transaction.commit();
			
		}catch(HibernateException ex){
			ex.printStackTrace();
			if(transaction!=null)transaction.rollback();
		}finally {
			session.close();
		}
		
		return rez;
	}
    
 
    
}
