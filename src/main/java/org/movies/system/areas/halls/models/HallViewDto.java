package org.movies.system.areas.halls.models;

import org.movies.system.areas.seats.models.SeatViewDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HallViewDto {

    private String name;

    private Set<SeatViewDto> seats;

    public HallViewDto() {
        this.seats = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SeatViewDto> getSeats() {
        return this.seats;
    }

    public void setSeats(Set<SeatViewDto> seats) {
        this.seats = seats;
    }

    public List<SeatViewDto> orderedSeats() {
        return this.getSeats().stream()
                .sorted((s1, s2) -> s1.getSeatNumber() - s2.getSeatNumber())
                .collect(Collectors.toList());
    }
}
