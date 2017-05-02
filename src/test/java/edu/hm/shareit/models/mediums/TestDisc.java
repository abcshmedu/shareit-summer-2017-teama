package edu.hm.shareit.models.mediums;

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
public class TestDisc extends Vars{

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {ONE_ARG_CONSTRUCTOR, title, nullBarcode, nullDirector, defaultFsk },
                {ONE_ARG_CONSTRUCTOR, otherTitle, nullBarcode, nullDirector, defaultFsk },
                {ONE_ARG_CONSTRUCTOR, nullTitle, nullBarcode, nullDirector, defaultFsk },
                {MULTI_ARG_CONSTRUCTOR, title, barcode, director, fsk },
                {MULTI_ARG_CONSTRUCTOR, otherTitle, otherBarcode, otherDirector, otherFsk },
                {MULTI_ARG_CONSTRUCTOR, nullTitle, nullBarcode, nullDirector, defaultFsk},
        });
    }

    @Parameter // first data value (0) is default
    public /* NOT private */ int constructorToUse;

    @Parameter(1)
    public /* NOT private */ String testTitle;

    @Parameter(2)
    public /* NOT private */ String testBarcode;

    @Parameter(3)
    public /* NOT private */ String testDirector;

    @Parameter(4)
    public /* NOT private */ int testFsk;

    @Test
    public void test(){
        Disc testDisc;
        if(constructorToUse == ONE_ARG_CONSTRUCTOR){
            testDisc = new Disc(testTitle);
        }else{
            testDisc = new Disc(testTitle, testBarcode, testDirector, testFsk);
        }

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
        boolean equalsOtherDiscOne = testDisc.equals(notEqualDiscOne);
        boolean equalsOtherDiscTwo = testDisc.equals(notEqualDiscTwo);
        boolean equalsOtherBookOne = testDisc.equals(notEqualBookOne);
        boolean equalsOtherBookTwo = testDisc.equals(notEqualBookTwo);
        boolean equalsSelf = testDisc.equals(testDisc);

        int hashCodeResult = testDisc.hashCode();

        String toStringResult = testDisc.toString();

        assertEquals(testTitle, getTitleResult);
        assertEquals(testBarcode, getBarcodeResult);
        assertEquals(testDirector, getDirectorResult);
        assertEquals(testFsk, getFskResult);

        assertFalse(equalsOtherMedium);
        assertFalse(equalsOtherDiscOne);
        assertFalse(equalsOtherDiscTwo);
        assertFalse(equalsOtherBookOne);
        assertFalse(equalsOtherBookTwo);
        assertTrue(equalsSelf);

        assertEquals(finalHashCode, hashCodeResult);

        assertEquals(toStringExpected, toStringResult);
    }
}

