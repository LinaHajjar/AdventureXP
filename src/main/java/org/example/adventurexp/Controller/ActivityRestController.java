package org.example.adventurexp.Controller;

import org.example.adventurexp.Model.Activity;
import org.example.adventurexp.Repo.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("activities")
@CrossOrigin(origins = "*") // for at sikre at frontend kan tilgå backend
public class ActivityRestController {

    @Autowired
    ActivityRepository activityRepository;

    // show all the activities
    @GetMapping("/all")
    public List<Activity> getActivities() {
        return activityRepository.findAll();
    }

    //Create a new activity
    @PostMapping("/activity")
    @ResponseStatus(HttpStatus.CREATED)
    public Activity createActivity(@RequestBody Activity activity) {
        return activityRepository.save(activity);
    }

    //Update an existing activity
    @PutMapping("/activity/{name}")
    public ResponseEntity<Activity> putActivity(@PathVariable String name, @RequestBody Activity activity) {
        Optional<Activity> existingActivity = activityRepository.findById(name);
        if (existingActivity.isPresent()) {
            activityRepository.save(activity);
            return ResponseEntity.ok(activity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Delete an activity
    @DeleteMapping("/activity/{name}")
    public ResponseEntity<String> deleteActivity(@PathVariable String name) {
        Optional<Activity> existingActivity = activityRepository.findById(name);
        if (existingActivity.isPresent()) {
            activityRepository.deleteById(name);
            return ResponseEntity.ok("Activity deleted");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("activity not found");
        }
    }
}
