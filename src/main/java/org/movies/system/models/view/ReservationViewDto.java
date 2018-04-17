package org.movies.system.models.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationViewDto {

    private String projectionHour;

    private List<SeatViewDto> seats;

    private int tickets;

    private ProjectionViewDto projection;

    private double price;

    public ReservationViewDto() {
        this.seats = new ArrayList<>();
    }

    public List<SeatViewDto> getSeats() {
        return this.seats.stream()
                .sorted((s1, s2) -> s1.getSeatNumber() - s2.getSeatNumber())
                .collect(Collectors.toList());
//        return this.seats;
    }

    public void setSeats(List<SeatViewDto> seats) {
        this.seats = seats;
    }

    public String getProjectionHour() {
        return this.projectionHour;
    }

    public void setProjectionHour(String projectionHour) {
        this.projectionHour = projectionHour;
    }

    public ProjectionViewDto getProjection() {
        return this.projection;
    }

    public void setProjection(ProjectionViewDto projection) {
        this.projection = projection;
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
