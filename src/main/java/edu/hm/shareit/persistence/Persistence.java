package edu.hm.shareit.persistence;

import edu.hm.shareit.models.mediums.Medium;
import edu.hm.shareit.resources.unsecured.media.MediaServiceResult;

import java.io.Serializable;

/**
 * Created by maxl on 14.06.17.
 */
public interface Persistence {

    MediaServiceResult addRecord(Medium medium);

    MediaServiceResult updateRecord(Medium medium);

    MediaServiceResult getTable(Class clazz);

    MediaServiceResult findRecord(Class clazz, Serializable id);
}
