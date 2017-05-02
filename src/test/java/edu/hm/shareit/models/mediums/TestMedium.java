package edu.hm.shareit.models.mediums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestMedium extends Vars{

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {title },
                {otherTitle },
                {nullTitle}
        });
    }

    @Parameter // first data value (0) is default
    public /* NOT private */ String testTitle;

    @Test
    public void test(){
        Medium testMedium = new Medium(testTitle);

        int titleHashCode = 0;
        if(testTitle != null) {
            titleHashCode = testTitle.hashCode();
        }
        String toStringExpected = "Medium{title='" + testTitle + "\'}";

        String getTitleResult = testMedium.getTitle();

        boolean equalsOtherMedium = testMedium.equals(notEqualMedium);
        boolean equalsSelf = testMedium.equals(testMedium);

        int hashCodeResult = testMedium.hashCode();

        String toStringResult = testMedium.toString();

        assertEquals(testTitle, getTitleResult);

        assertFalse(equalsOtherMedium);
        assertTrue(equalsSelf);

        assertEquals(titleHashCode, hashCodeResult);

        assertEquals(toStringExpected, toStringResult);
    }
}
