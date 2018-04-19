package org.movies.system.areas.cinemas.repositories;

import org.movies.system.areas.cinemas.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, String> {

    Cinema findFirstByName(String name);

    Cinema findFirstById(String id);
}
