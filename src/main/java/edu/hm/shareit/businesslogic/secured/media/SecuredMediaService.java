package edu.hm.shareit.businesslogic.secured.media;

import edu.hm.shareit.businesslogic.ServiceResult;
import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;

/**
 * Implements the interface SecuredMediaService and provides functionality and logic for managing the media in the database.
 */
public interface SecuredMediaService {
    /**
     * Request for adding a book to database.
     *
     * @param book The book to be added.
     * @param token The Token to authenticate the request
     * @return Status code and message.
     */
    ServiceResult addBook(Book book, Token token);

    /**
     * Request for adding a disc to database.
     *
     * @param disc the disc to be added.
     * @param token The Token to authenticate the request
     * @return Status code and message.
     */
    ServiceResult addDisc(Disc disc, Token token);

    /**
     * Request for getting all books in the database.
     *
     * @param token The Token to authenticate the request
     * @return Collection of all books.
     */
    ServiceResult getBooks(Token token);

    /**
     * Request for getting all discs in the database.
     *
     * @param token The Token to authenticate the request
     * @return Collection of all discs.
     */
    ServiceResult getDiscs(Token token);

    /**
     * Request for updating an existing book in the database.
     *
     * @param book The book to be updated.
     * @param isbn The isbn of the book to be updated.
     * @param token The Token to authenticate the request
     * @return Status code and message.
     */
    ServiceResult updateBook(Book book, String isbn, Token token);

    /**
     * Request for updating an existing disc in the database.
     *
     * @param disc The disc to be updated.
     * @param isbn The isbn to be updated.
     * @param token The Token to authenticate the request
     * @return Status code and message.
     */
    ServiceResult updateDisc(Disc disc, String isbn, Token token);

    /**
     * Request for getting a specific book from the database.
     *
     * @param isbn The isbn of the book.
     * @param token The Token to authenticate the request
     * @return an instance of the book.
     */
    ServiceResult getBook(String isbn, Token token);

    /**
     * Request for getting a specific disc from the database.
     *
     * @param barcode The barcode of the disc.
     * @param token The Token to authenticate the request
     * @return an instance of the disc.
     */
    ServiceResult getDisc(String barcode, Token token);
}
