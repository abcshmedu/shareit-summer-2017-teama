package edu.hm.shareit.api.secured.authentication;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.authentication.User;
import edu.hm.shareit.resources.ServiceGetter;
import edu.hm.shareit.resources.secured.authentication.AuthenticationService;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Rest API for users.
 * Receives requests for logging in/giving tokens for users.
 */
@Path("users")
public class AuthenticationRestApi {
    private AuthenticationService authenticationService = ServiceGetter.getAuthenticationService();

    @HeaderParam(HttpHeaders.AUTHORIZATION)
    private String tokenStr;

    /**
     * GET (authenticate) Returns AUTHENTICATED Code if the token is valid
     *
     * @return The Response saying whether or not the Token was authenticated
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(){
        Token token = new Token(tokenStr);
        AuthenticationServiceResult result = authenticationService.authenticate(token);
        return Response.ok(result).status(result.getCode()).build();
    }

    /**
     * POST (login) Posts a User to be authenticated for login
     * @param user The User to login
     * @return The Response, containing a Token upon success
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        AuthenticationServiceResult result = authenticationService.login(user);
        return Response.ok(result).status(result.getCode()).build();
    }
}