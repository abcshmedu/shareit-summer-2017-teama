package edu.hm.shareit.api.copy;

import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.resources.copy.CopyService;
import edu.hm.shareit.resources.copy.CopyServiceImpl;
import edu.hm.shareit.resources.copy.CopyServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("books")
public class BookCopyRestApi {

    private CopyService COPY_SERVICE;

    public BookCopyRestApi() {
        setMediaService(new CopyServiceImpl());
    }

    @POST
    @Path("{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBookCopy(Copy copyWithOwner, @PathParam("isbn") String isbn) {
        System.out.println("got isbn: " + isbn + "\n" + "and got owner: " + copyWithOwner.getOwner());
        CopyServiceResult result = COPY_SERVICE.addBookCopy(copyWithOwner, isbn);
        return Response.ok(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listBookCopies(){
        Collection<Copy> result = COPY_SERVICE.listBookCopies();
        return Response.ok(result).build();
    }

    private void setMediaService(CopyServiceImpl copyService) {
        COPY_SERVICE = copyService;
    }
}
