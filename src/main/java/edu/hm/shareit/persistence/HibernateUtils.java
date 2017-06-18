package edu.hm.shareit.persistence;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtils class.
 */
public final class HibernateUtils {
    /**
     * private custom constructor.
     */
    private HibernateUtils() {
    }

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            sessionFactory = null;
        }
    }

    /**
     * Getter for session factory.
     * @return the session factory.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * shutdown method.
     */
    public static void shutdown() {
        getSessionFactory().close();
    }
}