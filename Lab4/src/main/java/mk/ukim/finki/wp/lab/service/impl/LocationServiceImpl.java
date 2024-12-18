package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.repository.LocationRepository;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository repository;

    public LocationServiceImpl(LocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Location> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Location> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Location> removeLocation(Long id) {
        Optional<Location> location = repository.findById(id);
        if (location.isPresent()) {
            repository.delete(location.get());
            return Optional.of(location.get());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Location> addLocation(String name, String address, String capacity, String description) {
        Location location = new Location();
        location.setName(name);
        location.setAddress(address);
        location.setCapacity(Integer.parseInt(capacity));
        location.setDescription(description);
        return Optional.of(repository.save(location));
    }


}
