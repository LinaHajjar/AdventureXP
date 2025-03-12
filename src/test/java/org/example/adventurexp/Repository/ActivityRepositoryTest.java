package org.example.adventurexp.Repository;

import org.example.adventurexp.Model.Activity;
import org.example.adventurexp.Repo.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ActivityRepositoryTest {

    @Autowired
    private ActivityRepository activityRepository;

    @Test
    public void testSaveAndFindActivity() {
        Activity activity = new Activity();
        activity.setName("Hiking");
        activityRepository.save(activity);

        Optional<Activity> fetched = activityRepository.findById("Hiking");
        // Verificer, at aktiviteten findes
        assertEquals(true, fetched.isPresent());
        // Verificer, at navnet matcher
        assertEquals("Hiking", fetched.get().getName());

        // Hent alle aktiviteter og verificer, at vi har præcis 1 aktivitet
        List<Activity> allActivities = activityRepository.findAll();
        assertEquals(1, allActivities.size());
    }
}
