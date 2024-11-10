package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Event {
    String name;
    String description;
    double popularityScore;

    public Event(String name, String description, double popularityScore) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
    }
}
