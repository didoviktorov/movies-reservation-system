package org.movies.system.models.view;

import org.movies.system.models.entities.Cinema;
import org.movies.system.models.entities.Hall;
import org.movies.system.models.entities.Movie;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProjectionViewDto {

    private Date projectionDate;

    private String hours;

    private Cinema cinema;

    private Hall hall;

    public ProjectionViewDto() {
    }

    public Date getProjectionDate() {
        return this.projectionDate;
    }

    public void setProjectionDate(Date projectionDate) {
        this.projectionDate = projectionDate;
    }

    public String getHours() {
        return this.hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Cinema getCinema() {
        return this.cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Hall getHall() {
        return this.hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
