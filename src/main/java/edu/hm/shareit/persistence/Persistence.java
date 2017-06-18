package edu.hm.shareit.persistence;

import edu.hm.shareit.models.mediums.Medium;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by maxl on 14.06.17.
 */
public interface Persistence {
    /**
     * Adds a record to the database.
     * @param medium the medium to be added.
     * @return boolean value
     */
    boolean addRecord(Medium medium);
    /**
     * Updates a record in the database.
     * @param medium the medium to be updated.
     * @return boolean value
     */
    boolean updateRecord(Medium medium);
    /**
     * Returns the table for a certain class.
     * @param clazz the class that is looked for.
     * @return Collection with entries
     */
    Collection getTable(Class clazz);
    /**
     * Finds a record in the database.
     * @param clazz class of entry that is searched.
     * @param id isbn or barcode.
     * @return boolean value
     */
    boolean findRecord(Class clazz, Serializable id);

    /**
     * gets a specific medium record from the database.
     * @param clazz class of entry that is searched.
     * @param id isbn or barcode.
     * @return boolean value
     */
    Medium getRecord(Class clazz, Serializable id);
}
