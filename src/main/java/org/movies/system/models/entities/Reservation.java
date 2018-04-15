package org.movies.system.models.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String projectionHour;

    @ManyToMany
    @JoinTable(
            name = "reservations_seats",
            joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id", referencedColumnName = "id")
    )
    private Set<Seat> seats;

    private int tickets;

    @ManyToOne
    @JoinColumn(name="projection_id", nullable=false)
    private Projection projection;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    private double price;

    public Reservation() {
        this.seats = new HashSet<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Seat> getSeats() {
        return this.seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public String getProjectionHour() {
        return this.projectionHour;
    }

    public void setProjectionHour(String projectionHour) {
        this.projectionHour = projectionHour;
    }

    public Projection getProjection() {
        return this.projection;
    }

    public void setProjection(Projection projection) {
        this.projection = projection;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTickets() {
        return this.tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
