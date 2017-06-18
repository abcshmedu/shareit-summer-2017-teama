package edu.hm.shareit.persistence;

import edu.hm.shareit.models.mediums.Medium;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.Collection;

/**
 * Implementation of persistence functionality.
 */
public class PersistenceImpl implements Persistence {

    static final Logger LOGGER = LogManager.getLogger();
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public boolean addRecord(Medium medium) {
        boolean result = false;

        Session session = openOrGetCurrentSession();
        if (notNull(session)) {
            Transaction transaction = session.beginTransaction();
            session.save(medium);
            transaction.commit();
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateRecord(Medium medium) {
        boolean result = false;
        Session session = openOrGetCurrentSession();
        if (notNull(session)) {
            Transaction transaction = session.beginTransaction();
            session.merge(medium);
            transaction.commit();
            result = true;
        }
        return result;
    }

    @Override
    public Collection getTable(Class clazz) {
        Collection result = null;
        Session session = openOrGetCurrentSession();
        if (notNull(session)) {
            Transaction transaction = session.beginTransaction();
            String hql = "from " + clazz.getSimpleName();
            Query query = session.createQuery(hql);
            result = query.getResultList();
            transaction.commit();
        }
        return result;
    }

    @Override
    public boolean findRecord(Class clazz, Serializable id) {
        Session session = openOrGetCurrentSession();
        if (notNull(session)) {
            Transaction transaction = session.beginTransaction();
            Medium medium = (Medium) session.find(clazz, id);
            transaction.commit();
            if (notNull(medium)) {
                return true;
            }
        }
        return false;
    }
@Override
    public Medium getRecord(Class clazz, Serializable id) {
        Medium result = null;
        Session session = openOrGetCurrentSession();
        if (notNull(session)) {
            Transaction transaction = session.beginTransaction();
            result = (Medium) session.find(clazz, id);
            transaction.commit();
        }
        return result;
    }

    /**
     * checks if object is null or not.
     * @param object the object to be checked.
     * @return true or false, depending on check.
     */
    private boolean notNull(Object object) {
        return object != null;
    }

    /**
     * Retrieves the session
     * @return the current session.
     */
    private Session openOrGetCurrentSession() {
        Session session = null;
        if (notNull(sessionFactory)) {
            session = sessionFactory.getCurrentSession();
            if (session == null) {
                session = sessionFactory.openSession();
            }
        }
        return session;
    }
}
