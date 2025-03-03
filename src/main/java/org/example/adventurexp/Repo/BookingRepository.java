package org.example.adventurexp.Repo;

import org.example.adventurexp.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> getAllByBookingId(int bookingId);
}
