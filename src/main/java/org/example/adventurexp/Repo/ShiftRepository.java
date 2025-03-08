package org.example.adventurexp.Repo;

import org.example.adventurexp.Model.Instructor;
import org.example.adventurexp.Model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Integer> {

    List<Shift> findByInstructor(Instructor instructor);
}
