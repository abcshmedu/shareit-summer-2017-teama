package edu.hm.shareit.api;

import edu.hm.shareit.models.mediums.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;

/**
 * Created by maxl on 24.04.17.
 */

@Path("/shareit/media")
public class BookRestApi {

    @GET
    @Path("/books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(){
        //TODO Finish method
        System.out.println("GetBooks called");
        return null;
    }

    @POST
    @Path("/books")
    @Consumes(MediaType.APPLICATION_JSON)
    public void postBook(){
        Book book = new Book("Title", "102012", "Author");

    }
}
