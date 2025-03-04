package org.example.adventurexp.Controller;

import org.example.adventurexp.Model.Employee;
import org.example.adventurexp.Repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("employees")
@CrossOrigin(origins = "*")

public class EmployeeRestController {

    @Autowired
    EmployeeRepository employeeRepository;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Employee employee) {
        List<Employee> listOfEmployees = employeeRepository.findAll();

        for (Employee e : listOfEmployees) {
            if (e.getUsername().equals(employee.getUsername()) && e.getPassword().equals(employee.getPassword())) {

                if (e.getEmployee_id() == 1){
                    return ResponseEntity.ok("Welcome activity manager");
                } else if (e.getEmployee_id() == 2){
                    return ResponseEntity.ok("Welcome reservation manager");
                }

            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }













    @GetMapping("/all")
    public List<Employee> employees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee employee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // update an existing employee
    @PutMapping("/employee/{employee_id}")
    public ResponseEntity<Employee> putEmployee(@PathVariable int employee_id, @RequestBody Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employee_id);
        if (existingEmployee.isPresent()) {
            employeeRepository.save(employee); // function: Updates an existing employee if it exists in the database.
            return ResponseEntity.ok(employee); //200: ok
        } else {
            return ResponseEntity.notFound().build(); // 404: build
        }
    }

    @DeleteMapping("/employee/{employee_id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int employee_id) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employee_id);
        if (existingEmployee.isPresent()) {
            employeeRepository.deleteById(employee_id);
            return ResponseEntity.ok("Employee deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found"
            );
        }
    }

}
