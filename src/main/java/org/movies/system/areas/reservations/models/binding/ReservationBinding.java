package org.movies.system.areas.reservations.models.binding;

import org.movies.system.areas.projections.entities.Projection;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class ReservationBinding {

    @NotEmpty(message = "Please choose hour")
    private String projectionHour;

    @NotEmpty(message = "Please choose seats ")
    private List<String> seats;

    @Min(value = 1, message = "You have to choose tickets!")
    @Max(value = 6, message = "Maximum number of tickets is 6!")
    private int tickets;

    private Projection projection;

    private double price;

    public ReservationBinding() {
        this.seats = new ArrayList<>();
    }

    public List<String> getSeats() {
        return this.seats;
    }

    public void setSeats(List<String> seats) {
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
