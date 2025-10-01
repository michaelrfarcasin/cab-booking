package com.booking.cab.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.booking.cab.repository.entity.Driver;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
class DriverRepositoryIntegrationTest {
    @Autowired
    private DriverRepository driverRepository;

    @Test
    void seededDriversShouldBePresent() {
        List<Driver> drivers = driverRepository.findAll();
        // assert we have at least one seeded driver
        assertFalse(drivers.isEmpty(), "Expected seeded drivers to be present in the repository");
        // assert driver with id 1 and name Alpha exists
        assertTrue(drivers.stream().anyMatch(d -> d.getId() == 1 && "Alpha".equals(d.getName())),
                "Expected a driver with id=1 and name=Alpha to be present");
    }
}
