package org.example.adventurexp.Repository;

import org.example.adventurexp.Model.Candy;
import org.example.adventurexp.Repo.CandyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CandyRepositoryTest {

    @Autowired
    private CandyRepository candyRepository;

    @Test
    public void testSaveAndFindCandyByName() {
        Candy candy = new Candy();
        // Forudsætter, at 'name' bruges som en entitetsattribut
        candy.setName("Chocolate");
        candyRepository.save(candy);

        Optional<Candy> fetched = candyRepository.findByName("Chocolate");
        assertTrue(fetched.isPresent());
        assertEquals("Chocolate", fetched.get().getName());
    }
}
