package org.movies.system.areas.projections.models.view;

import org.movies.system.areas.cinemas.entities.Cinema;
import org.movies.system.areas.halls.models.HallViewDto;
import org.movies.system.areas.movies.models.view.MovieViewDto;

import java.util.Date;

public class ProjectionViewDto {

    private Date projectionDate;

    private String hours;

    private Cinema cinema;

    private HallViewDto hall;

    private MovieViewDto movie;

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

    public HallViewDto getHall() {
        return this.hall;
    }

    public void setHall(HallViewDto hall) {
        this.hall = hall;
    }

    public MovieViewDto getMovie() {
        return this.movie;
    }

    public void setMovie(MovieViewDto movie) {
        this.movie = movie;
    }
}
