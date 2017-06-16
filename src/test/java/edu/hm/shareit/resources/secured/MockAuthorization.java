package edu.hm.shareit.resources.secured;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;

/**
 * Created by Sascha on 24.05.17.
 */
public class MockAuthorization extends Authorization {

    public static AuthenticationServiceResult authorize(Token token) {
        if (token.getToken().equals("valid"))
            return AuthenticationServiceResult.AUTHENTICATED;
        else
            return AuthenticationServiceResult.TOKEN_NOT_VALID;
    }
}
