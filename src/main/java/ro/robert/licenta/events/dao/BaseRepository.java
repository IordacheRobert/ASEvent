package ro.robert.licenta.events.dao;


import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ro.robert.licenta.events.model.BaseEntity;



public interface BaseRepository<T extends BaseEntity> {

    void setFactory(SessionFactory factory) ;

    Session getSession() ;
    
    T getSingleValue(Map<String, Object> params);

    T saveOrUpdate(T object);

    List<T> getListFromNamedParams(Map<String, Object> params);
    
    List<T> getListFromNamedParams(Map<String, Object> params, Integer page, Integer pageSize);

    int delete(final String queryName, final Long id);

}
