package org.example.adventurexp.Controller;


import org.example.adventurexp.Model.Instructor;
import org.example.adventurexp.Model.Shift;
import org.example.adventurexp.Repo.InstructorRepository;
import org.example.adventurexp.Repo.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/instructor/{instructor_id")
    public Optional<Shift> getShiftsByInstructor(@PathVariable int instructor_id) {
        return shiftRepository.findById(instructor_id);
    }
}

