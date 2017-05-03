package edu.hm.shareit.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MediaServiceResult {
    PARAMETER_MISSING(404,"At least one parameter is missing."),
    ACCEPTED(202,"Request accepted"),
    INVALID_ISBN(400,"Invalid ISBN"),
    INVALID_BARCODE(400,"Invalid Barcode"),
    DUPLICATE_ISBN(400,"ISBN already exists"),
    ISBN_NOT_FOUND(404,"ISBN does not exist"),
    ISBN_DOES_NOT_MATCH(400,"ISBN in URI and ISBN in JSON-Request do not match!"),
    INVALID_DISC(400,"Invalid Disc-Barcode"),
    DUPLICATE_DISC(400,"Disc-Barcode already exists"),
    DISC_NOT_FOUND(404,"Disc-Barcode does not exist"),
    DISC_DOES_NOT_MATCH(400,"Disc-Barcode in URI and Disc-Barcode in JSON-Request do not match!");

    int code;
    String status;

    @JsonCreator
    MediaServiceResult(int code, String status) {
        setCode(code);
        setStatus(status);
    }

    public void setCode(int code){
        this.code = code;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
