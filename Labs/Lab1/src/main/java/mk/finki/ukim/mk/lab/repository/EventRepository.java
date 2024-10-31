package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    List<Event> eventList = new ArrayList<>(10);

    public List<Event> findAll() {
        return eventList;
    }

    public List<Event> searchEvents(String text) {
        return eventList.stream().filter(x -> x.getName().toLowerCase().contains(text.toLowerCase()) || x.getDescription().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
    }
}
