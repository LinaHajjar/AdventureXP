package org.example.adventurexp.Config;

import org.example.adventurexp.Model.*;
import org.example.adventurexp.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    BookingRepository bookingRepository;


    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    CandyRepository candyRepository;


    @Autowired
    InstructorRepository instructorRepository;


    @Autowired
    ShiftRepository shiftRepository;

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


        Candy candy1 = new Candy();
        Candy candy2 = new Candy();
        Candy candy3 = new Candy();



        candy1.setName("Candyflush");
        candy1.setPrice(14.99);

        candy2.setName("Dubai");
        candy2.setPrice(59.99);

        candy3.setName("Popcorn");
        candy3.setPrice(29.99);


        candyRepository.save(candy1);
        candyRepository.save(candy2);
        candyRepository.save(candy3);



        // Bookings for Activity 1
        Booking booking1a = new Booking();
        booking1a.setFirstName("Eve");
        booking1a.setLastName("Williams");
        booking1a.setEmail("eve.williams@example.com");
        booking1a.setPhone("+45 99887766");
        booking1a.setActivity(a1);
        booking1a.setNumberOfGuests(5);
        booking1a.setBookingDate(LocalDate.of(2025, 3, 16));
        booking1a.setBookingTime(LocalTime.of(10, 30));

        bookingRepository.save(booking1a);

        Booking booking1b = new Booking();
        booking1b.setFirstName("Frank");
        booking1b.setLastName("Miller");
        booking1b.setEmail("frank.miller@example.com");
        booking1b.setPhone("+45 22334455");
        booking1b.setActivity(a1);
        booking1b.setNumberOfGuests(2);
        booking1b.setBookingDate(LocalDate.of(2025, 3, 12));
        booking1b.setBookingTime(LocalTime.of(12, 45));

        bookingRepository.save(booking1b);

        Booking booking1c = new Booking();
        booking1c.setFirstName("Grace");
        booking1c.setLastName("Harrison");
        booking1c.setEmail("grace.harrison@example.com");
        booking1c.setPhone("+45 66778899");
        booking1c.setActivity(a1);
        booking1c.setNumberOfGuests(4);
        booking1c.setBookingDate(LocalDate.of(2025, 3, 22));
        booking1c.setBookingTime(LocalTime.of(15, 15));

        bookingRepository.save(booking1c);


// Bookings for Activity 2
        Booking booking2a = new Booking();
        booking2a.setFirstName("Henry");
        booking2a.setLastName("Clark");
        booking2a.setEmail("henry.clark@example.com");
        booking2a.setPhone("+45 33221144");
        booking2a.setActivity(a2);
        booking2a.setNumberOfGuests(3);
        booking2a.setBookingDate(LocalDate.of(2025, 3, 21));
        booking2a.setBookingTime(LocalTime.of(10, 45));

        bookingRepository.save(booking2a);

        Booking booking2b = new Booking();
        booking2b.setFirstName("Isabel");
        booking2b.setLastName("Martinez");
        booking2b.setEmail("isabel.martinez@example.com");
        booking2b.setPhone("+45 77889900");
        booking2b.setActivity(a2);
        booking2b.setNumberOfGuests(7);
        booking2b.setBookingDate(LocalDate.of(2025, 3, 23));
        booking2b.setBookingTime(LocalTime.of(13, 30));

        bookingRepository.save(booking2b);

        Booking booking2c = new Booking();
        booking2c.setFirstName("Jack");
        booking2c.setLastName("Taylor");
        booking2c.setEmail("jack.taylor@example.com");
        booking2c.setPhone("+45 88990011");
        booking2c.setActivity(a2);
        booking2c.setNumberOfGuests(5);
        booking2c.setBookingDate(LocalDate.of(2025, 3, 23));
        booking2c.setBookingTime(LocalTime.of(15, 00));

        bookingRepository.save(booking2c);


// Bookings for Activity 3
        Booking booking3a = new Booking();
        booking3a.setFirstName("Kelly");
        booking3a.setLastName("Davis");
        booking3a.setEmail("kelly.davis@example.com");
        booking3a.setPhone("+45 55668899");
        booking3a.setActivity(a3);
        booking3a.setNumberOfGuests(4);
        booking3a.setBookingDate(LocalDate.of(2025, 3, 23));
        booking3a.setBookingTime(LocalTime.of(11, 15));

        bookingRepository.save(booking3a);

        Booking booking3b = new Booking();
        booking3b.setFirstName("Liam");
        booking3b.setLastName("Walker");
        booking3b.setEmail("liam.walker@example.com");
        booking3b.setPhone("+45 99001122");
        booking3b.setActivity(a3);
        booking3b.setNumberOfGuests(6);
        booking3b.setBookingDate(LocalDate.of(2025, 3, 29));
        booking3b.setBookingTime(LocalTime.of(14, 45));

        bookingRepository.save(booking3b);

        Booking booking3c = new Booking();
        booking3c.setFirstName("Mia");
        booking3c.setLastName("Scott");
        booking3c.setEmail("mia.scott@example.com");
        booking3c.setPhone("+45 22113344");
        booking3c.setActivity(a3);
        booking3c.setNumberOfGuests(3);
        booking3c.setBookingDate(LocalDate.of(2025, 3, 30));
        booking3c.setBookingTime(LocalTime.of(16, 00));

        bookingRepository.save(booking3c);


// Bookings for Activity 4
        Booking booking4a = new Booking();
        booking4a.setFirstName("Nathan");
        booking4a.setLastName("White");
        booking4a.setEmail("nathan.white@example.com");
        booking4a.setPhone("+45 33445566");
        booking4a.setActivity(a4);
        booking4a.setNumberOfGuests(2);
        booking4a.setBookingDate(LocalDate.of(2025, 3, 31));
        booking4a.setBookingTime(LocalTime.of(10, 30));

        bookingRepository.save(booking4a);

        Booking booking4b = new Booking();
        booking4b.setFirstName("Olivia");
        booking4b.setLastName("Lopez");
        booking4b.setEmail("olivia.lopez@example.com");
        booking4b.setPhone("+45 77889955");
        booking4b.setActivity(a4);
        booking4b.setNumberOfGuests(5);
        booking4b.setBookingDate(LocalDate.of(2025, 4, 2));
        booking4b.setBookingTime(LocalTime.of(12, 00));

        bookingRepository.save(booking4b);

        Booking booking4c = new Booking();
        booking4c.setFirstName("Peter");
        booking4c.setLastName("Adams");
        booking4c.setEmail("peter.adams@example.com");
        booking4c.setPhone("+45 66770088");
        booking4c.setActivity(a4);
        booking4c.setNumberOfGuests(4);
        booking4c.setBookingDate(LocalDate.of(2025, 4, 5));
        booking4c.setBookingTime(LocalTime.of(14, 15));

        bookingRepository.save(booking4c);


        Instructor instructor1 = new Instructor();
        instructor1.setFirst_name("Victor");
        instructor1.setLast_name("Scott");
        instructor1.setInstructor_address("Victor123@hotmail.com");
        instructor1.setInstructor_phone("+45 66770088");
        instructor1.setInstructor_address("Mårkærvej 15");

        Instructor instructor2 = new Instructor();
        instructor2.setFirst_name("Emma");
        instructor2.setLast_name("Johnson");
        instructor2.setInstructor_email("emma.johnson@example.com");
        instructor2.setInstructor_phone("+45 55889900");
        instructor2.setInstructor_address("Roskildevej 42");

        Instructor instructor3 = new Instructor();
        instructor3.setFirst_name("Lucas");
        instructor3.setLast_name("Andersen");
        instructor3.setInstructor_email("lucas.andersen@example.com");
        instructor3.setInstructor_phone("+45 44776655");
        instructor3.setInstructor_address("Hovedgaden 78");

        Instructor instructor4 = new Instructor();
        instructor4.setFirst_name("Sofia");
        instructor4.setLast_name("Nielsen");
        instructor4.setInstructor_email("sofia.nielsen@example.com");
        instructor4.setInstructor_phone("+45 33445566");
        instructor4.setInstructor_address("Sønder Allé 21");

        Instructor instructor5 = new Instructor();
        instructor5.setFirst_name("Noah");
        instructor5.setLast_name("Christensen");
        instructor5.setInstructor_email("noah.christensen@example.com");
        instructor5.setInstructor_phone("+45 22998877");
        instructor5.setInstructor_address("Lyngbyvej 55");

        Instructor instructor6 = new Instructor();
        instructor6.setFirst_name("Isabella");
        instructor6.setLast_name("Larsen");
        instructor6.setInstructor_email("isabella.larsen@example.com");
        instructor6.setInstructor_phone("+45 66778899");
        instructor6.setInstructor_address("Østerbrogade 33");


        instructorRepository.save(instructor1);
        instructorRepository.save(instructor2);
        instructorRepository.save(instructor3);
        instructorRepository.save(instructor4);
        instructorRepository.save(instructor5);
        instructorRepository.save(instructor6);


        generateWeeklyShifts();

    }


    public void generateWeeklyShifts() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<Activity> activities = activityRepository.findAll();


        int instructorIndex = 0; // Track which instructor is next

        // Set start date to upcoming Monday
        LocalDate startDate = LocalDate.now().with(DayOfWeek.MONDAY);

        for (int i = 0; i < 5; i++){
            LocalDate shiftDate = startDate.plusDays(i);

            for (Activity activity : activities){
                Instructor assignedInstructor = instructors.get(instructorIndex);

                Shift shift = new Shift();
                shift.setDate(shiftDate);
                shift.setActivity(activity);
                shift.setInstructor(assignedInstructor);

                shiftRepository.save(shift);

                // Rotate instructor assignment
                instructorIndex = (instructorIndex + 1) % instructors.size();
            }

        }

    }

}

