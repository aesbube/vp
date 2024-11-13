package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository {
    private List<Location> locationList = new ArrayList<Location>();

    public LocationRepository() {
        locationList.add(new Location("Skyline Plaza", "123 City Center, Chicago, IL", "3500", "A modern rooftop venue with panoramic views of the city skyline."));
        locationList.add(new Location("Lakeside Gardens", "789 Shoreline Dr, Orlando, FL", "1200", "A lush, scenic venue by the lake, perfect for intimate gatherings."));
        locationList.add(new Location("Historic Theater District", "101 Broadway St, Nashville, TN", "2500", "A grand theater in the heart of the historic district, offering classic charm."));
        locationList.add(new Location("Forest Haven Lodge", "202 Woodland Ln, Portland, OR", "800", "A cozy lodge tucked in a serene forest setting, ideal for rustic events."));
        locationList.add(new Location("Sunset Point Amphitheater", "678 Cliffside Ave, Malibu, CA", "5000", "An open-air amphitheater with breathtaking sunset views over the ocean."));
    }

    public List<Location> findAll() {
        return locationList;
    }

    public Optional<Location> findById(Long id) {
        return locationList.stream().filter(location -> location.getId().equals(id)).findFirst();
    }
}
