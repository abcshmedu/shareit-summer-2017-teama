package edu.hm.shareit.api.copy;

import edu.hm.shareit.models.mediums.Copy;
import edu.hm.shareit.resources.copy.CopyService;
import edu.hm.shareit.resources.copy.CopyServiceImpl;
import edu.hm.shareit.resources.copy.CopyServiceResult;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("discs")
public class DiscCopyRestApi {

    private CopyService COPY_SERVICE;

    public DiscCopyRestApi() {
        setMediaService(new CopyServiceImpl());
    }

    @POST
    @Path("{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDiscCopy(Copy copyWithOwner, @PathParam("barcode") String barcode) {
        System.out.println("got isbn: " + barcode + "\n" + "and got owner: " + copyWithOwner.getOwner());
        CopyServiceResult result = COPY_SERVICE.addDiscCopy(copyWithOwner, barcode);
        return Response.ok(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listDiscCopies() {
        Collection<Copy> result = COPY_SERVICE.listDiscCopies();
        return Response.ok(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{barcode}")
    public Response borrowDisc(@PathParam("barcode") String isbn) {
        CopyServiceResult result = COPY_SERVICE.borrowDisc(isbn);
        return Response.ok(result).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{barcode}")
    public Response returnBook(@PathParam("barcode") String barcode) {
        CopyServiceResult result = COPY_SERVICE.returnDisc(barcode);
        return Response.ok(result).build();
    }

    private void setMediaService(CopyServiceImpl copyService) {
        COPY_SERVICE = copyService;
    }
}
