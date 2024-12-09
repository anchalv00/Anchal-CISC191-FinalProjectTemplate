package edu.sdccd.cisc191.template;

import java.io.Serializable;

public class CustomerRequest implements Serializable {

    private int day;

    /**
     * @param day integer requested by client
     */
    public CustomerRequest(int day) {
        this.day = day;
    }

    /**
     * @return the day value
     */
    public int getDay() {
        return day;
    }

    /**
     * @return a String with the day value
     */
    @Override
    public String toString() {
        return String.format(
                "Customer[day=%d]",
                day);
    }
}
