package org.example.adventurexp.Controller;


import org.example.adventurexp.Model.Instructor;
import org.example.adventurexp.Model.Shift;
import org.example.adventurexp.Repo.InstructorRepository;
import org.example.adventurexp.Repo.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("instructors")
@CrossOrigin(origins = "*")
public class InstructorRestController {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    ShiftRepository shiftRepository;

    @GetMapping("/all")
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @GetMapping("/instructor/{instructor_id}/shifts")
    public List<Shift> getShiftsByInstructor(@PathVariable int instructor_id) {
        Optional<Instructor> instructor = instructorRepository.findById(instructor_id);
        if (instructor.isPresent()) {
            return shiftRepository.findByInstructor(instructor.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found with ID: " + instructor_id);
        }
    }

}

