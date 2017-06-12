package edu.hm.shareit.resources.hibernate.media;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.persistence.HibernateUtils;
import org.apache.commons.validator.routines.ISBNValidator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;


/**
 * Implements the interface SecuredMediaService and provides functionality and logic for managing the media in the database.
 */
public class HibernateMediaServiceImpl implements HibernateMediaService, Serializable {
    private final int isbnBarcodeLength = 13;
    private final int isbnBarcodeValidStart = 48;
    private final int isbnBarcodeValidEnd = 57;
    private final ISBNValidator validator = new ISBNValidator();

    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private Session session;
    private Transaction transaction;

    @Override
    public HibernateMediaServiceResult addBook(Book book) {
        if (book == null || book.getIsbn() == null || book.getTitle() == null || book.getAuthor() == null) {
            return HibernateMediaServiceResult.PARAMETER_MISSING;
        }

        if (book.getAuthor().isEmpty()
                || book.getIsbn().isEmpty() || book.getTitle().isEmpty()) {
            return HibernateMediaServiceResult.PARAMETER_MISSING;
        }

        if (!validator.isValidISBN13(book.getIsbn())) {
            return HibernateMediaServiceResult.INVALID_ISBN;
        }

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        if (ifExists(Book.class, book.getIsbn())) {
            return HibernateMediaServiceResult.DUPLICATE_ISBN;
        }

        session.save(book);
        transaction.commit();
        session.close();

        return HibernateMediaServiceResult.ACCEPTED;
    }


    @Override
    public HibernateMediaServiceResult addDisc(Disc disc) {
        if (disc == null || disc.getBarcode() == null || disc.getDirector() == null || disc.getTitle() == null) {
            return HibernateMediaServiceResult.PARAMETER_MISSING;
        }

        if (disc.getBarcode().isEmpty()
                | disc.getDirector().isEmpty()
                | disc.getTitle().isEmpty()) {
            return HibernateMediaServiceResult.PARAMETER_MISSING;
        }

        if (!isValidBarcode(disc.getBarcode())) {
            return HibernateMediaServiceResult.INVALID_BARCODE;
        }

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        if (ifExists(Disc.class, disc.getBarcode())) {
            return HibernateMediaServiceResult.DUPLICATE_DISC;
        }

        session.save(disc);
        transaction.commit();
        session.close();

        return HibernateMediaServiceResult.ACCEPTED;
    }

    /**
     * Helper method to check for existance of Id
     *
     * @param clazz the class of the Object to check
     * @param object the id to check
     */
    private boolean ifExists(Class clazz, Serializable object){
        System.out.println("Class: " + clazz);
        System.out.println("Id: " + object);
        Book book = (Book) session.get(clazz, object);
        System.out.println("Get: " + book);
        System.out.println("Find: " + session.find(clazz, object));
        return session.get(clazz, object) != null;
    }

    /**
     * Helper method to check for validity of isbn.
     * @param isbn the isbn to be checked.
     * @return Isbn valid or not.
     */
    private boolean isValidISBN(String isbn) {
        if (isbn.length() != isbnBarcodeLength) {
            return false;
        }

        final char[] isbnChars = isbn.toCharArray();
        for (char i : isbnChars) {
            if (i < isbnBarcodeValidStart || i > isbnBarcodeValidEnd) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method to check for validity of barcode.
     * @param barcode The barcode to be checked.
     * @return Barcode is valid or not.
     */
    private boolean isValidBarcode(String barcode) {
        if (barcode.length() != isbnBarcodeLength) {
            return false;
        }
        final char[] barcodeChars = barcode.toCharArray();
        for (char i : barcodeChars) {
            if (i < isbnBarcodeValidStart || i > isbnBarcodeValidEnd) {
                return false;
            }
        }
        return true;
    }

    @Override
    public HibernateMediaServiceResult getBooks() {
        HibernateMediaServiceResult res = HibernateMediaServiceResult.ACCEPTED;
        String hql = "from " + Book.class.getSimpleName();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        Collection theBooks = query.getResultList();
        res.setMedia(theBooks);
        session.close();
        return res;
    }

    @Override
    public HibernateMediaServiceResult getDiscs() {
        HibernateMediaServiceResult res = HibernateMediaServiceResult.ACCEPTED;
        String hql = "from " + Disc.class.getSimpleName();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        Collection theDiscs = query.getResultList();
        res.setMedia(theDiscs);
        return res;
    }

    @Override
    public HibernateMediaServiceResult updateBook(Book book, String isbn) {
        if (book.getIsbn() != null && !book.getIsbn().equals(isbn)) {
            return HibernateMediaServiceResult.ISBN_DOES_NOT_MATCH;
        }

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Book oldBook = session.get(Book.class, isbn);

        if (oldBook == null) {
            return HibernateMediaServiceResult.ISBN_NOT_FOUND;
        }

        String bookAuthor = book.getAuthor();
        String bookTitle = book.getTitle();

        if (bookAuthor != null) {
            oldBook.setAuthor(bookAuthor);
        }

        if (bookTitle != null) {
            oldBook.setTitle(bookTitle);
        }

        session.merge(oldBook);
        transaction.commit();
        session.close();

        return HibernateMediaServiceResult.ACCEPTED;
    }

    @Override
    public HibernateMediaServiceResult updateDisc(Disc disc, String barcode) {

        if (disc.getBarcode() != null && !disc.getBarcode().equals(barcode)) {
            return HibernateMediaServiceResult.DISC_DOES_NOT_MATCH;
        }

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Disc oldDisc = session.get(Disc.class, barcode);

        if (oldDisc == null) {
            return HibernateMediaServiceResult.DISC_NOT_FOUND;
        }

        String discDirector = disc.getDirector();
        String discTitle = disc.getTitle();
        int discFsk = disc.getFsk();

        if (discFsk < 0) {
            return HibernateMediaServiceResult.INVALID_DISC;
        } else {
            oldDisc.setFsk(discFsk);
        }

        if (discDirector != null) {
            oldDisc.setDirector(discDirector);
        }

        if (discTitle != null) {
            oldDisc.setTitle(discTitle);
        }

        session.merge(oldDisc);
        transaction.commit();
        session.close();

        return HibernateMediaServiceResult.ACCEPTED;
    }

    @Override
    public HibernateMediaServiceResult getBook(String isbn) {
        HibernateMediaServiceResult res = HibernateMediaServiceResult.ACCEPTED;
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, isbn);
        res.setMedia(Collections.singletonList(book));
        return res;
    }

    @Override
    public HibernateMediaServiceResult getDisc(String barcode) {
        HibernateMediaServiceResult res = HibernateMediaServiceResult.ACCEPTED;
        Session session = sessionFactory.getCurrentSession();
        Disc disc = session.get(Disc.class, barcode);
        res.setMedia(Collections.singletonList(disc));
        return res;
    }
}
