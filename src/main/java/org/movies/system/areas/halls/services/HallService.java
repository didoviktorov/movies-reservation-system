package org.movies.system.areas.halls.services;

import org.movies.system.areas.halls.entities.Hall;

public interface HallService {

    void save(Hall hall);

    Hall findById(String id);
}
