package org.movies.system.controllers;

import org.movies.system.models.binding.ReservationBinding;
import org.movies.system.services.cinema.ProjectionService;
import org.movies.system.services.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ReservationController extends BaseController {

    private ProjectionService projectionService;

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ProjectionService projectionService, ReservationService reservationService) {
        this.projectionService = projectionService;
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations/add/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView makerReservation(@PathVariable String id, @ModelAttribute ReservationBinding reservationBinding) {
        return this.getCreateReservationView(id, new ReservationBinding());
    }

    @PostMapping("/reservations/add/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView makerReservation(@PathVariable String id, @Valid @ModelAttribute ReservationBinding reservationBinding, BindingResult bindingResult) {
        if (reservationBinding.getSeats().size() != reservationBinding.getTickets()) {
            bindingResult.rejectValue("seats", "error.reservationBinding", "Incorrect number of seats selected!");
        }

        if (bindingResult.hasErrors()) {
            return this.getCreateReservationView(id, reservationBinding);
        }

        this.reservationService.save(reservationBinding);

        return this.redirect("/");
    }

    private ModelAndView getCreateReservationView(@PathVariable String id, @Valid @ModelAttribute ReservationBinding reservationBinding) {
        String[] names = { "projectionId", "projection", "reservationBinding", "hall"  };

        Object[] objects = {
                id,
                this.projectionService.findProjectionView(id),
                reservationBinding,
                this.projectionService.findById(id).getHall()
        };

        return this.view("reservations/add-reservation", names, objects);
    }
}
