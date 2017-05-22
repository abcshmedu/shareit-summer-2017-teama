package edu.hm.shareit.models.authentication;

import edu.hm.shareit.models.Vars;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestUser extends Vars {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {username, password},
                {otherUsername, otherPassword},
                {nullUsername, nullPassword}
        });
    }

    @Parameter // first data value (0) is default
    public /* NOT private */ String testUsername;

    @Parameter(1)
    public /* NOT private */ String testPassword;

    private User testUser;

    @Before
    public void setup(){
        testUser = new User(testUsername, testPassword);
    }

    @Test
    public void testHashCode(){
        int titleHashCode = 0;
        if(testUsername != null) {
            titleHashCode = testUsername.hashCode();
        }
        int passwordHashcode = 0;
        if(testPassword != null) {
            passwordHashcode = testPassword.hashCode();
        }
        int finalHashCode = 7;
        finalHashCode = 31 * finalHashCode + titleHashCode;
        finalHashCode = 31 * finalHashCode + passwordHashcode;

        int hashCodeResult = testUser.hashCode();

        assertEquals(finalHashCode, hashCodeResult);
    }

    @Test
    public void testToString(){
        String toStringExpected = "User:{username: " + testUsername + ", password: " + testPassword + "}";

        String toStringResult = testUser.toString();

        assertEquals(toStringExpected, toStringResult);
    }

    @Test
    public void testGetUsername(){
        String getTitleResult = testUser.getUsername();

        assertEquals(testUsername, getTitleResult);
    }

    @Test
    public void testGetPassword(){
        String getIsbnResult = testUser.getPassword();

        assertEquals(testPassword, getIsbnResult);
    }


    @Test
    public void testEquals(){
        boolean equalsOtherUser = testUser.equals(notEqualUser);
        boolean equalsSelf = testUser.equals(testUser);

        assertFalse(equalsOtherUser);
        assertTrue(equalsSelf);
    }
}
