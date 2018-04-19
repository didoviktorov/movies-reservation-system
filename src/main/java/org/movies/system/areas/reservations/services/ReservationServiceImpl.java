package org.movies.system.areas.reservations.services;

import org.modelmapper.ModelMapper;
import org.movies.system.exceptions.BadRequestException;
import org.movies.system.areas.reservations.models.binding.ReservationBinding;
import org.movies.system.areas.reservations.entities.Reservation;
import org.movies.system.areas.seats.entities.Seat;
import org.movies.system.areas.users.entities.User;
import org.movies.system.areas.reservations.models.view.ReservationViewDto;
import org.movies.system.areas.reservations.repositories.ReservationRepository;
import org.movies.system.areas.seats.services.SeatService;
import org.movies.system.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    private UserService userService;

    private SeatService seatService;

    private ModelMapper modelMapper;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, UserService userService, SeatService seatService, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.seatService = seatService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(ReservationBinding reservationBinding) {
        Reservation reservation = this.modelMapper.map(reservationBinding, Reservation.class);
        reservation.setSeats(new HashSet<>());
        for (String seatNumber : reservationBinding.getSeats()) {
//            Seat currentSeat = this.seatService.findById(seatNumber);
            Seat currentSeat = reservationBinding.getProjection().getHall().getSeats()
                    .stream()
                    .filter(s -> s.getSeatNumber() == Integer.valueOf(seatNumber))
                    .findFirst().orElse(null);
            reservation.getSeats().add(currentSeat);
            this.seatService.save(currentSeat);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            user = this.userService.findFirstByUsername(currentUserName);
        }
        if (user != null) {
            reservation.setUser(user);
            user.getReservations().add(reservation);
        }

        this.reservationRepository.save(reservation);
        this.userService.save(user);
    }

    @Override
    public List<Reservation> findAll() {
        return this.reservationRepository.findAll();
    }

    @Override
    public Reservation findById(String id) {
        Reservation reservation = this.reservationRepository.findFirstById(id);
        this.checkReservation(reservation);
        return reservation;
    }

    @Override
    public List<Reservation> findAllByProjectionIdAndProjectionHour(String id, String projectionHour) {
        return this.reservationRepository.findAllByProjectionIdAndProjectionHourAndProjectionDeletedOnNull(id, projectionHour)
                .stream()
                .filter(r -> r.getProjection().getMovie().getDeletedOn() == null)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationViewDto> findAllByUserName(String username) {
        String userId = this.userService.findFirstByUsername(username).getId();
        List<ReservationViewDto> reservationViewDtos = this.reservationRepository.findAllByUserIdAndProjectionDeletedOnNull(userId)
                .stream()
                .filter(r -> r.getProjection().getMovie().getDeletedOn() == null)
                .map(reservation -> this.modelMapper.map(reservation, ReservationViewDto.class))
                .collect(Collectors.toList());

        return reservationViewDtos;
    }

    private void checkReservation(Reservation reservation) {
        if (reservation == null) {
            throw new BadRequestException();
        }
    }
}
