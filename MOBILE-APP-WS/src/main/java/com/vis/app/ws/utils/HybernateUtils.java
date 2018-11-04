/**
 * 
 */
package com.vis.app.ws.utils;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Visu
 *
 */
public class HybernateUtils {
	
	public static final SessionFactory sessionFactory;
	
	static {
		Configuration conf = new Configuration();
		conf.configure();
		
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			
		}catch(HibernateException e) {
			System.err.println("Initail session factory connection failed" + e);
			throw new ExceptionInInitializerError(e);
		}
		
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	
	}

}
