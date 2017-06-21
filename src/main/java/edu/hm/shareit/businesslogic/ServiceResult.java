package edu.hm.shareit.businesslogic;

/**
 * The ServiceResult.
 */
public interface ServiceResult {
    /**
     * Getter for the status code.
     *
     * @return the status code.
     */
    int getCode();

    /**
     * Setter for the status code.
     *
     * @param code the status code.
     */
    void setCode(int code);

    /**
     * Getter for the status message.
     *
     * @return the status message.
     */
    String getStatus();

    /**
     * setter for the status message.
     *
     * @param status the status message.
     */
    void setStatus(String status);
}
