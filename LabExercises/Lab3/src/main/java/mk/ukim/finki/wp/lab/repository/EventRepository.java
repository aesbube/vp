package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByLocation_Id(Long locationId);

    List<Event> findAllByPopularityScoreGreaterThanEqual(double popularityScore);

    List<Event> findByPopularityScoreGreaterThanEqualAndLocation_Id(double popularityScore, Long locationId);

    List<Event> findByNameContainingIgnoreCase(String name);

    List<Event> findByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqual(String name, double popularityScore);

    List<Event> findByNameContainingIgnoreCaseAndLocation_Id(String name, Long locationId);

    List<Event> findByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqualAndLocation_Id(String name, double popularityScore, Long locationId);
}
