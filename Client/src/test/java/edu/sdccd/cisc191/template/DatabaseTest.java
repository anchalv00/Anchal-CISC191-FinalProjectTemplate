package edu.sdccd.cisc191.template;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"edu.sdccd.cisc191.template.CustomerRequestRepository"})
class DatabaseTest {
    private CustomerRequestRepository repo;


    @org.junit.jupiter.api.Test
    void testObjectSave() {
        CustomerRequest request = new CustomerRequest(4);

        repo.save(request);
        CustomerRequest data = repo.findById(1L).orElse(null);

        assertEquals(4, data.getDay());
    }

}