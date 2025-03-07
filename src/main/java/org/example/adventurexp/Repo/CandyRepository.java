package org.example.adventurexp.Repo;

import org.example.adventurexp.Model.Candy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandyRepository extends JpaRepository<Candy, Integer> {
}
