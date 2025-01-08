package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/eventBooking")
public class EventBookingController {
    private final EventBookingService eventBookingService;

    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }

    @GetMapping
    public String showEventBooking(Model model) {
        List<EventBooking> eventBookings = eventBookingService.getEventBookings();
        model.addAttribute("bookings", eventBookings);
        return "bookingConfirmation";
    }

    @PostMapping
    public String bookingDetails(@RequestParam String eventName, @RequestParam String attendeeName, @RequestParam String attendeeAddress, @RequestParam String numTickets, HttpServletRequest request, Model model) {
        String clientIp = request.getRemoteAddr();
        eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, Integer.parseInt(numTickets), clientIp);
        List<EventBooking> eventBookings = eventBookingService.getEventBookings();
        model.addAttribute("bookings", eventBookings);
        return "bookingConfirmation";
    }
}
