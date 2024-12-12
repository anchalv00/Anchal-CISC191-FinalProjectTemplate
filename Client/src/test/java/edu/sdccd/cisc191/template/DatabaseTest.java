package edu.sdccd.cisc191.template;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DatabaseTest {

    @Autowired
    private CustomerRequestRepository repo;


    @Test
    void testObjectSave() {
        CustomerRequest request = new CustomerRequest(4);

        repo.save(request);
        Optional<CustomerRequest> retrievedData = repo.findById(request.getId());

        assertTrue(retrievedData.isPresent());
        assertEquals(4, retrievedData.get().getDay());
    }

}