package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text, Double minRating) {
        return eventRepository.searchEvents(text, minRating);
    }

    @Override
    public Optional<Event> saveEvent(String name, String description, Double popularityScore, Long locationID) {
        return eventRepository.saveEvent(name, description, popularityScore, locationID);
    }

    @Override
    public Optional<Event> editEvent(Long id, String name, String description, Double popularityScore, Long locationID) {
        return eventRepository.editEvent(id, name, description, popularityScore, locationID);
    }

    @Override
    public Optional<Event> deleteEvent(Long id) {
        return eventRepository.deleteEvent(id);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }


}
