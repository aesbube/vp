package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    List<Event> eventList = new ArrayList<>();

    public EventRepository() {
        eventList.add(new Event("Arctic Monkeys Concert", "Live in Manchester", 9.2));
        eventList.add(new Event("Radiohead Concert", "Special Night in Paris", 9.6));
        eventList.add(new Event("The Strokes Concert", "Open Air Performance in NYC", 8.9));
        eventList.add(new Event("Foo Fighters Concert", "Rock Night in LA", 9.0));
        eventList.add(new Event("Slowdive Tribute Concert", "Unplugged Experience", 8.3));
        eventList.add(new Event("Fugazi Concert", "Greatest Hits in Las Vegas", 8.7));
        eventList.add(new Event("Oasis Tribute Concert", "Britpop Revival in London", 8.5));
        eventList.add(new Event("Muse Concert", "Spectacular Show in Berlin", 9.3));
        eventList.add(new Event("Smashing Pumpkins Concert", "Alternative Rock Night", 8.4));
        eventList.add(new Event("Depeche Mode Concert", "Classic Electronic Hits", 9.1));
    }

    public List<Event> findAll() {
        return eventList;
    }

    public List<Event> searchEvents(String text, Double minimalRating) {
        return eventList.stream().filter(x -> x.getName().toLowerCase().contains(text.toLowerCase()) || x.getDescription().toLowerCase().contains(text.toLowerCase()) && x.getPopularityScore() >= minimalRating).collect(Collectors.toList());
    }
}
