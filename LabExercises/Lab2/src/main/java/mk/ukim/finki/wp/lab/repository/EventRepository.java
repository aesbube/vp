package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    private List<Event> events = new ArrayList<>();
    private final LocationService locationService;

    public EventRepository(LocationService locationService) {
        this.locationService = locationService;
        List<Location> locations = locationService.findAll();
        events.add(new Event("Tame Impala Concert", "Psychedelic Night in San Francisco", 9.2, locations.get(0)));
        events.add(new Event("The National Concert", "Intimate Evening in Brooklyn", 9.6, locations.get(1)));
        events.add(new Event("Florence + The Machine Concert", "Magical Performance in London", 8.9, locations.get(2)));
        events.add(new Event("Arcade Fire Concert", "Indie Rock Extravaganza in Montreal", 9.0, locations.get(3)));
        events.add(new Event("Bon Iver Concert", "Acoustic Session in Minneapolis", 8.3, locations.get(4)));
        events.add(new Event("Vampire Weekend Concert", "Summer Festival in Chicago", 8.7, locations.get(0)));
        events.add(new Event("LCD Soundsystem Concert", "Electronic Night in Detroit", 8.5, locations.get(1)));
        events.add(new Event("Sigur Ros Concert", "Atmospheric Sounds in Reykjavik", 9.3, locations.get(2)));
        events.add(new Event("The Cure Concert", "Gothic Rock Night in Amsterdam", 8.4, locations.get(3)));
        events.add(new Event("Nine Inch Nails Concert", "Industrial Hits Live", 9.1, locations.get(4)));
    }


    public List<Event> findAll() {
        List<Event> eventsToDelete = new ArrayList<>();
        for (Event event : events) {
            Optional<Location> location = locationService.findById(event.getLocation().getId());
            if (location.isEmpty()) {
                eventsToDelete.add(event);
            }
        }
        for (Event event : eventsToDelete) {
            deleteEvent(event.getId());
        }
        return events;
    }

    public Optional<Event> findById(Long id) {
        return events.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public List<Event> searchEvents(String text, Double minRating) {
        return events.stream()
                .filter(e -> (text == null || e.getName().toLowerCase().contains(text.toLowerCase()) || e.getDescription().toLowerCase().contains(text.toLowerCase())) &&
                        (minRating == null || e.getPopularityScore() >= minRating))
                .collect(Collectors.toList());
    }

    public Optional<Event> saveEvent(String name, String description, Double popularityScore, Long locationID) {
        Location location = locationService.findById(locationID).get();
        Event event = new Event(name, description, popularityScore, location);
        events.add(event);
        return Optional.of(event);
    }

    public Optional<Event> editEvent(Long id, String name, String description, Double popularityScore, Long locationID) {
        Event event = findById(id).get();
        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        event.setLocation(locationService.findById(locationID).get());
        return Optional.of(event);
    }

    public Optional<Event> deleteEvent(Long id) {
        Event event = findById(id).get();
        events.remove(event);
        return Optional.of(event);
    }


}
