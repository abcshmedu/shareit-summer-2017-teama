package edu.hm.shareit.resources.secured;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sascha on 17.06.17.
 */
public class TestAuthorizationImpl {

    private Authorization authorization = new AuthorizationImpl();

    @Test
    public void authorizationTest() {
        AuthenticationServiceResult result = authorization.authorize(new Token("validToken"));
        assertEquals(AuthenticationServiceResult.TOKEN_NOT_VALID, result);
    }
}
