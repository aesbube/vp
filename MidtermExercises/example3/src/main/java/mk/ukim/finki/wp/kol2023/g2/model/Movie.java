package mk.ukim.finki.wp.kol2023.g2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double rating;

    private Genre genre;

    @ManyToOne
    private Director director;

    private Integer votes = 0;

    public Movie(String name, String description, Double rating, Genre genre, Director director) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.genre = genre;
        this.director = director;
        this.votes = 0;
    }
}
