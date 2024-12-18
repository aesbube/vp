package mk.ukim.finki.wp.lab.service.impl;

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
    public void placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking booking =  new EventBooking();
        booking.setEventName(eventName);
        booking.setAttendeeName(attendeeName);
        booking.setAttendeeAddress(attendeeAddress);
        booking.setNumberOfTickets(numberOfTickets);
        bookingRepository.save(booking);
    }

    @Override
    public List<EventBooking> getEventBookings() {
        return bookingRepository.findAll();
    }


}
