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


        Booking booking1 = new Booking();
        booking1.setFirstName("Alice");
        booking1.setLastName("Johnson");
        booking1.setEmail("alice.johnson@example.com");
        booking1.setPhone("+45 12345678");
        booking1.setActivity(a1);
        booking1.setNumberOfGuests(4);
        booking1.setBookingDate(LocalDate.of(2025, 3, 15));
        booking1.setBookingTime(LocalTime.of(11, 0));

        bookingRepository.save(booking1);

        Booking booking2 = new Booking();
        booking2.setFirstName("Bob");
        booking2.setLastName("Anderson");
        booking2.setEmail("bob.anderson@example.com");
        booking2.setPhone("+45 87654321");
        booking2.setActivity(a2);
        booking2.setNumberOfGuests(6);
        booking2.setBookingDate(LocalDate.of(2025, 3, 20));
        booking2.setBookingTime(LocalTime.of(14, 30));

        bookingRepository.save(booking2);

        Booking booking3 = new Booking();
        booking3.setFirstName("Charlie");
        booking3.setLastName("Brown");
        booking3.setEmail("charlie.brown@example.com");
        booking3.setPhone("+45 11223344");
        booking3.setActivity(a3);
        booking3.setNumberOfGuests(3);
        booking3.setBookingDate(LocalDate.of(2025, 3, 25));
        booking3.setBookingTime(LocalTime.of(10, 45));

        bookingRepository.save(booking3);


        Booking booking4 = new Booking();
        booking4.setFirstName("David");
        booking4.setLastName("Smith");
        booking4.setEmail("david.smith@example.com");
        booking4.setPhone("+45 55667788");
        booking4.setActivity(a4);
        booking4.setNumberOfGuests(2);
        booking4.setBookingDate(LocalDate.of(2025, 3, 28));
        booking4.setBookingTime(LocalTime.of(16, 15));

        bookingRepository.save(booking4);



    }
}

