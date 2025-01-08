package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "event_booking")
public class EventBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "eventname")
    private String eventName;

    @Column(name = "attendeename")
    private String attendeeName;

    @Column(name = "attendeeaddress")
    private String attendeeAddress;

    @Column(name = "numberoftickets")
    private Integer numberOfTickets;
}
