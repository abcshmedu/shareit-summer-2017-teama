package edu.hm.shareit.businesslogic.secured;


import edu.hm.shareit.businesslogic.secured.authentication.AuthenticationServiceResult;
import edu.hm.shareit.models.authentication.Token;

public class MockAuthorization implements Authorization {
    @Override
    public AuthenticationServiceResult authorize(Token token) {
        if (token.getToken().equals("valid"))
            return AuthenticationServiceResult.AUTHENTICATED;
        else
            return AuthenticationServiceResult.TOKEN_NOT_VALID;
    }
}
