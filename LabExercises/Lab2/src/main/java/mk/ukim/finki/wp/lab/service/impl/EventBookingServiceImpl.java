package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.BookingRepository;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final BookingRepository bookingRepository;

    public EventBookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets, String clientIp) {
        EventBooking booking =  new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets, clientIp);
        bookingRepository.save(booking);
    }

    @Override
    public List<EventBooking> getEventBookings() {
        return bookingRepository.findAll();
    }


}
