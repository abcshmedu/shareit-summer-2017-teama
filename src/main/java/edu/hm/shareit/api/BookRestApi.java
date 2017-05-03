package edu.hm.shareit.api;

import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Medium;
import edu.hm.shareit.resources.MediaService;
import edu.hm.shareit.resources.MediaServiceImpl;
import edu.hm.shareit.resources.MediaServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/books")
public class BookRestApi {
    private final MediaService MEDIA_SERVICE = new MediaServiceImpl();

    @GET
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn) {
        Book book = MEDIA_SERVICE.getBook(isbn);
        return Response.ok(book).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        Collection<? extends Medium> books = MEDIA_SERVICE.getBooks();
        return Response.ok(books).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postBook(Book book) {
        MediaServiceResult result = MEDIA_SERVICE.addBook(book);
        return Response.ok(result).status(result.getCode()).build();
    }

    @PUT
    @Path("{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, @PathParam("isbn") String isbn) {
        MediaServiceResult result = MEDIA_SERVICE.updateBook(book, isbn);
        return Response.ok(result).status(result.getCode()).build();
    }
}
