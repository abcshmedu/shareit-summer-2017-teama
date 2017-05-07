package edu.hm.shareit.resources.copy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains all the status codes and messages needed for the CopyService.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CopyServiceResult {
    OK(200, "OK"),
    NO_OWNER_FOUND(404, "Owner is required in order to create new copy in database"),
    INVALID_URL_REQUEST(400, "Invalid URL-Request for this HTTP-Method"),
    PARAMETER_MISSING(404, "At least one parameter is missing."),
    ACCEPTED(202, "Request accepted"),
    INVALID_JSON_FORMAT(400, "Bad request. Please check json format and parameters"),
    INVALID_ISBN(400, "Invalid ISBN"),
    INVALID_BARCODE(400, "Invalid Barcode"),
    DUPLICATE_ISBN(400, "ISBN already exists"),
    ISBN_NOT_FOUND(404, "ISBN does not exist"),
    ISBN_DOES_NOT_MATCH(400, "ISBN in URI and ISBN in JSON-Request do not match!"),
    INVALID_DISC(400, "Invalid Disc-Barcode"),
    DUPLICATE_DISC(400, "Disc-Barcode already exists"),
    DISC_NOT_FOUND(404, "Disc-Barcode does not exist"),
    DISC_DOES_NOT_MATCH(400, "Disc-Barcode in URI and Disc-Barcode in JSON-Request do not match!");

    private int code;
    private String status;

    /**
     * Custom constructor.
     *
     * @param code   the status code.
     * @param status the status message.
     */
    CopyServiceResult(int code, String status) {
        setCode(code);
        setStatus(status);
    }

    /**
     * Getter for the status code.
     *
     * @return the status code.
     */
    @JsonProperty
    public int getCode() {
        return code;
    }

    /**
     * Getter for the status message.
     *
     * @return the status message.
     */
    @JsonProperty
    public String getStatus() {
        return status;
    }

    /**
     * Setter for the status code.
     *
     * @param code the status code.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * setter for the status message.
     *
     * @param status the status message.
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
