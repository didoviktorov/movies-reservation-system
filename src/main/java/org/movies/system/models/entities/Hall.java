package org.movies.system.models.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "halls_seats",
            joinColumns = @JoinColumn(name = "hall_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id", referencedColumnName = "id")
    )
    private Set<Seat> seats;

    public Hall() {
        this.seats = new HashSet<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Seat> getSeats() {
        return this.seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }
}
