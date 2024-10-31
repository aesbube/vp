package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Event {
    String name;
    String description;
    double popularityScore;
}
