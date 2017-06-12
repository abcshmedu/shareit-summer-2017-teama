package edu.hm.shareit.resources.secured;

import edu.hm.JettyStarter;
import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.resources.secured.authentication.AuthenticationServiceResult;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class TestAuthorization {

    private Authorization auth = new Authorization();

    private static JettyStarter jettyStarter = new JettyStarter();

    @BeforeClass
    public static void setup() throws Exception {
        try {
            new Socket("localhost", 8082);
        } catch (IOException ex) {
            new Thread(() -> {
                try {
                    jettyStarter.main();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            sleep(4_000);
        }
    }

    @Test
    public void testInvalidAuthorization() throws IOException {
        Token token = new Token("invalid");
        AuthenticationServiceResult sut = Authorization.authorize(token);
        assertEquals(AuthenticationServiceResult.TOKEN_NOT_VALID, sut);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        synchronized (JettyStarter.MONITOR) {
            JettyStarter.MONITOR.notifyAll();
        }
    }
}
