package org.example.adventurexp.Controller;

import org.example.adventurexp.Model.Activity;
import org.example.adventurexp.Model.Booking;
import org.example.adventurexp.Model.Candy;
import org.example.adventurexp.Repo.ActivityRepository;
import org.example.adventurexp.Repo.BookingRepository;
import org.example.adventurexp.Repo.CandyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("bookings")
@CrossOrigin(origins = "*")

public class BookingRestController {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    CandyRepository candyRepository;



    @Autowired
    private ActivityRepository activityRepository;

    @GetMapping("/by-date")
    public List<Booking> getBookingsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return bookingRepository.findByBookingDate(date);
    }


    // show the list of all bookings
    @GetMapping("/all")
    public List<Booking> bookings() {
        return bookingRepository.findAll();
    }

    //Create a new booking : sending post request
    @PostMapping("/booking")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking booking(@RequestBody Booking booking) {
        // Håndter Candy-feltet som før
        if (booking.getCandy() != null && booking.getCandy().getName() != null && !booking.getCandy().getName().isEmpty()) {
            Optional<Candy> candyOptional = candyRepository.findByName(booking.getCandy().getName());
            booking.setCandy(candyOptional.orElse(null));
        } else {
            booking.setCandy(null);
        }
        // Håndter Activity-feltet: hent Activity-objektet ud fra navnet
        if (booking.getActivity() != null && booking.getActivity().getName() != null && !booking.getActivity().getName().isEmpty()) {
            Optional<Activity> activityOptional = activityRepository.findById(booking.getActivity().getName());
            booking.setActivity(activityOptional.orElse(null));
        } else {
            booking.setActivity(null);
        }

        return bookingRepository.save(booking);
    }

    // Update an existing booking
    @PutMapping("/booking/{bookingId}")
    public ResponseEntity<Booking> putBooking(@PathVariable int bookingId, @RequestBody Booking booking) { //ResponseEntity
        Optional<Booking> existingBooking = bookingRepository.findById(bookingId); //Optional for at undgå NULL objekter
        if (existingBooking.isPresent()) {
            bookingRepository.save(booking); //Function: Updates an existing booking if it exists in the database.
            return ResponseEntity.ok(booking); //200 : ok
        } else {
            return ResponseEntity.notFound().build(); // 404 : not found
        }
    }



    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<Booking> getBooking(@PathVariable int bookingId) {
        Optional<Booking> existingBooking = bookingRepository.findById(bookingId);
        if (existingBooking.isPresent()) {
            return ResponseEntity.ok(existingBooking.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @CrossOrigin(origins = "http://localhost:63343")
    @GetMapping("/editBooking/{phone}")
    public ResponseEntity<Booking> getBookingByPhone(@PathVariable String phone) {
        List<Booking> existingBookings = bookingRepository.findByPhone(phone);

        if (!existingBookings.isEmpty()) {
            return ResponseEntity.ok(existingBookings.get(0)); // Return the first booking
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("/editBooking/{phone}")
    public ResponseEntity<Booking> updateBookingByPhone(@PathVariable String phone, @RequestBody Booking updatedBooking) {
        // Fetch bookings by phone number
        List<Booking> bookings = bookingRepository.findByPhone(phone);
        if (bookings.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Get the first matching booking
        Booking bookingToUpdate = bookings.get(0);

        // Log the incoming updated booking for debugging
        System.out.println("Received Updated Booking: " + updatedBooking);

        // Update the booking details
        bookingToUpdate.setFirstName(updatedBooking.getFirstName());
        bookingToUpdate.setLastName(updatedBooking.getLastName());
        bookingToUpdate.setEmail(updatedBooking.getEmail());
        bookingToUpdate.setPhone(updatedBooking.getPhone());

        // Handle Activity: Update or save the activity
        if (updatedBooking.getActivity() != null) {
            Activity updatedActivity = updatedBooking.getActivity();
            System.out.println("Updating Activity to: " + updatedActivity.getName());

            // If the Activity already exists in the database, update it
            if (updatedActivity.getName() != null) {
                // Fetch the existing Activity from the database using its ID
                Activity existingActivity = activityRepository.findById(updatedActivity.getName()).orElse(null);

                if (existingActivity != null) {
                    // If activity exists, update its details
                    existingActivity.setName(updatedActivity.getName());
                    activityRepository.save(existingActivity);
                    bookingToUpdate.setActivity(existingActivity); // Set updated activity
                } else {
                    System.out.println("Activity with ID " + updatedActivity.getName() + " not found, creating new one.");
                    activityRepository.save(updatedActivity); // Save new activity if not found
                    bookingToUpdate.setActivity(updatedActivity); // Set new activity
                }
            } else {
                // If no ID exists (indicating a new activity), save it
                activityRepository.save(updatedActivity);
                bookingToUpdate.setActivity(updatedActivity); // Set the new activity
            }
        }

        // Update other booking details
        bookingToUpdate.setNumberOfGuests(updatedBooking.getNumberOfGuests());
        bookingToUpdate.setBookingDate(updatedBooking.getBookingDate());
        bookingToUpdate.setBookingTime(updatedBooking.getBookingTime());

        // Handle Candy: Fetch Candy by Name
        if (updatedBooking.getCandy() != null && updatedBooking.getCandy().getName() != null) {
            String candyName = updatedBooking.getCandy().getName(); // Get the name of the candy
            System.out.println("Received candy name: " + candyName); // Log the candyName for debugging

            // Fetch the Candy by Name from the database
            Optional<Candy> candy = candyRepository.findByName(candyName);

            if (candy.isEmpty()) {
                // If the Candy was not found, return a 404
                System.out.println("Candy with name " + candyName + " not found in the database.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            } else {
                System.out.println("Candy with name " + candyName + " found in the database.");
            }

            // If found, associate the Candy with the booking
            bookingToUpdate.setCandy(candy.orElse(null));
        }


        // Save the updated booking
        bookingRepository.save(bookingToUpdate);

        // Log the updated booking
        System.out.println("Booking updated successfully: " + bookingToUpdate);

        return ResponseEntity.ok(bookingToUpdate);
    }





    @GetMapping("/fetchBookingDetails/{phone}")
    public ResponseEntity<Booking> fetchBookingDetailsforDelete(@PathVariable String phone) {

        // Fetch the booking details
        List<Booking> bookings = bookingRepository.findByPhone(phone);

        if (!bookings.isEmpty()) {
            return ResponseEntity.ok(bookings.get(0));
        }  else {
            return ResponseEntity.notFound().build();
        }

    }



    @DeleteMapping("/deleteBooking/{phone}")
    public ResponseEntity<String> deleteBooking(@PathVariable String phone) {
        System.out.println("Received phone: " + phone);

        List<Booking> bookings = bookingRepository.findByPhone(phone);
        if (bookings.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        bookingRepository.deleteAll(bookings); // Delete all matching bookings
        return ResponseEntity.ok("Booking deleted successfully.");
    }



}
