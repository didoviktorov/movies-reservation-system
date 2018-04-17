package org.movies.system.repositories;

import org.movies.system.models.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

    Reservation findFirstById(String id);

    List<Reservation> findAllByProjectionIdAndProjectionHour(String projectionId, String projectionHour);


}
