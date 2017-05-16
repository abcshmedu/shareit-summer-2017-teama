package edu.hm.shareit.api.authentication;

import edu.hm.shareit.models.authentication.User;
import edu.hm.shareit.resources.authentication.AuthenticationService;
import edu.hm.shareit.resources.authentication.AuthenticationServiceImpl;
import edu.hm.shareit.resources.authentication.AuthenticationServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Rest API for users.
 * Receives requests for logging in/giving tokens for users.
 */
@Path("users")
public class AuthenticationRestApi {
    private static AuthenticationService authenticationService = new AuthenticationServiceImpl();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        AuthenticationServiceResult result = authenticationService.login(user);
        return Response.ok(result.getStatus()).status(result.getCode()).build();
    }
}