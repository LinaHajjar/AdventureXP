package org.example.adventurexp.Repo;

import org.example.adventurexp.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> getAllByBookingId(int bookingId);
    List<Booking> findByBookingDate(LocalDate bookingDate);
}
