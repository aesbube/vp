package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    private List<Event> events = new ArrayList<>();

    public EventRepository() {
        events.add(new Event("Tame Impala Concert", "Psychedelic Night in San Francisco", 9.2));
        events.add(new Event("The National Concert", "Intimate Evening in Brooklyn", 9.6));
        events.add(new Event("Florence + The Machine Concert", "Magical Performance in London", 8.9));
        events.add(new Event("Arcade Fire Concert", "Indie Rock Extravaganza in Montreal", 9.0));
        events.add(new Event("Bon Iver Concert", "Acoustic Session in Minneapolis", 8.3));
        events.add(new Event("Vampire Weekend Concert", "Summer Festival in Chicago", 8.7));
        events.add(new Event("LCD Soundsystem Concert", "Electronic Night in Detroit", 8.5));
        events.add(new Event("Sigur Ros Concert", "Atmospheric Sounds in Reykjavik", 9.3));
        events.add(new Event("The Cure Concert", "Gothic Rock Night in Amsterdam", 8.4));
        events.add(new Event("Nine Inch Nails Concert", "Industrial Hits Live", 9.1));
    }


    public List<Event> findAll() {
        return events;
    }

    public List<Event> searchEvents(String text, Double minRating) {
        return events.stream()
                .filter(e -> (text == null || e.getName().contains(text) || e.getDescription().contains(text)) &&
                        (minRating == null || e.getPopularityScore() >= minRating))
                .collect(Collectors.toList());
    }
}
