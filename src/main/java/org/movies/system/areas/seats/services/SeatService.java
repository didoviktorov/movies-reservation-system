package org.movies.system.areas.seats.services;

import org.movies.system.areas.seats.entities.Seat;

public interface SeatService {

    void save(Seat seat);

    Seat findById(String id);
}
