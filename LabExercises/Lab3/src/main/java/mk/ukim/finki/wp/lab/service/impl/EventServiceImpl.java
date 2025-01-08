package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationService locationService;

    public EventServiceImpl(EventRepository eventRepository, LocationService locationService) {
        this.eventRepository = eventRepository;
        this.locationService = locationService;
    }

    public List<Event> listAll() {
        return eventRepository.findAll();
    }


    @Override
    public List<Event> searchEvents(String text, Double minRating, Long locationId) {
        if (text == null || text.isEmpty()) {
            if (minRating == null) {
                if (locationId == null) {
                    return eventRepository.findAll();
                } else {
                    return eventRepository.findAllByLocation_Id(locationId);
                }
            } else {
                if (locationId == null) {
                    return eventRepository.findAllByPopularityScoreGreaterThanEqual(minRating);
                } else {
                    return eventRepository.findByPopularityScoreGreaterThanEqualAndLocation_Id(minRating, locationId);
                }
            }
        } else {
            if (minRating == null) {
                if (locationId == null) {
                    return eventRepository.findByNameContainingIgnoreCase(text);
                } else {
                    return eventRepository.findByNameContainingIgnoreCaseAndLocation_Id(text, locationId);
                }
            } else {
                if (locationId == null) {
                    return eventRepository.findByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqual(text, minRating);
                } else {
                    return eventRepository.findByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqualAndLocation_Id(text, minRating, locationId);
                }
            }
        }
    }

    @Override
    public Optional<Event> saveEvent(String name, String description, Double popularityScore, Long locationID) {
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);

        Optional<Location> location = locationService.findById(locationID);

        if (location.isPresent()) {
            event.setLocation(location.get());
            return Optional.of(eventRepository.save(event));
        }
        throw new IllegalArgumentException("Invalid location");
    }

    @Override
    public Optional<Event> editEvent(Long id, String name, String description, Double popularityScore, Long locationID) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            Event editedEvent = event.get();
            editedEvent.setName(name);
            editedEvent.setDescription(description);
            editedEvent.setPopularityScore(popularityScore);
            Optional<Location> location = locationService.findById(locationID);
            if (location.isPresent()) {
                editedEvent.setLocation(location.get());
                return Optional.of(eventRepository.save(editedEvent));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Event> deleteEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            eventRepository.delete(event.get());
            return event;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> findAllByLocation_Id(Long locationId) {
        return eventRepository.findAllByLocation_Id(locationId);
    }


}
