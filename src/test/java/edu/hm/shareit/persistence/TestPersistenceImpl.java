package edu.hm.shareit.persistence;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Medium;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Nelson on 17.06.2017.
 */
public class TestPersistenceImpl {
    private Persistence persistence = new PersistenceImpl();
    private SessionFactory sessionFactory;
    private Session session;
    private final Book book1 = new Book("title1", "1234567890121", "author1");
    private final Book book2 = new Book("title2", "1234567890122", "author2");
    private final Book book3 = new Book("title3", "1234567890123", "author3");
    private final Book book4 = new Book("title4", "1234567890124", "author4");
    private final Book book5 = new Book("title5", "1234567890125", "author5");
    private final Book bookUpdate = new Book("update", "1234567890122", "authorUpdate");


    @Before
    public void setup() {
        sessionFactory = HibernateUtils.getSessionFactory();
    }

    @Test
    public void testAddRecord() {
        deleteAllBookEntries();
        persistence.addRecord(book1);

        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Book book = session.find(Book.class, book1.getIsbn());
        transaction.commit();
        assertNotNull(book);
    }

    @Test
    public void testUpdateRecord() {
        deleteAllBookEntries();
        // add Book and retrieve it again for comparison
        persistence.addRecord(book2);
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Book oldBook = session.find(Book.class, book2.getIsbn());
        transaction.commit();

        // update Book and retrieve it again for comparison
        persistence.updateRecord(bookUpdate);
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        Book updatedBook = session.find(Book.class, book2.getIsbn());
        transaction.commit();
        assertEquals(oldBook.getIsbn(), updatedBook.getIsbn());
        assertEquals(updatedBook.getAuthor(), "authorUpdate");
    }


    @Test
    public void testGetTable() {
        persistence.addRecord(book1);
        Collection c = persistence.getTable(Book.class);
        assertEquals("[Book{isbn='1234567890121', author='author1'}]", c.toString());
    }

    @Test
    public void findRecord() {
        persistence.addRecord(book1);
        boolean foundRecord = persistence.findRecord(Book.class, book1.getIsbn());
        assertTrue(foundRecord);
    }

    @Test
    public void getRecord() {
        deleteAllBookEntries();
        Medium medium = persistence.getRecord(Book.class, "123");
        assertNull(medium);

        persistence.addRecord(book1);
        medium = persistence.getRecord(Book.class, book1.getIsbn());
        assertNotNull(medium);
    }

    // helper for clearing entries before every test
    private void deleteAllBookEntries() {
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from Book").executeUpdate();
        transaction.commit();
    }
}
