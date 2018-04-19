package org.movies.system.areas.projections.entities;

import org.hibernate.annotations.GenericGenerator;
import org.movies.system.areas.cinemas.entities.Cinema;
import org.movies.system.validators.ValidDate;
import org.movies.system.areas.halls.entities.Hall;
import org.movies.system.areas.movies.entities.Movie;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "projections")
public class Projection {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ValidDate
    private Date projectionDate;

    @NotEmpty(message = "You must choose hours")
    private String hours;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private Date deletedOn;

    public Projection() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getDeletedOn() {
        return this.deletedOn;
    }

    public void setDeletedOn(Date deletedOn) {
        this.deletedOn = deletedOn;
    }
}
