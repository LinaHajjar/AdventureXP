package org.example.adventurexp.Repo;

import org.example.adventurexp.Model.Candy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandyRepository extends JpaRepository<Candy, Integer> {
    Optional<Candy> findByName(String name);

}
