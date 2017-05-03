package edu.hm.shareit.api;

import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.resources.copy.CopyService;
import edu.hm.shareit.resources.copy.CopyServiceImpl;
import edu.hm.shareit.resources.copy.CopyServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Nelson on 03.05.2017.
 */
@Path("copies")
public class CopyRestApi  {

    private CopyService COPY_SERVICE;

    public CopyRestApi() {
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

    private void setMediaService(CopyServiceImpl copyService) {
        COPY_SERVICE = copyService;
    }
}
