package mk.ukim.finki.wp.lab.web.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/", "/events"})
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model, @RequestParam(required = false) String searchText, @RequestParam(required = false) String minRating, @RequestParam(required = false) String locationId) {
        Double rating = minRating != null && !minRating.isEmpty() ? Double.valueOf(minRating) : null;
        Long location = locationId != null && !locationId.isEmpty() ? Long.valueOf(locationId) : null;
        List<Event> events;
        if ((searchText != null && !searchText.isEmpty()) || rating != null || location != null) {
            events = eventService.searchEvents(searchText, rating, location);
        } else {
            events = eventService.listAll();
        }
        model.addAttribute("events", events);
        model.addAttribute("error", error);
        return "listEvents";
    }
    
    @GetMapping("/events/add")
    public String showAdd(Model model) {
        model.addAttribute("mode", "add");
        model.addAttribute("locations", locationService.findAll());
        return "addEvent";
    }

    @GetMapping("/events/edit/{eventId}")
    public String showEdit(@PathVariable Long eventId, RedirectAttributes redirectAttributes, Model model) {
        Optional<Event> eventOptional = eventService.findById(eventId);
        if (eventOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Event with id " + eventId + " not found");
            return "redirect:/events";
        }

        Event event = eventService.findById(eventId).get();
        model.addAttribute("mode", "edit");
        model.addAttribute("event", event);
        model.addAttribute("locations", locationService.findAll());
        return "addEvent";
    }

    @GetMapping("/events/locations")
    public String showLocations(Model model) {
        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);
        return "locations";
    }

    @PostMapping("/events/locations/add")
    public String addLocation(@RequestParam String name, @RequestParam String address, @RequestParam String capacity, @RequestParam String description) {
        locationService.addLocation(name, address, capacity, description);
        return "redirect:/events/locations";
    }

    @PostMapping("/events/locations/delete/{id}")
    public String deleteLocation(@PathVariable Long id) {
        locationService.removeLocation(id);
        return "redirect:/events/locations";
    }

    @PostMapping("/events/add")
    public String saveEvent(@RequestParam String name, @RequestParam String description, @RequestParam Double popularityScore, @RequestParam Long locationID) {
        eventService.saveEvent(name, description, popularityScore, locationID);
        return "redirect:/events";
    }

    @PostMapping("/events/edit/{eventId}")
    public String editEvent(@PathVariable Long eventId, @RequestParam String name, @RequestParam String description, @RequestParam Double popularityScore, @RequestParam Long locationID) {
        eventService.editEvent(eventId, name, description, popularityScore, locationID);
        return "redirect:/events";
    }

    @PostMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }
}
