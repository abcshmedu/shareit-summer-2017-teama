package edu.hm.shareit.persistence;

import edu.hm.shareit.models.mediums.Book;
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
        if (medium.getTitle().equals("title"))
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
        if (id.equals("9783940006127"))
            return false;
        return true;
    }

    @Override
    public Medium getRecord(Class clazz, Serializable id) {
        if (id.equals("9783127323207"))
            return new Book("title", "9783127323207", "author");
        else
            return null;
    }
}
