package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingRepository {
    private static List<EventBooking> bookings = new ArrayList<>();

    public EventBooking save(EventBooking booking) {
        bookings.add(booking);
        return booking;
    }

    public List<EventBooking> findAll() {
        return bookings;
    }
}
