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
public class TestBook extends Vars{

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {title, isbn, author},
                {otherTitle, otherIsbn, otherAuthor},
                {nullTitle, nullIsbn, nullAuthor},
        });
    }

    @Parameter // first data value (0) is default
    public /* NOT private */ String testTitle;

    @Parameter(1)
    public /* NOT private */ String testIsbn;

    @Parameter(2)
    public /* NOT private */ String testAuthor;

    @Test
    public void test(){
        Book testBook = new Book(testTitle, testIsbn, testAuthor);

        int titleHashCode = 0;
        if(testTitle != null) {
            titleHashCode = testTitle.hashCode();
        }
        int isbnHashCode = 0;
        if(testIsbn != null) {
            isbnHashCode = testIsbn.hashCode();
        }
        int authorHashCode = 0;
        if(testAuthor != null) {
            authorHashCode = testAuthor.hashCode();
        }
        int finalHashCode = titleHashCode;
        finalHashCode = 31 * finalHashCode + isbnHashCode;
        finalHashCode = 31 * finalHashCode + authorHashCode;

        String toStringExpected = "Book{isbn='" + testIsbn + "\', author='" + testAuthor + "\'}";

        String getTitleResult = testBook.getTitle();
        String getIsbnResult = testBook.getIsbn();
        String getAuthorResult = testBook.getAuthor();

        boolean equalsOtherMedium = testBook.equals(notEqualMedium);
        boolean equalsOtherDisc = testBook.equals(notEqualDisc);
        boolean equalsOtherBook = testBook.equals(notEqualBook);
        boolean equalsSelf = testBook.equals(testBook);

        int hashCodeResult = testBook.hashCode();

        String toStringResult = testBook.toString();

        assertEquals(testTitle, getTitleResult);
        assertEquals(testIsbn, getIsbnResult);
        assertEquals(testAuthor, getAuthorResult);

        assertFalse(equalsOtherMedium);
        assertFalse(equalsOtherDisc);
        assertFalse(equalsOtherBook);
        assertTrue(equalsSelf);

        assertEquals(finalHashCode, hashCodeResult);

        assertEquals(toStringExpected, toStringResult);
    }
}
