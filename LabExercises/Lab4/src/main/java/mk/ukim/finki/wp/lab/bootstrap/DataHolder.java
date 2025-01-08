package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.repository.LocationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> eventList;
    public static List<Location> locationList;
    public static List<EventBooking> eventBookingList;
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public DataHolder(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    public void init() {
        locationList = new ArrayList<>(5);
        if (locationRepository.count() == 0) {
            locationList.add(new Location("Skyline Plaza", "123 City Center, Chicago, IL", 3500, "A modern rooftop venue with panoramic views of the city skyline."));
            locationList.add(new Location("Lakeside Gardens", "789 Shoreline Dr, Orlando, FL", 1200, "A lush, scenic venue by the lake, perfect for intimate gatherings."));
            locationList.add(new Location("Historic Theater District", "101 Broadway St, Nashville, TN", 2500, "A grand theater in the heart of the historic district, offering classic charm."));
            locationList.add(new Location("Forest Haven Lodge", "202 Woodland Ln, Portland, OR", 800, "A cozy lodge tucked in a serene forest setting, ideal for rustic events."));
            locationList.add(new Location("Sunset Point Amphitheater", "678 Cliffside Ave, Malibu, CA", 5000, "An open-air amphitheater with breathtaking sunset views over the ocean."));

            locationRepository.saveAll(locationList);
        }

        eventList = new ArrayList<>(10);
        if (eventRepository.count() == 0) {
            eventList.add(new Event("Tame Impala Concert", "Psychedelic Night in San Francisco", 9.2, locationList.get(0)));
            eventList.add(new Event("The National Concert", "Intimate Evening in Brooklyn", 9.6, locationList.get(1)));
            eventList.add(new Event("Florence + The Machine Concert", "Magical Performance in London", 8.9, locationList.get(2)));
            eventList.add(new Event("Arcade Fire Concert", "Indie Rock Extravaganza in Montreal", 9.0, locationList.get(3)));
            eventList.add(new Event("Bon Iver Concert", "Acoustic Session in Minneapolis", 8.3, locationList.get(4)));
            eventList.add(new Event("Vampire Weekend Concert", "Summer Festival in Chicago", 8.7, locationList.get(0)));
            eventList.add(new Event("LCD Soundsystem Concert", "Electronic Night in Detroit", 8.5, locationList.get(1)));
            eventList.add(new Event("Sigur Ros Concert", "Atmospheric Sounds in Reykjavik", 9.3, locationList.get(2)));
            eventList.add(new Event("The Cure Concert", "Gothic Rock Night in Amsterdam", 8.4, locationList.get(3)));
            eventList.add(new Event("Nine Inch Nails Concert", "Industrial Hits Live", 9.1, locationList.get(4)));

            eventRepository.saveAll(eventList);
        }

        eventBookingList = new ArrayList<>();
    }
}
