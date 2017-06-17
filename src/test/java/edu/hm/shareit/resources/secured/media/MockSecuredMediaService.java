package edu.hm.shareit.resources.secured.media;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.resources.ServiceResult;

/**
 * Created by Sascha on 17.06.17.
 */
public class MockSecuredMediaService implements SecuredMediaService {
    @Override
    public ServiceResult addBook(Book book, Token token) {
        return null;
    }

    @Override
    public ServiceResult addDisc(Disc disc, Token token) {
        return null;
    }

    @Override
    public ServiceResult getBooks(Token token) {
        return null;
    }

    @Override
    public ServiceResult getDiscs(Token token) {
        return null;
    }

    @Override
    public ServiceResult updateBook(Book book, String isbn, Token token) {
        return null;
    }

    @Override
    public ServiceResult updateDisc(Disc disc, String isbn, Token token) {
        return null;
    }

    @Override
    public ServiceResult getBook(String isbn, Token token) {
        return null;
    }

    @Override
    public ServiceResult getDisc(String barcode, Token token) {
        return null;
    }
}
