package org.movies.system.areas.cinemas.services;

import org.movies.system.areas.cinemas.entities.Cinema;

import java.util.List;

public interface CinemaService {

    Long cinemaCount();

    void save(Cinema cinema);

    List<Cinema> findAll();

    Cinema findById(String id);

    void seedCinemas();
}
