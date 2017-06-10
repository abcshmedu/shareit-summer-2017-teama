package edu.hm.shareit.models.mediums;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Represents a copy of a medium.
 * Contains information about the owner and the medium it is a copy of.
 */

@Entity
@Table(name = "TCopy")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Copy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TCopy_Id")
    private long id;

    @Column(name = "TCopy_Medium")
    private Medium medium;

    @Column(name = "TCopy_Owner")
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
