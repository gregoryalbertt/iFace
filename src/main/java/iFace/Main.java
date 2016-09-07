package iFace;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistryBuilder;

public class Main {
    public static void main(final String[] args) throws Exception {   
    	
    	 //SessionFactory sessionFactory = 
         	//	new Configuration().configure("META-INF/hibernate.cfg.xml").buildSessionFactory();

       //  Session session = sessionFactory.openSession();
       //  session.beginTransaction();
        
        //User user = new User();
        //user.setLogin("nome");
        //user.setId(0);
        //session.save(user);
        //session.getTransaction().commit();
        //session.close();
        
       Process process = new Process();

        while(true){
        	process.MainMenu();
        }
    }
}