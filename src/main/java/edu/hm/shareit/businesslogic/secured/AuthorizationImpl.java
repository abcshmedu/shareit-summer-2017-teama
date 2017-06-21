package edu.hm.shareit.resources.secured;

import edu.hm.JettyStarter;
import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Performs the call to the AuthorizationService.
 */
public class AuthorizationImpl implements Authorization {
    //Constants
    private static final String HOST = "localhost";
    private static final int PORT = JettyStarter.PORT;
    private static final String HTTP_SCHEME = "http";
    private static final String PATH = JettyStarter.APP_URL + "/shareit/authentication/users";

    /**
     * Calls the Authentication MicroService to authenticate users.
     *
     * @param token The token to authorize
     * @return code and status of the result
     */
    public AuthenticationServiceResult authorize(Token token) {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            URI uri = new URIBuilder()
                    .setScheme(HTTP_SCHEME)
                    .setHost(HOST)
                    .setPort(PORT)
                    .setPath(PATH)
                    .build();

            HttpGet httpGet = new HttpGet(uri);
            httpGet.addHeader(HttpHeaders.AUTHORIZATION, token.getToken());

            HttpResponse response = httpClient.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == AuthenticationServiceResult.AUTHENTICATED.getCode()) {
                return AuthenticationServiceResult.AUTHENTICATED;
            }

            httpClient.close();
        } catch (URISyntaxException | IOException e) {
            System.err.println("Failed to successfully complete authentication");
            e.printStackTrace();
        }
        return AuthenticationServiceResult.TOKEN_NOT_VALID;
    }
}
