package edu.hm.shareit.models.mediums;

class Vars {

    static final int ONE_ARG_CONSTRUCTOR = 0;
    static final int MULTI_ARG_CONSTRUCTOR = 1;

    static final String title = "title";
    static final String otherTitle = "other_title";
    static final String nullTitle = null;

    static final String isbn = "1234567890123";
    static final String otherIsbn = "3210987654321";
    static final String nullIsbn = null;

    static final String barcode = "123456789012";
    static final String otherBarcode = "210987654321";
    static final String nullBarcode = null;

    static final String director = "director";
    static final String otherDirector = "other_director";
    static final String nullDirector = null;

    static final String author = "author";
    static final String otherAuthor = "other_author";
    static final String nullAuthor = null;

    static final int fsk = 12;
    static final int otherFsk = 14;
    static final int defaultFsk = -1;

    private static final String nonMatchingTitle = "non_matching_title";
    private static final String nonMatchingBarcode = "102938475610";
    private static final String nonMatchingIsbn = "1029384756102";
    private static final String nonMatchingDirector = "non_matching_director";
    private static final String nonMatchingAuthor = "non_matching_author";
    private static final int nonMatchingFsk = 18;

    static final Medium notEqualMedium = new Medium(nonMatchingTitle);
    static final Disc notEqualDisc = new Disc(nonMatchingTitle, nonMatchingBarcode, nonMatchingDirector, nonMatchingFsk);
    static final Book notEqualBook = new Book(nonMatchingTitle, nonMatchingIsbn, nonMatchingAuthor);
}
