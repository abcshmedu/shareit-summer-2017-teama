package edu.hm.shareit.models.mediums;

/**
 * Base class for all types of media.
 * Title field is inherited by all media.
 */
public class Medium {
    private String title;

    /**
     * Default constructor.
     */
    public Medium() {
        this(null);
    }

    /**
     * Custom constructor.
     *
     * @param title The title of the medium.
     */
    public Medium(String title) {
        setTitle(title);
    }

    /**
     * Getter for the title.
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the title.
     * @param title The title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Medium medium = (Medium) o;

        return title != null ? title.equals(medium.title) : medium.title == null;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Medium{"
                + "title='" + title + "\'"
                + "}";
    }
}
