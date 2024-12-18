package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    public List<Location> findAll();

    public Optional<Location> findById(Long id);

    public Optional<Location> removeLocation(Long id);

    public Optional<Location> addLocation(String name, String address, String capacity, String description);
}
