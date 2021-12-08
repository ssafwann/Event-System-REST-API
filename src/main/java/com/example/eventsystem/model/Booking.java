/*
    The class which represents the booking entity
 */
package com.example.eventsystem.model;

import org.springframework.core.annotation.Order;
import javax.persistence.*;

@Entity
@Table(name="bookings")
@Order(10)
public class Booking {
    @Id
    @SequenceGenerator(
            name = "booking_sequence",
            sequenceName = "booking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "booking_sequence"
    )
    private Long id;


    @ManyToOne
    @JoinColumn(
            name = "event_id",
            referencedColumnName = "id"
    )
    private Event event;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;

    private Integer tickets;
    @Transient
    private Integer amount;

    // constructors
    public Booking() {
    }

    public Booking(Long id, Event event, User user, Integer tickets) {
        this.id = id;
        this.event = event;
        this.user = user;
        this.tickets = tickets;
    }

    public Booking(Event event, User user, Integer tickets) {
        this.event = event;
        this.user = user;
        this.tickets = tickets;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long bookingID) {
        this.id = id;
    }

    public Event getEventBooked() {
        return event;
    }

    public void setEventBooked(Event event) {
        this.event = event;
    }

    public User getUserBooked() {
        return user;
    }

    public void setUserBooked(User user) {
        this.user = user;
    }

    public Integer getAmount() {
        if (getEventBooked().getPrice() != null) {
            amount = getEventBooked().getPrice() * tickets;
            return amount;
        }
        else return 0;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + id +
                ", eventID=" + event +
                ", userID=" + user +
                ", amount=" + amount +
                ", tickets=" + tickets +
                '}';
    }
}
