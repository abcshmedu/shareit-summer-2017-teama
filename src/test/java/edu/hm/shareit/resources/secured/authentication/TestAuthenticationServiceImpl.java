package edu.hm.shareit.resources.secured.authentication;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.authentication.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAuthenticationServiceImpl {
    private AuthenticationServiceImpl authenticationService = new AuthenticationServiceImpl();

    @Test
    public void loginAsValidUser(){
        User validUser = new User("admin", "admin");
        AuthenticationServiceResult result = authenticationService.login(validUser);
        assertEquals(AuthenticationServiceResult.LOGIN_ACCEPTED, result);
    }

    @Test
    public void loginAsInvalidUser(){
        User invalidUser = new User("hans", "dampf");
        AuthenticationServiceResult result = authenticationService.login(invalidUser);
        assertEquals(AuthenticationServiceResult.LOGIN_REJECTED, result);
    }

    @Test
    public void loginAsUserWithNullValue(){
        User userWithNullReference = null;
        AuthenticationServiceResult result = authenticationService.login(userWithNullReference);
        assertEquals(AuthenticationServiceResult.LOGIN_REJECTED, result);
    }

    @Test
    public void authenticateValidUser(){
        User validUser = new User("admin", "admin");
        Token validToken =authenticationService.login(validUser).getToken();
        AuthenticationServiceResult result = authenticationService.authenticate(validToken);
        assertEquals(AuthenticationServiceResult.AUTHENTICATED, result);
    }

    @Test
    public void authenticateInvalidUser(){
        User validUser = new User("hans", "dampf");
        Token validToken =authenticationService.login(validUser).getToken();
        AuthenticationServiceResult result = authenticationService.authenticate(validToken);
        assertEquals(AuthenticationServiceResult.TOKEN_NOT_VALID, result);
    }
}
