package org.movies.system.services.cinema;

import org.movies.system.models.entities.Cinema;

import java.util.List;

public interface CinemaService {

    Long cinemaCount();

    void save(Cinema cinema);

    List<Cinema> findAll();

    Cinema findById(String id);

    void seedCinemas();
}
