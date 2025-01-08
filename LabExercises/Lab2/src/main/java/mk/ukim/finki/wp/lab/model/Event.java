package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Event {
    String name;
    String description;
    double popularityScore;
    private Long id;
    private Location location;

    public Event(String name, String description, double popularityScore, Location location) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.id = (long) (Math.random() * 1000);
        this.location = location;
    }
}
