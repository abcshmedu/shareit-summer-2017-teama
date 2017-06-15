package edu.hm.shareit.models.mediums;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Represents a disc with a unique barcode.
 * The barcode is unique, director, title and fsk can exist more than once.
 */
@Entity
@Table(name = "Disc")
public class Disc extends Medium {

    @Id
    @Column(name = "Barcode", length = 13)
    private String barcode;

    @Column(name = "Director")
    private String director;

    @Column(name = "FSK", length = 2)
    private int fsk;

    /**
     * The default constructor.
     */
    public Disc() {
        this(null, null, null, -1);
    }

    /**
     * The custom constructor.
     *
     * @param title    Title of the disc.
     * @param barcode  Barcode of the disc.
     * @param director Director of the disc's content.
     * @param fsk      fsk of the disc's content.
     */
    public Disc(String title,
                String barcode,
                String director,
                int fsk) {
        setTitle(title);
        setBarcode(barcode);
        setDirector(director);
        setFsk(fsk);
    }

    /**
     * Getter for the barcode.
     *
     * @return The barcode.
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Getter for the director.
     *
     * @return The director.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Getter for fsk.
     *
     * @return The fsk.
     */
    public int getFsk() {
        return fsk;
    }

    /**
     * Setter for the barcode.
     *
     * @param barcode The barcode.
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * Setter for the director.
     *
     * @param director The director.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Setter for fsk.
     *
     * @param fsk The fsk.
     */
    public void setFsk(int fsk) {
        this.fsk = fsk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Disc disc = (Disc) o;

        if (fsk != disc.fsk) {
            return false;
        }
        if (barcode != null ? !barcode.equals(disc.barcode) : disc.barcode != null) {
            return false;
        }
        return director != null ? director.equals(disc.director) : disc.director == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + fsk;
        return result;
    }

    @Override
    public String toString() {
        return "Disc{"
                + "barcode='" + barcode + "\'"
                + ", director='" + director + "\'"
                + ", fsk=" + fsk
                + "}";
    }
}
