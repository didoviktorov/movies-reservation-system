package org.movies.system.repositories;

import org.movies.system.models.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, String> {

    Cinema findFirstByName(String name);

    Cinema findFirstById(String id);
}
