package edu.hm.shareit.restapi.secured.authentication;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.shareit.DependencyInjectionMockBindings;
import edu.hm.shareit.businesslogic.secured.authentication.AuthenticationServiceResult;
import edu.hm.shareit.models.authentication.User;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class TestAuthenticationRestApi {

    private Injector injector = Guice.createInjector(DependencyInjectionMockBindings.getSingletonInjection());

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
