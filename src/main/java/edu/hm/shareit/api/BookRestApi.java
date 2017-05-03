package edu.hm.shareit.api;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Medium;
import edu.hm.shareit.resources.media.MediaService;
import edu.hm.shareit.resources.media.MediaServiceImpl;
import edu.hm.shareit.resources.media.MediaServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * REST Resource Class for the Book API
 *
 * GET  /books/{isbn}   ->  getBook
 * GET  /books          ->  getBooks
 * POST /books          ->  postBook
 * PUT  /books/{isbn}   ->  updateBook
 */
@Path("books")
public class BookRestApi {
    // Field for the backend service
    private static MediaService MEDIA_SERVICE = new MediaServiceImpl();

    /**
     * Helper Method, used for setting MediaService (to allow for testing)
     * @param mediaService The MediaService to use
     */
    protected void setMediaService(MediaService mediaService){
        MEDIA_SERVICE = mediaService;
    }

    /**
     * GET (getBook) Returns a specific book, provided it exists
     * @param isbn The ISBN for the book to get
     * @return The Response with the Json format for the book
     */
    @GET
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn) {
        Book book = MEDIA_SERVICE.getBook(isbn);
        return Response.ok(book).build();
    }

    /**
     * GET (getBooks) Returns all books
     * @return The Response with the Json array format for the books
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        Collection<? extends Medium> books = MEDIA_SERVICE.getBooks();
        return Response.ok(books).build();
    }

    /**
     * POST (postBook) Posts a book to the Media_Service
     * @param book The book to post
     * @return The Response from the MediaService
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postBook(Book book) {
        MediaServiceResult result = MEDIA_SERVICE.addBook(book);
        return Response.ok(result).build();
    }

    /**
     * PUT (updateBook) Updates a book with the given ISBN to the new values
     * @param book The book with the new values
     * @param isbn The ISBN for the book to replace
     * @return The Response from the MediaService
     */
    @PUT
    @Path("{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, @PathParam("isbn") String isbn) {
        MediaServiceResult result = MEDIA_SERVICE.updateBook(book, isbn);
        return Response.ok(result).build();
    }
}
