package org.example.adventurexp.Controller;

import org.example.adventurexp.Repo.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("shifts")
@CrossOrigin(origins="*")
public class shiftsRestController {

    @Autowired
    ShiftRepository shiftRepository;


}
