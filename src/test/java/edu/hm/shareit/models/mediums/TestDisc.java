package edu.hm.shareit.models.mediums;

import edu.hm.shareit.models.Vars;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestDisc extends Vars {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {title, barcode, director, fsk },
                {otherTitle, otherBarcode, otherDirector, otherFsk },
                {nullTitle, nullBarcode, nullDirector, defaultFsk},
        });
    }

    @Parameter // first data value (0) is default
    public /* NOT private */ String testTitle;

    @Parameter(1)
    public /* NOT private */ String testBarcode;

    @Parameter(2)
    public /* NOT private */ String testDirector;

    @Parameter(3)
    public /* NOT private */ int testFsk;

    @Test
    public void test(){
        Disc testDisc = new Disc(testTitle, testBarcode, testDirector, testFsk);

        int titleHashCode = 0;
        if(testTitle != null) {
            titleHashCode = testTitle.hashCode();
        }
        int barcodeHashCode = 0;
        if(testBarcode != null) {
            barcodeHashCode = testBarcode.hashCode();
        }
        int directorHashCode = 0;
        if(testDirector != null) {
            directorHashCode = testDirector.hashCode();
        }
        int finalHashCode = titleHashCode;
        finalHashCode = 31 * finalHashCode + barcodeHashCode;
        finalHashCode = 31 * finalHashCode + directorHashCode;
        finalHashCode = 31 * finalHashCode + testFsk;

        String toStringExpected = "Disc{barcode='" + testBarcode + "\', director='" + testDirector + "\', fsk=" + testFsk + "}";

        String getTitleResult = testDisc.getTitle();
        String getBarcodeResult = testDisc.getBarcode();
        String getDirectorResult = testDisc.getDirector();
        int getFskResult = testDisc.getFsk();

        boolean equalsOtherMedium = testDisc.equals(notEqualMedium);
        boolean equalsOtherDisc = testDisc.equals(notEqualDisc);
        boolean equalsOtherBook = testDisc.equals(notEqualBook);
        boolean equalsSelf = testDisc.equals(testDisc);

        int hashCodeResult = testDisc.hashCode();

        String toStringResult = testDisc.toString();

        assertEquals(testTitle, getTitleResult);
        assertEquals(testBarcode, getBarcodeResult);
        assertEquals(testDirector, getDirectorResult);
        assertEquals(testFsk, getFskResult);

        assertFalse(equalsOtherMedium);
        assertFalse(equalsOtherDisc);
        assertFalse(equalsOtherBook);
        assertTrue(equalsSelf);

        assertEquals(finalHashCode, hashCodeResult);

        assertEquals(toStringExpected, toStringResult);
    }
}

