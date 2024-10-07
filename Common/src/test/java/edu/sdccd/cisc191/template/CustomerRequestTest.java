package edu.sdccd.cisc191.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerRequestTest {
    private CustomerRequest customerRequest;

    /*
     * creates an instance of the CustomerRequest class
     */
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        customerRequest = new CustomerRequest(1);
    }

    /*
     * tests the toString method in the CustomerRequest class
     */
    @org.junit.jupiter.api.Test
    void getCustomerPrint() {
        assertEquals(customerRequest.toString(), "Customer[day=1]");
    }

    /*
     * tests the getDay method in the CustomerRequest class
     */
    @org.junit.jupiter.api.Test
    void getCustomer() {
        assertEquals(customerRequest.getDay(), 1);
    }
}