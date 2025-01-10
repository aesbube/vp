package mk.ukim.finki.wp.kol2023.g2.service.impl;

import mk.ukim.finki.wp.kol2023.g2.model.Director;
import mk.ukim.finki.wp.kol2023.g2.model.Genre;
import mk.ukim.finki.wp.kol2023.g2.model.Movie;
import mk.ukim.finki.wp.kol2023.g2.model.exceptions.InvalidDirectorIdException;
import mk.ukim.finki.wp.kol2023.g2.model.exceptions.InvalidMovieIdException;
import mk.ukim.finki.wp.kol2023.g2.repository.DirectorRepository;
import mk.ukim.finki.wp.kol2023.g2.repository.MovieRepository;
import mk.ukim.finki.wp.kol2023.g2.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.wp.kol2023.g2.service.FieldFilterSpecification.*;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;

    public MovieServiceImpl(MovieRepository movieRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
    }

    @Override
    public List<Movie> listAllMovies() {
        return this.movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return this.movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
    }

    @Override
    public Movie create(String name, String description, Double rating, Genre genre, Long director) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDescription(description);
        movie.setRating(rating);
        movie.setGenre(genre);
        Director dir = this.directorRepository.findById(director).orElseThrow(InvalidDirectorIdException::new);
        movie.setDirector(dir);
        return this.movieRepository.save(movie);
    }

    @Override
    public Movie update(Long id, String name, String description, Double rating, Genre genre, Long director) {
        Movie movie = this.movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
        movie.setName(name);
        movie.setDescription(description);
        movie.setRating(rating);
        movie.setGenre(genre);
        Director dir = this.directorRepository.findById(director).orElseThrow(InvalidDirectorIdException::new);
        movie.setDirector(dir);
        return this.movieRepository.save(movie);
    }

    @Override
    public Movie delete(Long id) {
        Movie movie = this.movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
        this.movieRepository.delete(movie);
        return movie;
    }

    @Override
    public Movie vote(Long id) {
        Movie movie = this.movieRepository.findById(id).orElseThrow(InvalidMovieIdException::new);
        movie.setVotes(movie.getVotes() + 1);
        return this.movieRepository.save(movie);
    }

    @Override
    public List<Movie> listMoviesWithRatingLessThenAndGenre(Double rating, Genre genre) {
        if (rating != null && genre != null) {
            return this.movieRepository.findMovieByRatingLessThanAndGenre(rating, genre);
        } else if (rating != null) {
            return this.movieRepository.findMovieByRatingLessThan(rating);
        } else if (genre != null) {
            return this.movieRepository.findMovieByGenre(genre);
        } else
            return this.movieRepository.findAll();
    }

    @Override
    public Page<Movie> findPage(Double rating, Genre genre, int pageNum, int pageSize) {
        Specification<Movie> specification = Specification
                .where(greaterThan(Movie.class, "rating", rating))
                .and(filterEqualsV(Movie.class, "genre", genre));

        return this.movieRepository.findAll(
                specification,
                PageRequest.of(pageNum - 1, pageSize)
        );
    }
}
