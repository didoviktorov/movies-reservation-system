package org.movies.system.areas.halls.entities;

import org.hibernate.annotations.GenericGenerator;
import org.movies.system.areas.seats.entities.Seat;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<Seat> orderedSeats() {
        return this.getSeats().stream()
                .sorted((s1, s2) -> s1.getSeatNumber() - s2.getSeatNumber())
                .collect(Collectors.toList());
    }
}
