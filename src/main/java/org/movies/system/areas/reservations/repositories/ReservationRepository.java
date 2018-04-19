package org.movies.system.areas.reservations.repositories;

import org.movies.system.areas.reservations.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

    Reservation findFirstById(String id);

    List<Reservation> findAllByProjectionIdAndProjectionHourAndProjectionDeletedOnNull(String projectionId, String projectionHour);

    List<Reservation> findAllByUserIdAndProjectionDeletedOnNull(String id);
}
