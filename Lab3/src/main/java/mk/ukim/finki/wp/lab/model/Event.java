package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String description;
    @Column(name = "popularityscore")
    double popularityScore;

    @ManyToOne
    @JoinColumn(name = "locationid")
    private Location location;
}