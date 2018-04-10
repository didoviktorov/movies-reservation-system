package org.movies.system.models.binding;

import org.movies.system.custom.ValidDate;
import org.movies.system.models.entities.Cinema;
import org.movies.system.models.entities.Hall;
import org.movies.system.models.entities.Movie;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProjectionEditBinding {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ValidDate
    private Date projectionDate;

    @NotEmpty(message = "You must choose hours")
    private String hours;

    @NotNull
    private Cinema cinema;

    @NotNull
//    @NotEmpty(message = "You must choose cinema hall")
    private Hall hall;

    @NotNull
//    @NotEmpty(message = "You must choose movie")
    private Movie movie;

    public ProjectionEditBinding() {
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

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
