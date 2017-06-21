package edu.hm.shareit.api.secured.media;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.resources.ServiceResult;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;
import edu.hm.shareit.resources.secured.media.SecuredMediaService;
import edu.hm.shareit.resources.unsecured.media.MediaServiceResult;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * REST Resource Class for the Authenticated Book API.
 * <p>
 * GET  /books/{isbn}   ->  getBook
 * GET  /books          ->  getBooks
 * POST /books          ->  postBook
 * PUT  /books/{isbn}   ->  updateBook
 */
@Path("books")
public class AuthenticatedBookRestApi {

    /**
     * Object for authentication
     */
    @Inject
    private SecuredMediaService securedMediaService;

    /**
     * Object for authentication
     */
    @HeaderParam(HttpHeaders.AUTHORIZATION)
    private String tokenStr = null;

    /**
     * GET (getBook) Returns a specific book, provided it exists.
     *
     * @param isbn The ISBN for the book to get
     * @return The Response with the Json format for the book
     */
    @GET
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn) {
        Token token = new Token(tokenStr);
        ServiceResult result = securedMediaService.getBook(isbn, token);
        if (result instanceof AuthenticationServiceResult) {
            return Response.ok(result.getStatus()).status(result.getCode()).build();
        } else {
            MediaServiceResult mediaRes = (MediaServiceResult) result;
            Collection collection = mediaRes.getMedia();
            Book book = null;
            if (collection != null) {
                book = (Book) collection.toArray()[0];
            }
            return Response.ok(book).build();
        }
    }

    /**
     * GET (getBooks) Returns all books.
     *
     * @return The Response with the Json array format for the books
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        Token token = new Token(tokenStr);
        ServiceResult result = securedMediaService.getBooks(token);
        if (result instanceof AuthenticationServiceResult) {
            return Response.ok(result.getStatus()).status(result.getCode()).build();
        } else {
            MediaServiceResult mediaRes = (MediaServiceResult) result;
            Collection collection = mediaRes.getMedia();
            return Response.ok(collection).build();
        }
    }

    /**
     * POST (postBook) Posts a book to the Media_Service.
     *
     * @param book The book to post
     * @return The Response from the SecuredMediaService
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postBook(Book book) {
        Token token = new Token(tokenStr);
        ServiceResult result = securedMediaService.addBook(book, token);
        return Response.ok(result.getStatus()).status(result.getCode()).build();
    }

    /**
     * PUT (updateBook) Updates a book with the given ISBN to the new values.
     *
     * @param book The book with the new values
     * @param isbn The ISBN for the book to replace
     * @return The Response from the SecuredMediaService
     */
    @PUT
    @Path("{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, @PathParam("isbn") String isbn) {
        Token token = new Token(tokenStr);
        ServiceResult result = securedMediaService.updateBook(book, isbn, token);
        return Response.ok(result.getStatus()).status(result.getCode()).build();
    }
}
