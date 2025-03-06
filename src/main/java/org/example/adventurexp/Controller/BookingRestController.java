package org.example.adventurexp.Controller;

import org.example.adventurexp.Model.Booking;
import org.example.adventurexp.Repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("bookings")
@CrossOrigin(origins = "*")

public class BookingRestController {

    @Autowired
    BookingRepository bookingRepository;


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


    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable int bookingId) {
        Optional<Booking> existingBooking = bookingRepository.findById(bookingId);
        if (existingBooking.isPresent()) {
            bookingRepository.deleteById(bookingId);
            return ResponseEntity.ok("Booking deleted"); //ResponseEntity.ok : status 200
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found"); //status: 404
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





    @GetMapping("/editBooking/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int bookingId) {
        Optional<Booking> existingBooking = bookingRepository.findById(bookingId);
        return existingBooking.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PutMapping("/editBooking/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable int bookingId, @RequestBody Booking updatedBooking) {
        Optional<Booking> existingBooking = bookingRepository.findById(bookingId);

        if (existingBooking.isPresent()) {
            Booking bookingToUpdate = existingBooking.get();

            // Update all fields from the updatedBooking (from the form submission)
            bookingToUpdate.setFirstName(updatedBooking.getFirstName());
            bookingToUpdate.setLastName(updatedBooking.getLastName());
            bookingToUpdate.setEmail(updatedBooking.getEmail());
            bookingToUpdate.setPhone(updatedBooking.getPhone());
            bookingToUpdate.setActivity(updatedBooking.getActivity());
            bookingToUpdate.setNumberOfGuests(updatedBooking.getNumberOfGuests());
            bookingToUpdate.setBookingDate(updatedBooking.getBookingDate());
            bookingToUpdate.setBookingTime(updatedBooking.getBookingTime());

            // Save the updated booking
            bookingRepository.save(bookingToUpdate);

            return ResponseEntity.ok(bookingToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
