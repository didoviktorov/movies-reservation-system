package org.movies.system.areas.reservations.services;

import org.movies.system.areas.reservations.models.binding.ReservationBinding;
import org.movies.system.areas.reservations.entities.Reservation;
import org.movies.system.areas.reservations.models.view.ReservationViewDto;

import java.util.List;

public interface ReservationService {

    void save(ReservationBinding reservationBinding);

    List<Reservation> findAll();

    Reservation findById(String id);

    List<Reservation> findAllByProjectionIdAndProjectionHour(String id, String projectionHour);

    List<ReservationViewDto> findAllByUserName(String username);
}
