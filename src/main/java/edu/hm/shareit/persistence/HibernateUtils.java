package edu.hm.shareit.persistence;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            System.out.println("SUCCESS: " + sessionFactory);
        }catch (Exception e){
            System.out.println("Failed to create sessionFactory: " + e.getMessage());
            sessionFactory = null;
        }
    }

    public static SessionFactory getSessionFactory() {
        System.out.println("getSessionFactory: " + sessionFactory);
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}