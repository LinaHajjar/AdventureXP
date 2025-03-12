package org.example.adventurexp.Repository;

import org.example.adventurexp.Model.Employee;
import org.example.adventurexp.Repo.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveAndFindEmployee() {
        Employee employee = new Employee();
        // Fjern den manuelle sætning af id'et, hvis det er auto-genereret
        employee.setUsername("testuser");
        employee.setPassword("password");

        // Gem employee og få den gemte instans med auto-genereret id
        Employee savedEmployee = employeeRepository.save(employee);

        // Hent employee baseret på det genererede id
        Employee fetched = employeeRepository.findById(savedEmployee.getEmployee_id()).orElse(null);

        // Sammenlign brugernavnet med assertEquals
        assertEquals("testuser", fetched.getUsername());
    }
}

