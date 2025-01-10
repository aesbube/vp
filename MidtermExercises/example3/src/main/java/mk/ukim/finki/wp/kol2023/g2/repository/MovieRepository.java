package mk.ukim.finki.wp.kol2023.g2.repository;

import mk.ukim.finki.wp.kol2023.g2.model.Genre;
import mk.ukim.finki.wp.kol2023.g2.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaSpecificationRepository<Movie, Long> {
    Optional<Movie> findByName(String name);
    List<Movie> findMovieByRatingLessThanAndGenre(Double rating, Genre genre);
    List<Movie> findMovieByRatingLessThan(Double rating);
    List<Movie> findMovieByGenre(Genre genre);
}
