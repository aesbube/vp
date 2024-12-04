package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
