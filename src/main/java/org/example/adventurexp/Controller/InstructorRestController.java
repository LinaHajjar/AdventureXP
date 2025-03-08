package org.example.adventurexp.Controller;


import org.example.adventurexp.Model.Instructor;
import org.example.adventurexp.Model.Shift;
import org.example.adventurexp.Repo.InstructorRepository;
import org.example.adventurexp.Repo.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
        List<Shift> shifts = shiftRepository.findByInstructor(instructor.get());
        return shifts;
    }

    @GetMapping("/{instructor_id}")
    public Instructor findById(@PathVariable int instructor_id) {
        return instructorRepository.findById(instructor_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/update/{instructor_id}")
    public ResponseEntity<Instructor> update(@PathVariable int instructor_id, @RequestBody Instructor updatedInstructor) {
        Instructor instructor = instructorRepository.findById(instructor_id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        instructor.setFirst_name(updatedInstructor.getFirst_name());
        instructor.setLast_name(updatedInstructor.getLast_name());
        instructor.setInstructor_email(updatedInstructor.getInstructor_email());
        instructor.setInstructor_phone(updatedInstructor.getInstructor_phone());
        instructor.setInstructor_address(updatedInstructor.getInstructor_address());

        Instructor savedInstructor = instructorRepository.save(instructor);
        return ResponseEntity.ok(savedInstructor);

    }


}

