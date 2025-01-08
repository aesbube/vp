//package mk.ukim.finki.wp.lab.repository.old;
//
//import mk.ukim.finki.wp.lab.model.Event;
//import mk.ukim.finki.wp.lab.model.Location;
//import mk.ukim.finki.wp.lab.service.LocationService;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class EventRepository {
//    private List<Event> events = new ArrayList<>();
//    private final LocationService locationService;
//
//
//
//    public List<Event> findAll() {
//        List<Event> eventsToDelete = new ArrayList<>();
//        for (Event event : events) {
//            Optional<Location> location = locationService.findById(event.getLocation().getId());
//            if (location.isEmpty()) {
//                eventsToDelete.add(event);
//            }
//        }
//        for (Event event : eventsToDelete) {
//            deleteEvent(event.getId());
//        }
//        return events;
//    }
//
//    public Optional<Event> findById(Long id) {
//        return events.stream().filter(e -> e.getId().equals(id)).findFirst();
//    }
//
//    public List<Event> searchEvents(String text, Double minRating) {
//        return events.stream()
//                .filter(e -> (text == null || e.getName().toLowerCase().contains(text.toLowerCase()) || e.getDescription().toLowerCase().contains(text.toLowerCase())) &&
//                        (minRating == null || e.getPopularityScore() >= minRating))
//                .collect(Collectors.toList());
//    }
//
//    public Optional<Event> saveEvent(String name, String description, Double popularityScore, Long locationID) {
//        Location location = locationService.findById(locationID).get();
//        Event event = new Event(name, description, popularityScore, location);
//        events.add(event);
//        return Optional.of(event);
//    }
//
//    public Optional<Event> editEvent(Long id, String name, String description, Double popularityScore, Long locationID) {
//        Event event = findById(id).get();
//        event.setName(name);
//        event.setDescription(description);
//        event.setPopularityScore(popularityScore);
//        event.setLocation(locationService.findById(locationID).get());
//        return Optional.of(event);
//    }
//
//    public Optional<Event> deleteEvent(Long id) {
//        Event event = findById(id).get();
//        events.remove(event);
//        return Optional.of(event);
//    }
//
//
//}
