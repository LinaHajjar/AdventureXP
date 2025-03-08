package org.example.adventurexp.Repo;

import org.example.adventurexp.Model.Instructor;
import org.example.adventurexp.Model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {

    List<Shift> findByInstructor(Instructor instructor);
}
