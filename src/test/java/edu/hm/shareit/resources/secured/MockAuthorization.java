package edu.hm.shareit.resources.secured;


import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;

public class MockAuthorization implements Authorization {
    @Override
    public AuthenticationServiceResult authorize(Token token) {
        if (token.getToken().equals("valid"))
            return AuthenticationServiceResult.AUTHENTICATED;
        else
            return AuthenticationServiceResult.TOKEN_NOT_VALID;
    }
}
