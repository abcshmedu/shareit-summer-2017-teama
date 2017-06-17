package edu.hm.shareit.persistence;

import edu.hm.shareit.models.mediums.Medium;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by maxl on 14.06.17.
 */
public interface Persistence {

    boolean addRecord(Medium medium);

    boolean updateRecord(Medium medium);

    Collection getTable(Class clazz);

    boolean findRecord(Class clazz, Serializable id);

    Medium getRecord(Class clazz, Serializable id);
}
