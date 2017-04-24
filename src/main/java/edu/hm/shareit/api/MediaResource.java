package edu.hm.shareit.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;
import edu.hm.shareit.resources.MediaService;
import edu.hm.shareit.resources.MediaServiceImpl;
import edu.hm.shareit.resources.MediaServiceResult;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

@Path("/newPath")
public class MediaResource {
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String BOOKS_ROOT_URI = "/books";
    private static final String DISCS_ROOT_URI = "/discs";
    private static final MediaService MEDIA_SERVICE = new MediaServiceImpl();

    @Path("/books")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() throws JsonProcessingException {
        Collection<? extends Medium> books = MEDIA_SERVICE.getBooks();
        System.out.println("Books size after fetch: " + books.size());
        String responseStr = mapper.writeValueAsString(books);
        return Response.ok(responseStr, MediaType.APPLICATION_JSON).build();
    }

    @Path("/books/{isbn}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn) throws JsonProcessingException {
        Book book = MEDIA_SERVICE.getBook(isbn);
        String result = mapper.writeValueAsString(book);
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("/discs")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs() throws IOException {
        Collection<? extends Medium> discs = MEDIA_SERVICE.getDiscs();
        String responseStr = mapper.writeValueAsString(discs);
        return Response.ok(responseStr, MediaType.APPLICATION_JSON).build();
    }

    @Path("/discs/{barcode}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode) throws JsonProcessingException {
        Book book = MEDIA_SERVICE.getBook(barcode);
        String result = mapper.writeValueAsString(book);
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @Path("/books")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postBook(String jsonBody) throws IOException {
        Book book = mapper.readValue(jsonBody.getBytes(), Book.class);
        MediaServiceResult result = MEDIA_SERVICE.addBook(book);
        String jsonResult = mapper.writeValueAsString(result);
        //System.out.println("New book json:" + jsonBody + " and status code: " + result.getCode());
        return Response.ok(jsonResult, MediaType.APPLICATION_JSON).status(result.getCode()).build();
    }

    @Path("/discs")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postDisc(String jsonBody) throws IOException {
        Disc disc = mapper.readValue(jsonBody.getBytes(), Disc.class);
        MediaServiceResult result = MEDIA_SERVICE.addDisc(disc);
        String jsonResult = mapper.writeValueAsString(result);
        return Response.ok(jsonResult, MediaType.APPLICATION_JSON).status(result.getCode()).build();
    }

    @Path("/books/{isbn}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("isbn") String isbn, String jsonBody) throws IOException {
        Book updateValues = mapper.readValue(jsonBody.getBytes(), Book.class);
        Book fullInstance = new Book(updateValues.getTitle(), isbn, updateValues.getAuthor());
        MediaServiceResult result = MEDIA_SERVICE.updateBook(fullInstance);
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).status(result.getCode()).build();
    }

    @Path("/discs/{barcode}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDisc(@PathParam("barcode") String barcode, String jsonBody) throws IOException {
        Disc updateValues = mapper.readValue(jsonBody.getBytes(), Disc.class);
        Disc fullInstance = new Disc(updateValues.getTitle(), barcode, updateValues.getDirector(), updateValues.getFsk());
        MediaServiceResult result = MEDIA_SERVICE.updateDisc(fullInstance);
        return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).status(result.getCode()).build();
    }

    /*
    Methods changed to Jersey Api, so that no servlet needed.
     */
    /*
    //@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonRequest = getBodyContent(request);
        ObjectMapper mapper = new ObjectMapper();
        MediaServiceResult result;
        if (BOOKS_ROOT_URI.equals(request.getPathInfo())) {
            try {
                Book book = mapper.readValue(jsonRequest, Book.class);
                result = MEDIA_SERVICE.addBook(book);
                System.out.println("Book Added");
            } catch (Exception ex) {
                result = MediaServiceResult.INVALID_JSON_FORMAT;
            }
        } else if (DISCS_ROOT_URI.equals(request.getPathInfo())) {
            try {
                Disc disc = mapper.readValue(jsonRequest, Disc.class);
                result = MEDIA_SERVICE.addDisc(disc);
            } catch (Exception ex) {
                result = MediaServiceResult.INVALID_JSON_FORMAT;
            }
        } else {
            result = MediaServiceResult.INVALID_URL_REQUEST;
        }

        response.setStatus(result.getCode());

        if (result.getCode() / 100 != 2) {
            String jsonErrorResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            try (PrintWriter out = response.getWriter()) {
                out.println(jsonErrorResponse);
            }
        }


    }

    private String getBodyContent(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        String jsonRequest = "";
        for (String line = reader.readLine(); line != null; line = reader.readLine())
            jsonRequest += line;
        return jsonRequest;
    }

    //@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (BOOKS_ROOT_URI.equals(request.getPathInfo())) {
            Collection<? extends Medium> books = MEDIA_SERVICE.getBooks();

            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(books);
            try (PrintWriter out = response.getWriter()) {
                out.println(jsonResponse);
            }
        }
    }

    //@Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }*/
}
