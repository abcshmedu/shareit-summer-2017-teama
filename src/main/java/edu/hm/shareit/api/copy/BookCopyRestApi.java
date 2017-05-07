package edu.hm.shareit.api.copy;

import edu.hm.shareit.resources.ServiceGetter;
import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.resources.copy.CopyService;
import edu.hm.shareit.resources.copy.CopyServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
* Rest API for book copies.
* Receives requests for adding/borrowing/returning copies of books to/from the database.
 */
@Path("books")
public class BookCopyRestApi {
    private CopyService copyService = ServiceGetter.getCopyService();

    /**
     * Method called for borrowing books from the database.
     * Checks whether there are any copies left of the book specified in the isbn parameter and responds accordingly.
     * @param isbn The book of which a copy is requested.
     * @return The response containing a status code and message.
     */
    @GET
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrowBook(@PathParam("isbn") String isbn) {
        CopyServiceResult result = copyService.borrowBook(isbn);
        return Response.ok(result).build();
    }

    /**
     * Shows a list of all book copies that exist in the database.
     * @return List of book copies
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listBookCopies() {
        Collection<Copy> result = copyService.listBookCopies();
        return Response.ok(result).build();
    }

    /**
     * Adds a book copy to the database if the provided isbn exists.
     * @param copyWithOwner An instance of Copy which provides the owner's name.
     * @param isbn The isbn number of the book of which a copy is to be added.
     * @return The response containing a status code and message.
     */
    @POST
    @Path("{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBookCopy(Copy copyWithOwner, @PathParam("isbn") String isbn) {
        CopyServiceResult result = copyService.addBookCopy(copyWithOwner, isbn);
        return Response.ok(result).status(result.getCode()).build();
    }

    /**
     * Receives a request for returning a book to the database.
     * @param isbn The isbn number of the book of which a copy is to be returned.
     * @return The response containing a status code and message.
     */
    @PUT
    @Path("{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnBook(@PathParam("isbn") String isbn) {
        CopyServiceResult result = copyService.returnBook(isbn);
        return Response.ok(result).status(result.getCode()).build();
    }
}
