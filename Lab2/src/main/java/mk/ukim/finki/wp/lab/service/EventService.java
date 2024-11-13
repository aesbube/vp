package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text, Double minRating);
    Optional<Event> saveEvent(String name, String description, Double popularityScore, Long locationID);
    Optional<Event> editEvent(Long id, String name, String description, Double popularityScore, Long locationID);
    Optional<Event> deleteEvent(Long id);
    Optional<Event> findById(Long id);
}




