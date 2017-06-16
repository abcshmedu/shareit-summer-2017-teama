package edu.hm.shareit.api.secured.authentication;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.shareit.TestInjectionBindings;
import edu.hm.shareit.models.authentication.User;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class TestAuthenticationRestApi {

    private Injector injector = Guice.createInjector(new TestInjectionBindings());

    private AuthenticationRestApi authenticationRestApi = new AuthenticationRestApi();

    @Before
    public void setup() {
        injector.injectMembers(authenticationRestApi);
    }


    @Test
    public void testValidLogin() {
        Response response = authenticationRestApi.login(new User("admin", "admin"));
        assertEquals(AuthenticationServiceResult.LOGIN_ACCEPTED.getCode(), response.getStatus());
    }

    @Test
    public void testToken() {
        Response response = authenticationRestApi.authenticate();
        assertEquals(AuthenticationServiceResult.TOKEN_NOT_VALID.getCode(), response.getStatus());
    }

}
