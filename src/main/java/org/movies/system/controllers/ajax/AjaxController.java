package org.movies.system.controllers.ajax;

import org.movies.system.models.entities.Cinema;
import org.movies.system.models.entities.Reservation;
import org.movies.system.services.cinema.CinemaService;
import org.movies.system.services.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AjaxController {

    private CinemaService cinemaService;

    private ReservationService reservationService;

    @Autowired
    public AjaxController(CinemaService cinemaService, ReservationService reservationService) {
        this.cinemaService = cinemaService;
        this.reservationService = reservationService;
    }

    @ResponseBody
    @RequestMapping(value = "/all-cinemas")
    public List<Cinema> getCinemasViaAjax() {

        return this.cinemaService.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/cinema_ajax/{id}")
    public Cinema getCinemasViaAjax(@PathVariable String id) {
        return this.cinemaService.findById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/reserved-seats")
    public List<String> getReservationsAjax(HttpServletRequest request) {
        String projectionId = request.getParameter("projection");
        String hour = request.getParameter("hour");
        List<Reservation> reservations = this.reservationService.findAllByProjectionIdAndProjectionHour(projectionId, hour);
        List<String> seatsIds = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservation.getSeats().forEach(seat -> {
                seatsIds.add(seat.getId());
            });
        }
        return seatsIds;
    }
}
