package org.example.adventurexp.Config;

import org.example.adventurexp.Model.Activity;
import org.example.adventurexp.Model.Booking;
import org.example.adventurexp.Model.Employee;
import org.example.adventurexp.Repo.ActivityRepository;
import org.example.adventurexp.Repo.BookingRepository;
import org.example.adventurexp.Repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    BookingRepository bookingRepository;


    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        Activity a1 = new Activity();
        a1.setName("Go Kart");
        a1.setDescription("Gokart!!! Drive around with your friends and family and have an enjoyable day with racing and the wind rushing through you.\nAge requirement: 12\nHeight requirement: 150 cm");
        a1.setDuration(60);
        a1.setPrice(360);
        activityRepository.save(a1);

        Activity a2 = new Activity();
        a2.setName("Paintball");
        a2.setDescription("Exciting and strategic team game where you test your skills in a thrilling battle.\nAge requirement: 14\nHeight requirement: 160 cm");
        a2.setDuration(90);
        a2.setPrice(500);
        activityRepository.save(a2);

        Activity a3 = new Activity();
        a3.setName("Mini Golf");
        a3.setDescription("Fun and relaxing outdoor activity suitable for all ages. Perfect for families and friends!\nAge requirement: None\nHeight requirement: None");
        a3.setDuration(45);
        a3.setPrice(200);
        activityRepository.save(a3);

        Activity a4 = new Activity();
        a4.setName("Sumo Wrestling");
        a4.setDescription("Hilarious and competitive activity where you put on a giant sumo suit and try to push your opponent out of the ring!\nAge requirement: 10\nHeight requirement: 140 cm");
        a4.setDuration(30);
        a4.setPrice(400);
        activityRepository.save(a4);


        Employee employee1 = new Employee();

        employee1.setUsername("bruger1");
        employee1.setPassword("1234");

        Employee employee2 = new Employee();

        employee2.setUsername("bruger2");
        employee2.setPassword("1234");

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

    }

}
