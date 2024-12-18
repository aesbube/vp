package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<EventBooking, Long> {
}
