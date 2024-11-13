package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class EventBooking {
    String eventName;
    String attendeeName;
    String attendeeAddress;
    Long numberOfTickets;
    String clientIp;

    public EventBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets, String clientIp) {
        this.eventName = eventName;
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTickets = numberOfTickets;
        this.clientIp = clientIp;
    }
}
