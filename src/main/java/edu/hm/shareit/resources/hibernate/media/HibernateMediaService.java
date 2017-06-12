package edu.hm.shareit.resources.hibernate.media;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;

/**
 * Implements the interface SecuredMediaService and provides functionality and logic for managing the media in the database.
 */
public interface HibernateMediaService {
    /**
     * Request for adding a book to database.
     *
     * @param book The book to be added.
     * @return Status code and message.
     */
    HibernateMediaServiceResult addBook(Book book);

    /**
     * Request for adding a disc to database.
     *
     * @param disc the disc to be added.
     * @return Status code and message.
     */
    HibernateMediaServiceResult addDisc(Disc disc);

    /**
     * Request for getting all books in the database.
     *
     * @return Collection of all books.
     */
    HibernateMediaServiceResult getBooks();

    /**
     * Request for getting all discs in the database.
     *
     * @return Collection of all discs.
     */
    HibernateMediaServiceResult getDiscs();

    /**
     * Request for updating an existing book in the database.
     *
     * @param book The book to be updated.
     * @param isbn The isbn of the book to be updated.
     * @return Status code and message.
     */
    HibernateMediaServiceResult updateBook(Book book, String isbn);

    /**
     * Request for updating an existing disc in the database.
     *
     * @param disc The disc to be updated.
     * @param barcode The isbn to be updated.
     * @return Status code and message.
     */
    HibernateMediaServiceResult updateDisc(Disc disc, String barcode);

    /**
     * Request for getting a specific book from the database.
     *
     * @param isbn The isbn of the book.
     * @return an instance of the book.
     */
    HibernateMediaServiceResult getBook(String isbn);

    /**
     * Request for getting a specific disc from the database.
     *
     * @param barcode The barcode of the disc.
     * @return an instance of the disc.
     */
    HibernateMediaServiceResult getDisc(String barcode);
}
