package edu.hm.shareit.persistence;

import edu.hm.shareit.models.mediums.Medium;

import java.io.Serializable;
import java.util.Collection;

public class PersistenceMock implements Persistence {
    @Override
    public boolean addRecord(Medium medium) {
        if (medium.getTitle().equals("valid"))
            return true;
        else
            return false;
    }

    @Override
    public boolean updateRecord(Medium medium) {
        if (medium.getTitle().equals("valid"))
            return true;
        else
            return false;
    }

    @Override
    public Collection getTable(Class clazz) {
        return null;
    }

    @Override
    public boolean findRecord(Class clazz, Serializable id) {
        return false;
    }

    @Override
    public Medium getRecord(Class clazz, Serializable id) {
        return null;
    }
}
