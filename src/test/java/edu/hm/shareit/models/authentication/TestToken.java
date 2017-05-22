package edu.hm.shareit.models.authentication;

import edu.hm.shareit.models.Vars;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestToken extends Vars {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {tokenStr},
                {otherTokenStr},
                {nullTokenStr}
        });
    }

    @Parameter // first data value (0) is default
    public /* NOT private */ String testTokenStr;


    private Token testToken;

    @Before
    public void setup(){
        testToken = new Token(testTokenStr);
    }

    @Test
    public void testHashCode(){
        int titleHashCode = 0;
        if(testTokenStr != null) {
            titleHashCode = testTokenStr.hashCode();
        }
        int finalHashCode = 7;
        finalHashCode = 31 * finalHashCode + titleHashCode;

        int hashCodeResult = testToken.hashCode();

        assertEquals(finalHashCode, hashCodeResult);
    }

    @Test
    public void testToString(){
        String toStringExpected = testTokenStr;

        String toStringResult = testToken.toString();

        assertEquals(toStringExpected, toStringResult);
    }

    @Test
    public void testGetToken(){
        String getTokenResult = testToken.getToken();

        assertEquals(testTokenStr, getTokenResult);
    }


    @Test
    public void testEquals(){
        boolean equalsOtherToken = testToken.equals(notEqualToken);
        boolean equalsSelf = testToken.equals(testToken);

        assertFalse(equalsOtherToken);
        assertTrue(equalsSelf);
    }
}
