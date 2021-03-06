package edu.hm.shareit.restapi.unsecured.media;

import edu.hm.shareit.businesslogic.unsecured.media.MediaService;
import edu.hm.shareit.businesslogic.unsecured.media.MediaServiceResult;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Medium;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * REST Resource Class for the Book API.
 * <p>
 * GET  /books/{isbn}   ->  getBook
 * GET  /books          ->  getBooks
 * POST /books          ->  postBook
 * PUT  /books/{isbn}   ->  updateBook
 */
@Path("books")
public class BookRestApi {

    @Inject
    private MediaService mediaService;

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
        Collection collection = mediaService.getBook(isbn).getMedia();
        Book book = null;
        if (collection != null) {
            book = (Book) collection.toArray()[0];
        }
        return Response.ok(book).build();
    }

    /**
     * GET (getBooks) Returns all books.
     *
     * @return The Response with the Json array format for the books
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        Collection< ? extends Medium> books = mediaService.getBooks().getMedia();
        return Response.ok(books).build();
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
        MediaServiceResult result = mediaService.addBook(book);
        return Response.ok(result).status(result.getCode()).build();
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
        MediaServiceResult result = mediaService.updateBook(book, isbn);
        return Response.ok(result).status(result.getCode()).build();
    }
}
