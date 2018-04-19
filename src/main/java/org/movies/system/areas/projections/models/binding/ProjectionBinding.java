package org.movies.system.areas.projections.models.binding;

import org.movies.system.validators.ValidDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class ProjectionBinding {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ValidDate
    private Date projectionDate;

    @NotEmpty(message = "You must choose hours")
    private String hours;

    @NotEmpty(message = "You must choose cinema")
    private String cinema;

    @NotEmpty(message = "You must choose cinema hall")
    private String hall;

    @NotEmpty(message = "You must choose movie")
    private String movie;

    public ProjectionBinding() {
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

    public String getCinema() {
        return this.cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getHall() {
        return this.hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public String getMovie() {
        return this.movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }
}
