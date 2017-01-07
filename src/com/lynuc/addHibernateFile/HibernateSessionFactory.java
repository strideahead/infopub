package com.lynuc.addHibernateFile;

import org.hibernate.Session;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateSessionFactory {
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";

    /*它的功用是为每一个使用该变量的线程都提供一个变量值的副本，是每一个线程都可以独立地改变自己的副本，而不会和其它线程的副本冲突。从线程的角度看，就好像每一个线程都完全拥有该变量。这样可以实现子线程的安全。
*/
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<>();

   
    private static final Configuration cfg = new Configuration();

    private static SessionFactory sessionFactory;
    public HibernateSessionFactory(){
    }
	@SuppressWarnings({ "unused", "deprecation" })
	public static SessionFactory config(){
        try{
            Configuration configuration=new Configuration();
            Configuration configure = configuration.configure(CONFIG_FILE_LOCATION);
			ServiceRegistry serviceRegistry=(ServiceRegistry) new ServiceRegistryBuilder().applySettings(configure.getProperties()).buildServiceRegistry();
            
            return configure.buildSessionFactory();
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }
	public static Session getSession() throws HibernateException {
    	Session session = (Session) threadLocal.get();
    	if(session==null){
    		if(sessionFactory==null){
    			try{
    				cfg.configure(CONFIG_FILE_LOCATION);
    	            ServiceRegistry serviceRegistry= new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
        			sessionFactory=cfg.buildSessionFactory(serviceRegistry);
        			return sessionFactory.openSession();
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			
    		}
    		session=sessionFactory.openSession();
    		threadLocal.set(session);
    	}

        return session;
    }
    public static void closeSession() throws HibernateException 
    {
    	Session session=(Session)threadLocal.get();
    	threadLocal.set(null);
    	if(session!=null){
    		session.close();
    	}
    }
}
