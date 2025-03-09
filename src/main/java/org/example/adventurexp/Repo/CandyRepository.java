package org.example.adventurexp.Repo;

import org.example.adventurexp.Model.Booking;
import org.example.adventurexp.Model.Candy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandyRepository extends JpaRepository<Candy, Integer> {
    Optional<Candy> findById(int candyId);

}
