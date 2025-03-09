package org.example.adventurexp.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.adventurexp.Model.Booking;
import org.example.adventurexp.Model.Candy;
import org.example.adventurexp.Repo.BookingRepository;
import org.example.adventurexp.Repo.CandyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("bookings")
@CrossOrigin(origins = "*")
public class BookingRestController {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    CandyRepository candyRepository;



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

//
//    @DeleteMapping("/booking/{bookingId}")
//    public ResponseEntity<String> deleteBooking(@PathVariable int bookingId) {
//        Optional<Booking> existingBooking = bookingRepository.findById(bookingId);
//        if (existingBooking.isPresent()) {
//            bookingRepository.deleteById(bookingId);
//            return ResponseEntity.ok("Booking deleted"); //ResponseEntity.ok : status 200
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found"); //status: 404
//        }
//    }

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

        // Update the booking details
        bookingToUpdate.setFirstName(updatedBooking.getFirstName());
        bookingToUpdate.setLastName(updatedBooking.getLastName());
        bookingToUpdate.setEmail(updatedBooking.getEmail());
        bookingToUpdate.setPhone(updatedBooking.getPhone());
        bookingToUpdate.setActivity(updatedBooking.getActivity());
        bookingToUpdate.setNumberOfGuests(updatedBooking.getNumberOfGuests());
        bookingToUpdate.setBookingDate(updatedBooking.getBookingDate());
        bookingToUpdate.setBookingTime(updatedBooking.getBookingTime());

        // Handle Candy: save or update the Candy entity
        if (updatedBooking.getCandy() != null) {
            Candy candy = updatedBooking.getCandy();
            System.out.println("Received candyId: " + candy.getCandyId()); // Log the candyId for debugging

            if (candy.getCandyId() == 0) { // This indicates it's a new Candy (ID is auto-generated)
                // If it's a new Candy, save it first
                candyRepository.save(candy);
                bookingToUpdate.setCandy(candy);
            } else {
                // If it's an existing Candy, try to find it by ID
                Candy existingCandy = candyRepository.findById(candy.getCandyId()).orElse(null);

                if (existingCandy == null) {
                    // If the Candy was not found in the database, return a 404
                    System.out.println("Candy with id " + candy.getCandyId() + " not found in the database.");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }

                // If found, update the existing Candy
                existingCandy.setName(candy.getName());
                existingCandy.setPrice(candy.getPrice());
                candyRepository.save(existingCandy);
                bookingToUpdate.setCandy(existingCandy);
            }
        }

        // Save the updated booking
        bookingRepository.save(bookingToUpdate);

        return ResponseEntity.ok(bookingToUpdate);
    }



//
//    @GetMapping("/fetchBookingDetails/{phone}")
//    public ResponseEntity<Booking> fetchBookingDetailsforDelete(@PathVariable String phone) {
//
//        String decodedPhone = URLDecoder.decode(phone, StandardCharsets.UTF_8);
//        List<Booking> bookings = bookingRepository.findByPhone(decodedPhone);
//
//        if (bookings.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(bookings.get(0)); // Send back the first matching booking
//    }


    @GetMapping("/fetchBookingDetails/{phone}")
    public ResponseEntity<Booking> fetchBookingDetailsforDelete(@PathVariable String phone) {

//        String decodedPhone = URLDecoder.decode(phone, StandardCharsets.UTF_8);

        // Fetch the booking details
        List<Booking> bookings = bookingRepository.findByPhone(phone);

        if (!bookings.isEmpty()) {
            return ResponseEntity.ok(bookings.get(0));
        }  else {
            return ResponseEntity.notFound().build();
        }

    }



//    @CrossOrigin(origins = "http://localhost:63342")
//    @DeleteMapping("/deleteBooking/{phone}")
//    public ResponseEntity<String> deleteBookingByPhone(@PathVariable String phone) {
//        try {
//            String decodedPhone = URLDecoder.decode(phone, StandardCharsets.UTF_8);
//            List<Booking> bookings = bookingRepository.findByPhone(decodedPhone);
//
//            if (bookings.isEmpty()) {
//                return ResponseEntity.notFound().build(); // Return 404 if no booking found
//            }
//
//            Booking bookingToDelete = bookings.get(0);
//            bookingRepository.delete(bookingToDelete);
//
//            return ResponseEntity.ok("Booking deleted successfully"); // Return a success message
//        } catch (Exception e) {
//            // Log the error if something goes wrong
//            System.out.println("Error deleting booking: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting booking"); // Return 500 if there was an error
//        }
//    }

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
