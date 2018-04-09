package org.movies.system.repositories;

import org.movies.system.models.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, String> {

    Seat findFirstById(String id);

}
