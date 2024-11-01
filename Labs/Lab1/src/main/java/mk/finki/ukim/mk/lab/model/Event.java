package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.springframework.stereotype.Component;

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
