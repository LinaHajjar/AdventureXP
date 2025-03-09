package org.example.adventurexp.Controller;


import jakarta.persistence.Entity;
import org.example.adventurexp.Model.Candy;
import org.example.adventurexp.Repo.CandyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("candy")
@CrossOrigin(origins = "*")
public class CandyRestController {

    @Autowired
    CandyRepository candyRepository;


    @GetMapping("/all")
    public List<Candy> allCandy(){
        return candyRepository.findAll();
    }

    @PutMapping("/update/{name}")
    public Candy updateCandyPrice(@PathVariable String name, @RequestBody Candy updatedCandy){

        Optional<Candy> findCandy = candyRepository.findByName(name);
        if(findCandy.isPresent()){
            Candy candy = findCandy.get();
            candy.setPrice(updatedCandy.getPrice());
            return candyRepository.save(candy);
        } else {
            throw new RuntimeException("Candy not found: " + name);
        }

    }



}
