package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventService;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "EventListServlet", urlPatterns = "/")
public class EventListServlet extends HttpServlet {
    private final EventService eventService;

    public EventListServlet(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Event> events = eventService.listAll();
        req.setAttribute("events", events);
        req.getRequestDispatcher("/WEB-INF/templates/listEvents.html").forward(req, resp);
    }
}
