package edu.sdccd.cisc191.template;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "Rehicle")
@Table(name = "request")
public class CustomerRequest implements Serializable {

    @Column
    private int day;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * @param day integer requested by client
     */
    public CustomerRequest(int day) {
        this.day = day;
    }

    public CustomerRequest() {

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
