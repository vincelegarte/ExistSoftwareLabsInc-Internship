package com.activity.three;
import java.io.File;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static{
        String filePath = "model/src/main/resources/hibernate.cfg.xml";
        File file = new File(filePath);
        try{
            sessionFactory = new Configuration().configure(file).buildSessionFactory();
        } catch(HibernateException ex){
            System.out.println("failed"+ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
