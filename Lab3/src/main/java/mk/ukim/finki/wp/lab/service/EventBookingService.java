package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.EventBooking;

import java.util.List;

public interface EventBookingService {
    void placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);

    List<EventBooking> getEventBookings();
}