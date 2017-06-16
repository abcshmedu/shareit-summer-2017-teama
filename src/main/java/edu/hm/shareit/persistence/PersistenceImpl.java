package edu.hm.shareit.persistence;

import edu.hm.shareit.models.mediums.Medium;
import edu.hm.shareit.resources.unsecured.media.MediaServiceResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public class PersistenceImpl  implements Persistence{

    static final Logger logger = LogManager.getLogger();
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public MediaServiceResult addRecord(Medium medium) {
        MediaServiceResult result = MediaServiceResult.FAILURE;

        Session session = openOrGetCurrentSession();
        if(notNull(session)){
            Transaction transaction = session.beginTransaction();
            session.save(medium);
            System.out.println(medium.toString());
            transaction.commit();
            result = MediaServiceResult.SUCCESS;
        }
        return result;
    }

    @Override
    public MediaServiceResult updateRecord(Medium medium) {
        MediaServiceResult result = MediaServiceResult.FAILURE;
        Session session = openOrGetCurrentSession();
        if(notNull(session)){
            Transaction transaction = session.beginTransaction();
            session.merge(medium);
            transaction.commit();
            result = MediaServiceResult.SUCCESS;
        }
        return result;
    }

    @Override
    public MediaServiceResult getTable(Class clazz) {
        MediaServiceResult result = MediaServiceResult.FAILURE;
        Session session = openOrGetCurrentSession();
        if(notNull(session)){
            Transaction transaction = session.beginTransaction();
            String hql = "from " + clazz.getSimpleName();
            Query query = session.createQuery(hql);
            Collection theMedia = query.getResultList();
            logger.info("Collection is null: " + theMedia == null ? true : false);
            logger.info("Size of Collection: " + theMedia.size());
            result = MediaServiceResult.SUCCESS;
            result.setMedia(theMedia);
            transaction.commit();
        }
        return result;
    }

    @Override
    public MediaServiceResult findRecord(Class clazz, Serializable id) {
        MediaServiceResult result = MediaServiceResult.FAILURE;
        Session session = openOrGetCurrentSession();
        if(notNull(session)){
            Transaction transaction = session.beginTransaction();
            Medium medium = (Medium) session.find(clazz, id);
            transaction.commit();
            if(notNull(medium)){
                result = MediaServiceResult.SUCCESS;
                result.setMedia(Collections.singletonList(medium));
            }
        }
        return result;
    }

    private boolean notNull(Object object){
        return object != null;
    }

    private Session openOrGetCurrentSession(){
        Session session = null;
        if(notNull(sessionFactory)){
            session = sessionFactory.getCurrentSession();
            if(session == null){
                session = sessionFactory.openSession();
            }
        }
        return session;
    }
}
