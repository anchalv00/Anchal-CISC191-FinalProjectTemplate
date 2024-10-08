package edu.sdccd.cisc191.template;

import java.io.Serializable;

/**
 * This class sends a request to the server
 * to update the file
 */
public class UpdateRequest implements Serializable {
    private String lineToAdd;

    public UpdateRequest(String lineToAdd) {
        this.lineToAdd = lineToAdd;
    }

    /**
     * gets the lines to add to file
     * @return lines
     */
    public String getLineToAdd() {
        return lineToAdd;
    }

    @Override
    public String toString() {
        return "UpdateRequest{" +
                "lineToAdd='" + lineToAdd + '\'' +
                '}';
    }
}
