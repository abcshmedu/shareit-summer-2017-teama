package edu.hm.shareit.resources.secured.copy;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.resources.ServiceResult;

import java.util.Collection;

public interface SecuredCopyService {
    /**
     * The result of the request for adding a copy.
     *
     * @param copy Copy to be added.
     * @param isbn Isbn of book copy to be added.
     * @return Status code and message.
     */
    ServiceResult addBookCopy(Copy copy, String isbn, Token token);

    /**
     * Requests a list of book copies.
     *
     * @return Collection of book copies.
     */
    ServiceResult listBookCopies(Token token);

    /**
     * Requests to borrow a book copy.
     *
     * @param isbn Isbn of the book copy requested.
     * @return Status code and message.
     */
    ServiceResult borrowBook(String isbn, Token token);

    /**
     * Requests to return a book.
     *
     * @param isbn Isbn of the book to be returned.
     * @return Status code and message.
     */
    ServiceResult returnBook(String isbn, Token token);

    /**
     * Request to add disc copy.
     *
     * @param copy Copy to be added.
     * @param barcode Barcode of copy to be added.
     * @return Status code and message.
     */
    ServiceResult addDiscCopy(Copy copy, String barcode, Token token);

    /**
     * Request for a list of disc copies.
     * @return All copies of discs in the database.
     */
    ServiceResult listDiscCopies(Token token);

    /**
     * Request for borrowing a disc.
     * @param barcode Barcode of the disc requested.
     * @return Status code and message.
     */
    ServiceResult borrowDisc(String barcode, Token token);

    /**
     * Request to return a copy of a disc.
     * @param barcode The barcode of the copy.
     * @return Status code and message.
     */
    ServiceResult returnDisc(String barcode, Token token);
}
