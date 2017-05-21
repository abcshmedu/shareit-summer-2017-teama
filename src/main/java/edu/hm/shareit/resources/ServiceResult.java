package edu.hm.shareit.resources;

import java.util.Collection;

public interface ServiceResult {
    /**
     * Getter for the status code.
     *
     * @return the status code.
     */
    int getCode();

    /**
     * Getter for the status message.
     *
     * @return the status message.
     */
    String getStatus();

    /**
     * Setter for the status code.
     *
     * @param code the status code.
     */
    void setCode(int code);

    /**
     * setter for the status message.
     *
     * @param status the status message.
     */
    void setStatus(String status);
}
