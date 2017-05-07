package edu.hm.shareit.resources.copy;

import edu.hm.shareit.models.mediums.Copy;

import java.util.Collection;

/**
 * Created by Nelson on 03.05.2017.
 */
public interface CopyService {
    /**
     * The result of the request for adding a copy.
     *
     * @param copy Copy to be added.
     * @param isbn Isbn of book copy to be added.
     * @return Status code and message.
     */
    CopyServiceResult addBookCopy(Copy copy, String isbn);

    /**
     * Requests a list of book copies.
     *
     * @return Collection of book copies.
     */
    Collection<Copy> listBookCopies();

    /**
     * Requests to borrow a book copy.
     *
     * @param isbn Isbn of the book copy requested.
     * @return Status code and message.
     */
    CopyServiceResult borrowBook(String isbn);

    /**
     * Requests to return a book.
     *
     * @param isbn Isbn of the book to be returned.
     * @return Status code and message.
     */
    CopyServiceResult returnBook(String isbn);

    /**
     * Request to add disc copy.
     *
     * @param copy Copy to be added.
     * @param barcode Barcode of copy to be added.
     * @return Status code and message.
     */
    CopyServiceResult addDiscCopy(Copy copy, String barcode);

    /**
     * Request for a list of disc copies.
     * @return All copies of discs in the database.
     */
    Collection<Copy> listDiscCopies();

    /**
     * Request for borrowing a disc.
     * @param barcode Barcode of the disc requested.
     * @return Status code and message.
     */
    CopyServiceResult borrowDisc(String barcode);

    /**
     * Request to return a copy of a disc.
     * @param barcode The barcode of the copy.
     * @return Status code and message.
     */
    CopyServiceResult returnDisc(String barcode);
}
