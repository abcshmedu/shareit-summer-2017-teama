package edu.hm.shareit.models;

import edu.hm.shareit.models.authentication.Token;
import edu.hm.shareit.models.authentication.User;
import edu.hm.shareit.models.mediums.Book;
import edu.hm.shareit.models.mediums.Disc;
import edu.hm.shareit.models.mediums.Medium;

public class Vars {

    public static final String title = "title";
    public static final String otherTitle = "other_title";
    public static final String nullTitle = null;

    public static final String isbn = "1234567890123";
    public static final String otherIsbn = "3210987654321";
    public static final String nullIsbn = null;

    public static final String barcode = "123456789012";
    public static final String otherBarcode = "210987654321";
    public static final String nullBarcode = null;

    public static final String director = "director";
    public static final String otherDirector = "other_director";
    public static final String nullDirector = null;

    public static final String author = "author";
    public static final String otherAuthor = "other_author";
    public static final String nullAuthor = null;

    public static final int fsk = 12;
    public static final int otherFsk = 14;
    public static final int defaultFsk = -1;

    public static final String username = "user";
    public static final String otherUsername = "other_user";
    public static final String nullUsername = null;

    public static final String password = "password";
    public static final String otherPassword = "other_password";
    public static final String nullPassword = null;

    public static final String tokenStr = "token";
    public static final String otherTokenStr = "other_token";
    public static final String nullTokenStr = null;

    private static final String nonMatchingTitle = "non_matching_title";
    private static final String nonMatchingBarcode = "102938475610";
    private static final String nonMatchingIsbn = "1029384756102";
    private static final String nonMatchingDirector = "non_matching_director";
    private static final String nonMatchingAuthor = "non_matching_author";
    private static final int nonMatchingFsk = 18;
    private static final String nonMatchingUsername = "non_matching_username";
    private static final String nonMatchingPassword = "non_matching_password";
    private static final String nonMatchingToken = "non_matching_token";

    public static final Disc testDisc = new Disc(title, barcode, director, fsk);
    public static final Book testBook = new Book(title, isbn, author);
    public static final User testUser = new User(username, password);

    public static final Medium notEqualMedium = new Medium(nonMatchingTitle);
    public static final Disc notEqualDisc = new Disc(nonMatchingTitle, nonMatchingBarcode, nonMatchingDirector, nonMatchingFsk);
    public static final Book notEqualBook = new Book(nonMatchingTitle, nonMatchingIsbn, nonMatchingAuthor);
    public static final User notEqualUser = new User(nonMatchingUsername, nonMatchingPassword);
    public static final Token notEqualToken = new Token(nonMatchingToken);
}
