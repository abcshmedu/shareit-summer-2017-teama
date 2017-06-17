package edu.hm.toDelete.media.resources;

import edu.hm.shareit.resources.ServiceGetter;
import edu.hm.shareit.resources.secured.authentication.AuthenticationService;
import edu.hm.shareit.resources.secured.media.SecuredMediaService;
import edu.hm.shareit.resources.unsecured.media.MediaService;
import org.junit.Test;

import static org.junit.Assert.assertFalse;


public class TestServiceGetter {

    @Test
    public void gettersDoNotThrowAnyExceptionAndIsNotNull() {
        boolean gotError = false;

        try {
            MediaService sut1 = ServiceGetter.getMediaService();
            SecuredMediaService sut2 = ServiceGetter.getSecuredMediaService();
            AuthenticationService sut5 = ServiceGetter.getAuthenticationService();
            ServiceGetter.setAuthenticationService(null);
            ServiceGetter.setMediaService(null);
            ServiceGetter.setSecuredMediaService(null);


        } catch (Exception ex) {
            gotError = true;
        }
        assertFalse(gotError);
    }

}
