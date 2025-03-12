package org.example.adventurexp.Repository;

import org.example.adventurexp.Model.Booking;
import org.example.adventurexp.Repo.BookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void testSaveAndFindBooking() {
        Booking booking = new Booking();
        booking.setPhone("12345678");
        booking.setBookingDate(LocalDate.now());
        bookingRepository.save(booking);

        // Test findByBookingDate
        List<Booking> bookingsByDate = bookingRepository.findByBookingDate(LocalDate.now());
        assertEquals(1, bookingsByDate.size());

        // Test findByPhone
        List<Booking> bookingsByPhone = bookingRepository.findByPhone("12345678");
        assertEquals(1, bookingsByPhone.size());

        // Test getAllByBookingId
        List<Booking> bookingsById = bookingRepository.getAllByBookingId(1);
        assertEquals(1, bookingsById.size());
    }
}

