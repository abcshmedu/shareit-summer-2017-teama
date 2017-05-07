package edu.hm.shareit.models.mediums;

/**
 * Represents a copy of a medium.
 * Contains information about the owner and the medium it is a copy of.
 */
public class Copy {
    private Medium medium;
    private String owner;

    /**
     * Default constructor.
     */
    public Copy() {
        this(null, null);
    }

    /**
     * Custom constructor.
     *
     * @param medium The medium this copy is a copy of.
     * @param owner  The owner of the copy.
     */
    public Copy(Medium medium, String owner) {
        setMedium(medium);
        setOwner(owner);
    }

    /**
     * Getter for the medium.
     *
     * @return The medium.
     */
    public Medium getMedium() {
        return medium;
    }

    /**
     * Getter for the owner.
     *
     * @return The owner.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Setter for the medium.
     *
     * @param medium The medium.
     */
    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    /**
     * Setter for the owner.
     *
     * @param owner The owner.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
