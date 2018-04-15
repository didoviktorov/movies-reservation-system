package org.movies.system.services.reservation;

import org.movies.system.models.binding.ReservationBinding;
import org.movies.system.models.entities.Reservation;

import java.util.List;

public interface ReservationService {

    void save(ReservationBinding reservationBinding);

    List<Reservation> findAll();

    Reservation findById(String id);

    List<Reservation> findAllByProjectionIdAndProjectionHour(String id, String projectionHour);

}
