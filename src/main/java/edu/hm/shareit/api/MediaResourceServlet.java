package edu.hm.shareit.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;
import edu.hm.shareit.resources.MediaService;
import edu.hm.shareit.resources.MediaServiceImpl;
import edu.hm.shareit.resources.MediaServiceResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class MediaResourceServlet {
    private static final String BOOKS_ROOT_URI = "/books";
    private static final String DISCS_ROOT_URI = "/discs";
    private static final MediaService MEDIA_SERVICE = new MediaServiceImpl();

    //@Path("/books")
    //@GET
    //@Produces(MediaType.TEXT_PLAIN)
    public Response getBooks(){
        Collection<Book> books = (Collection<Book>) MEDIA_SERVICE.getBooks();
        String responseStr = "";
        for(Book book: books){
            System.out.println(book.getTitle());
            responseStr += book.getTitle() + ";";
        }
        return Response.ok("OK: " + responseStr).build();
    }

    //@Path("/discs")
    //@GET
    //@Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(){
        Collection<Disc> discs = (Collection<Disc>) MEDIA_SERVICE.getDiscs();
        return Response.ok("Get Discs Successful").build();
    }


    //@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonRequest = getBodyContent(request);
        ObjectMapper mapper = new ObjectMapper();
        MediaServiceResult result;
        if (BOOKS_ROOT_URI.equals(request.getPathInfo())) {
            try{
                Book book = mapper.readValue(jsonRequest, Book.class);
                result = MEDIA_SERVICE.addBook(book);
                System.out.println("Book Added");
            } catch (Exception ex){
                result = MediaServiceResult.INVALID_JSON_FORMAT;
            }
        } else if (DISCS_ROOT_URI.equals(request.getPathInfo())) {
            try{
                Disc disc = mapper.readValue(jsonRequest, Disc.class);
                result = MEDIA_SERVICE.addDisc(disc);
            } catch (Exception ex){
                result = MediaServiceResult.INVALID_JSON_FORMAT;
            }
        } else {
            result = MediaServiceResult.INVALID_URL_REQUEST;
        }

        response.setStatus(result.getCode());

        if(result.getCode()/100 != 2){
            String jsonErrorResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            try(PrintWriter out = response.getWriter()){
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
        if(BOOKS_ROOT_URI.equals(request.getPathInfo())) {
            Collection<? extends Medium> books = MEDIA_SERVICE.getBooks();

            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse  = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(books);
            try(PrintWriter out = response.getWriter()){
                out.println(jsonResponse);
            }
        }
    }

    //@Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
