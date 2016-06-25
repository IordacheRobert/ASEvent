package ro.robert.licenta.events.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
	private static SessionFactory sessionFactory=null;

	private HibernateUtils() {
		super();
	}

	public static synchronized SessionFactory getSessionFactory(){
		if(sessionFactory==null){
			try{
				sessionFactory = new Configuration().configure().buildSessionFactory();
				System.out.println("A SessionFactory was created.");
		      }catch (Throwable ex) { 
		         System.err.println("Failed to create sessionFactory object." + ex);
		         throw new ExceptionInInitializerError(ex); 
		      }
		}
		return sessionFactory;
	}
	

}
