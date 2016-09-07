package iFace;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DataBase {
	
	SessionFactory sessionFactory = 
     		new Configuration().configure("META-INF/hibernate.cfg.xml").buildSessionFactory();
	//Session session = sessionFactory.openSession();
	
	public DataBase() {
		super();
	}

	public HashMap<String, User> GetDataUser(){
		
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<User> usersList = (ArrayList<User>) session.createCriteria(User.class).list();
        session.getTransaction().commit();
        session.close();
        
        HashMap<String, User> usersMap = new HashMap<String, User>();
        for(User user : usersList){
        	usersMap.put(user.getLogin(), user);
        }
        return usersMap;
	}
	
	public HashMap<String, Community> GetDataCommunity(){
		
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Community> communitiesList = (ArrayList<Community>) session.createCriteria(Community.class).list();
        session.getTransaction().commit();
        session.close();
		
        HashMap<String, Community> communitiesMap = new HashMap<String, Community>();
        for(Community community : communitiesList){
        	communitiesMap.put(community.getTitle(), community);
        }
        return communitiesMap;
	}
	
	public void SaveUser(User user){
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
	        session.save(user);
	        session.getTransaction().commit();
		}
		catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			session.close();
		}

	}
	
	public void SaveCommunity(Community community){
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.save(community);
			session.getTransaction().commit();
		} catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
	}

}
